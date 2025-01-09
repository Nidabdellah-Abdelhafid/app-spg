package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BadgeRepository extends JpaRepository<Badge, Long> {
        Badge findBadgesById(Long id);
}
