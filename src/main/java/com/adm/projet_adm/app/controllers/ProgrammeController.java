package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Planing;
import com.adm.projet_adm.app.entities.Programme;
import com.adm.projet_adm.app.repositories.PlaningRepository;
import com.adm.projet_adm.app.services.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/programmes")
public class ProgrammeController {

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private PlaningRepository planingRepository;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Programme create(@RequestBody Programme programme) {
        if (programme.getPlaning_programmes() == null ) {
            throw new IllegalArgumentException("All required fields must be filled.");
        }

        Planing planing = planingRepository.findPlaningById(programme.getPlaning_programmes().getId());
        programme.setPlaning_programmes(planing);

        return programmeService.save(programme);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<Programme> programmes = programmeService.findAll();
        List<Map<String, Object>> response = programmes.stream().map(programme -> {
            Map<String, Object> programmeMap = new HashMap<>();
            programmeMap.put("id", programme.getId());
            programmeMap.put("label", programme.getLabel());
            programmeMap.put("heure", programme.getHeure());
            programmeMap.put("description", programme.getDescription());
            programmeMap.put("planing_programmes", programme.getPlaning_programmes());
            return programmeMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Programme getById(@PathVariable Long id) {
        return programmeService.findById(id);
    }

    @GetMapping("/details")
    public List<Programme> getProgrammeWithDetails() {
        return programmeService.getProgrammeWithDetails();
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Programme update(@PathVariable Long id, @RequestBody Programme programme) {
        return programmeService.update(id, programme);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        programmeService.deleteById(id);
    }
}
