package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public Theme save(Theme theme) {
        return themeRepository.save(theme);
    }

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    public Theme findById(Long id) {
        return themeRepository.findThemeById(id);
    }

    public void deleteById(Long id) {
        themeRepository.deleteById(id);
    }

    public Theme update(Long id, Theme theme) {
        if (themeRepository.existsById(id)) {
            theme.setId(id);
            return themeRepository.save(theme);
        }
        return null; // or throw an exception if needed
    }
}
