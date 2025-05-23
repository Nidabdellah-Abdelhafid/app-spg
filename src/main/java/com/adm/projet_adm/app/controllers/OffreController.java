package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.repositories.PaysRepository;
import com.adm.projet_adm.app.services.OffreService;
import com.adm.projet_adm.security.entities.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    // @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<Offre> offres = offreService.findAll();
        List<Map<String, Object>> response = offres.stream().map(offre -> {
            Map<String, Object> offreMap = new HashMap<>();
            offreMap.put("id", offre.getId());
            offreMap.put("label", offre.getLabel());
            offreMap.put("description", offre.getDescription());
            offreMap.put("price", offre.getPrice());
            offreMap.put("image", offre.getImage());
            offreMap.put("latitude", offre.getLatitude());
            offreMap.put("longitude", offre.getLongitude());
            offreMap.put("offreDayNumber", offre.getOffreDayNumber());
            offreMap.put("photos", offre.getPhotos());
            offreMap.put("pays", offre.getPays());
            offreMap.put("usersFvrOffre", offre.getAppUsers());
            offreMap.put("themes", new ArrayList<>(offre.getThemes()));
            offreMap.put("badges", new ArrayList<>(offre.getBadges()));
            return offreMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
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

     @GetMapping("/{id}/badges")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> getOffreBadges(@PathVariable Long id) {
        try {
            Offre offre = offreService.findById(id);
            System.out.println("offre by "+offre);
            if (offre == null) {
                return ResponseEntity.notFound().build();
            }
            List<Badge> badges = new ArrayList<>(offre.getBadges());
            return ResponseEntity.ok(badges);
        } catch (Exception e) {
            System.err.println("Error fetching badges for offre " + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error fetching badges");
        }
    }

    @GetMapping("/{id}/themes")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> getOffreThemes(@PathVariable Long id) {
        try {
            Offre offre = offreService.findById(id);
            System.out.println("offre by"+offre);
            if (offre == null) {
                return ResponseEntity.notFound().build();
            }
            List<Theme> themes = new ArrayList<>(offre.getThemes());
            return ResponseEntity.ok(themes);
        } catch (Exception e) {
            System.err.println("Error fetching themes for offre " + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error fetching themes");
        }
    }

    @PostMapping(path = "/userFvrOffre")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> addFavoriteToOffre(@RequestBody UserFvrOffre userFvrOffre) {
        try {
            Offre offre = offreService.findById(userFvrOffre.getOffre().getId());
            if (offre == null) {
                return ResponseEntity.notFound().build();
            }
            offreService.addFavoriteToOffre(offre, userFvrOffre.getAppUser());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error adding favorite to offre: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error adding favorite");
        }
    }

    @DeleteMapping(path = "/userFvrOffre")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> removeFavoriteFromOffre(@RequestBody UserFvrOffre userFvrOffre) {
        try {
            Offre offre = offreService.findById(userFvrOffre.getOffre().getId());
            if (offre == null) {
                return ResponseEntity.notFound().build();
            }
            offreService.removeFavoriteFromOffre(offre, userFvrOffre.getAppUser());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error removing favorite from offre: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error removing favorite");
        }
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

class UserFvrOffre {
    private Offre offre;
    private AppUser appUser;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}