package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findReservationById(long id);
}
