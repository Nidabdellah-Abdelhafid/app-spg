package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Theme;
import com.adm.projet_adm.app.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Theme create(@RequestBody Theme theme) {
        return themeService.save(theme);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Theme> getAll() {
        return themeService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Theme getById(@PathVariable Long id) {
        return themeService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Theme update(@PathVariable Long id, @RequestBody Theme theme) {
        return themeService.update(id, theme);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        themeService.deleteById(id);
    }

}
