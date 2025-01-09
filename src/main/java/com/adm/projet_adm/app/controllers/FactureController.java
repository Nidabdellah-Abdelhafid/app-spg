package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Facture;
import com.adm.projet_adm.app.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Facture create(@RequestBody Facture facture) {
        return factureService.save(facture);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Facture> getAll() {
        return factureService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Facture getById(@PathVariable Long id) {
        return factureService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Facture update(@PathVariable Long id, @RequestBody Facture facture) {
        return factureService.update(id, facture);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        factureService.deleteById(id);
    }
}
