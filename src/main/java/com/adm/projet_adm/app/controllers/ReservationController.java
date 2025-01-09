package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Reservation;
import com.adm.projet_adm.app.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Reservation> getAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Reservation getById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
