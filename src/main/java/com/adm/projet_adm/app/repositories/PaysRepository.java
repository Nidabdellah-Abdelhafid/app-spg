package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Long> {
    Pays findPaysById(Long id);
}
