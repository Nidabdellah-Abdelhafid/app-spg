package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Badge create(@RequestBody Badge badge) {
        return badgeService.save(badge);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Badge> getAll() {
        return badgeService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Badge getById(@PathVariable Long id) {
        return badgeService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Badge update(@PathVariable Long id, @RequestBody Badge badge) {
        return badgeService.update(id, badge);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        badgeService.deleteById(id);
    }
}
