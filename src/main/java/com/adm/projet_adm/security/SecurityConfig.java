package com.adm.projet_adm.security;

import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.jwt.JwtAuthenticationFilter;
import com.adm.projet_adm.security.jwt.JwtAuthorizationFilter;
import com.adm.projet_adm.security.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Lazy
    @Autowired
    private AccountService accountService;
    @Autowired
    private Environment environment;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.out.println("Attempting to load user with email: " + username);  // Debugging log
                AppUser appUser = accountService.getUserbyEmail(username);
                if (appUser == null) {
                    throw new UsernameNotFoundException("User not found with email: " + username);
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                appUser.getAppRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                });

                return new User(appUser.getEmail(), appUser.getPassword(), authorities);
            }

        });

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                //config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                String[] allowedOrigins = environment.getProperty("cors.allowedOrigins", String.class, "").split(",");
                config.setAllowedOrigins(Arrays.asList(allowedOrigins));
                //config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedMethods(Collections.singletonList("*"));
                //config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        });
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .headers().frameOptions().disable()
                    .and()
                .authorizeRequests().antMatchers("/h2-console/**","/refreshToken/**","/login/**","/users/createUser/**","/addRoleToUser/**").permitAll()
                .antMatchers("/ws/**").permitAll()  // Allow WebSocket connections
                .antMatchers("/topic/**", "/queue/**").authenticated()    
                .and()
                .authorizeRequests().anyRequest().authenticated()
                    .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
