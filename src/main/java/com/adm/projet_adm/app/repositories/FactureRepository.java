package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Facture findFactureById(long id);
}
