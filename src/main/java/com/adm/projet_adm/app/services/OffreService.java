package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.repositories.BadgeRepository;
import com.adm.projet_adm.app.repositories.OffreRepository;
import com.adm.projet_adm.app.repositories.ThemeRepository;
import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.repositories.AppUserRepository;

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

    @Autowired
    private AppUserRepository appUserRepository;

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

    @Transactional
    public void addFavoriteToOffre(Offre offre, AppUser appUser) {
        if (offreRepository.existsById(offre.getId())) {
            Offre offreById = offreRepository.findOffreById(offre.getId());
            AppUser appUserById = appUserRepository.findByEmail(appUser.getEmail());
            
            if (appUserById != null) {
                boolean hasFavorite = offreById.getAppUsers().stream()
                        .anyMatch(u -> u.getId() == appUserById.getId());
                        
                if (!hasFavorite) {
                    offreById.getAppUsers().add(appUserById);
                    offreRepository.save(offreById);
                }
            }
        }
    }

    @Transactional
    public void removeFavoriteFromOffre(Offre offre, AppUser appUser) {
        if (offreRepository.existsById(offre.getId())) {
            Offre offreById = offreRepository.findOffreById(offre.getId());
            AppUser appUserById = appUserRepository.findByEmail(appUser.getEmail());
            
            if (appUserById != null) {
                boolean hasFavorite = offreById.getAppUsers().stream()
                        .anyMatch(u -> u.getId() == appUserById.getId());
                        
                if (hasFavorite) {
                    offreById.getAppUsers().remove(appUserById);
                    offreRepository.save(offreById);
                }
            }
        }
    }
}
