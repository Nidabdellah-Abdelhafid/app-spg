package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findThemeById(long id);
}
