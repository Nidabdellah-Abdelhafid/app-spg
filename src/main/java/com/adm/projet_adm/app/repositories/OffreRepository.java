package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, Long> {
        Offre findOffreById(Long id);
}
