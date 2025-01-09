package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.entities.Planing;
import com.adm.projet_adm.app.entities.Programme;
import com.adm.projet_adm.app.repositories.PlaningRepository;
import com.adm.projet_adm.app.services.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Programme> getAll() {
        return programmeService.findAll();
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
