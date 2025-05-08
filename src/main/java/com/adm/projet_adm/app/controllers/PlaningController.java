package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Planing;
import com.adm.projet_adm.app.repositories.OffreRepository;
import com.adm.projet_adm.app.services.PlaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/planings")
public class PlaningController {

    @Autowired
    private PlaningService planingService;

    @Autowired
    private OffreRepository offreRepository;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Planing create(@RequestBody Planing planing) {
        if (planing.getOffre() == null ) {
            throw new IllegalArgumentException("All required fields must be filled.");
        }

        Offre offre = offreRepository.findOffreById(planing.getOffre().getId());
        planing.setOffre(offre);

        return planingService.save(planing);
    }

    @GetMapping
    // @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<Planing> planings = planingService.findAll();
        List<Map<String, Object>> response = planings.stream().map(planing -> {
            Map<String, Object> planingMap = new HashMap<>();
            planingMap.put("id", planing.getId());
            planingMap.put("label", planing.getLabel());
            planingMap.put("description", planing.getDescription());
            planingMap.put("jourNumero", planing.getJourNumero());
            planingMap.put("mapPlaningImage", planing.getMapPlaningImage());
            planingMap.put("planing_programmes", planing.getProgrammes());
            planingMap.put("photos", planing.getPhotos());
            planingMap.put("offre", planing.getOffre());
            return planingMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Planing getById(@PathVariable Long id) {
        return planingService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Planing update(@PathVariable Long id, @RequestBody Planing planing) {
        return planingService.update(id, planing);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        planingService.deleteById(id);
    }
}
