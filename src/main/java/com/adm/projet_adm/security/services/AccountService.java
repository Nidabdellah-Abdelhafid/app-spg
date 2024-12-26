package com.adm.projet_adm.security.services;

import com.adm.projet_adm.security.entities.AppRole;
import com.adm.projet_adm.security.entities.AppUser;

import java.util.List;

public interface AccountService {
        AppUser addUser(AppUser appUser);
        AppRole addRole(AppRole appRole);

        void addRoleToUser(String roleName, String email);

        AppUser getUserbyEmail(String email);
        List<AppUser> getUsers();
}
