package com.adm.projet_adm.security.repositories;

import com.adm.projet_adm.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser findById(long id);
}
