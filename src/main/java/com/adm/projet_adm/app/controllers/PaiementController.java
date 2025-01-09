package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Paiement;
import com.adm.projet_adm.app.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Paiement create(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Paiement> getAll() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Paiement getById(@PathVariable Long id) {
        return paiementService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement paiement) {
        return paiementService.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        paiementService.deleteById(id);
    }
}
