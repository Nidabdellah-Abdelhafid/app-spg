package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.repositories.PaysRepository;
import com.adm.projet_adm.app.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private PaysRepository paysRepository;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Offre create(@RequestBody Offre offre) {
        if (offre.getPays() == null ) {
            throw new IllegalArgumentException("All required fields must be filled.");
        }

        Pays pays = paysRepository.findPaysById(offre.getPays().getId());
        offre.setPays(pays);
        return offreService.save(offre);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Offre> getAll() {
        return offreService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Offre getById(@PathVariable Long id) {
        return offreService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Offre update(@PathVariable Long id, @RequestBody Offre offre) {
        return offreService.update(id, offre);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        offreService.deleteById(id);
    }

    @PostMapping(path = "/addThemeToOffre")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addThemeToOffre(@RequestBody ThemeOffreForme themeOffreForme) {
        System.out.println("theme to offre"+themeOffreForme);
        offreService.addThemeToOffre(themeOffreForme.getOffre(),themeOffreForme.getTheme());
    }

    @PostMapping(path = "/addBadgeToOffre")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addBadgeToOffre(@RequestBody BadgeOffreForme badgeOffreForme) {
        System.out.println("badge to offre"+badgeOffreForme);

        offreService.addBadgeToOffre(badgeOffreForme.getOffre(),badgeOffreForme.getBadge());
    }
}


class ThemeOffreForme {
    private Theme theme;
    private Offre offre;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}

class BadgeOffreForme {
    private Badge badge;
    private Offre offre;

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}
