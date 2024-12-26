package com.adm.projet_adm.security;

import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.repositories.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByEmail(username);  // Assuming 'email' is used as the username.

        if (appUser == null) {
            throw new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found");
        }

        // Convert roles to authorities (GrantedAuthority objects)
        var authorities = appUser.getAppRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new User(appUser.getEmail(), appUser.getPassword(), authorities);
    }
}
