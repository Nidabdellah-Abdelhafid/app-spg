package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.services.PaysService;
import com.adm.projet_adm.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pays")
public class PaysController {

    @Autowired
    private PaysService paysService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Pays create(@RequestBody Pays pays) {
        return paysService.save(pays);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Pays> getAll() {
        return paysService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Pays getById(@PathVariable Long id) {
        return paysService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Pays update(@PathVariable Long id, @RequestBody Pays pays) {
        return paysService.update(id, pays);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        paysService.deleteById(id);
    }

    @PostMapping(path = "/userFvrPays")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void addFavoriteToPays(@RequestBody UserFvrPays userFvrPays) {
        System.out.println("theme to offre"+userFvrPays);
        paysService.addFavoriteToPays(userFvrPays.getPays(),userFvrPays.getAppUser());
    }

}
class UserFvrPays {
    private Pays pays;
    private AppUser appUser;

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}