package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Paiement findPaiementById(long id);
}
