package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.repositories.BadgeRepository;
import com.adm.projet_adm.app.repositories.OffreRepository;
import com.adm.projet_adm.app.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    public Offre save(Offre offre) {
        return offreRepository.save(offre);
    }

    public List<Offre> findAll() {
        return offreRepository.findAll();
    }

    public Offre findById(Long id) {
        return offreRepository.findOffreById(id);
    }

    public void deleteById(Long id) {
        offreRepository.deleteById(id);
    }

    public Offre update(Long id, Offre offre) {
        if (offreRepository.existsById(id)) {
            Offre existingOffre = offreRepository.findOffreById(id);
            offre.setId(id);
            
            offre.setThemes(existingOffre.getThemes());
            offre.setBadges(existingOffre.getBadges());
            return offreRepository.save(offre);
        }
        return null;
    }

    @Transactional
    public void addThemeToOffre(Offre offre, Theme theme) {
        if (offreRepository.existsById(offre.getId()) && themeRepository.existsById(theme.getId())) {
            Offre offreById = offreRepository.findOffreById(offre.getId());
            Theme themeById = themeRepository.findThemeById(theme.getId());
            
            boolean hasTheme = offreById.getThemes().stream()
                    .anyMatch(t -> t.getId().equals(themeById.getId()));
                    
            if (!hasTheme) {
                offreById.getThemes().add(themeById);
            }
        }
    }

    @Transactional
    public void addBadgeToOffre(Offre offre, Badge badge) {
        if (offreRepository.existsById(offre.getId()) && badgeRepository.existsById(badge.getId())) {
            Offre offreById = offreRepository.findOffreById(offre.getId());
            Badge badgeById = badgeRepository.findBadgesById(badge.getId());
            
            boolean hasBadge = offreById.getBadges().stream()
                    .anyMatch(b -> b.getId().equals(badgeById.getId()));
                    
            if (!hasBadge) {
                offreById.getBadges().add(badgeById);
            }
        }
    }
}
