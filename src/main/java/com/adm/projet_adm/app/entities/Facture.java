package com.adm.projet_adm.app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String prixTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("facture-paiement")
    private Paiement paiement;

    @OneToOne()
    @JsonBackReference("facture-reservation")
    private Reservation reservation;

    public Facture() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(String prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Facture(Long id, String reference, String prixTotal, Paiement paiement, Reservation reservation) {
        this.id = id;
        this.reference = reference;
        this.prixTotal = prixTotal;
        this.paiement = paiement;
        this.reservation = reservation;
    }

}
