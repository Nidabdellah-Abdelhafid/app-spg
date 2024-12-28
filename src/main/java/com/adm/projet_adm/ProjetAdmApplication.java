package com.adm.projet_adm;

import com.adm.projet_adm.security.entities.AppRole;
import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ProjetAdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetAdmApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.addRole(new AppRole(1L,"ADMIN"));
            accountService.addRole(new AppRole(2L,"USER"));

        };
    }
}
