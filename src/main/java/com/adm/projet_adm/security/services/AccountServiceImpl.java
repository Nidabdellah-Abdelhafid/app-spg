package com.adm.projet_adm.security.services;

import com.adm.projet_adm.security.entities.AppRole;
import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.repositories.AppRoleRepository;
import com.adm.projet_adm.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        String password = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String roleName, String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Transactional
    public AppUser getUserbyEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        appUser.getAppRoles().size();
        return appUser;
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }
}
