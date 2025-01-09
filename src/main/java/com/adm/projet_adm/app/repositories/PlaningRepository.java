package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Planing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaningRepository extends JpaRepository<Planing, Long> {
    Planing findPlaningById(long id);
}
