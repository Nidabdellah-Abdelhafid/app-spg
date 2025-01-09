package com.adm.projet_adm.app.entities;

import java.util.Date;


import com.adm.projet_adm.security.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private int nb_voyageurs_adultes;
    private int nb_voyageurs_enfants;
    private String pourquois_voyagez_vous;
    private Date date_depart;
    private Date date_retour;
    private int duree_int;
    private Boolean duree_modifiable;
    private String categorie_hebergement;
    private String cabine;
    private String experience_souhaitez;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("reservation-offre")
    private Offre offre_reservations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("reservation-appUser")
    private AppUser appUser_reservations;

    @OneToOne(mappedBy = "reservation")
    @JsonBackReference("reservation-facture")
    private Facture facture;


    public Reservation() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNb_voyageurs_adultes() {
        return nb_voyageurs_adultes;
    }

    public void setNb_voyageurs_adultes(int nb_voyageurs_adultes) {
        this.nb_voyageurs_adultes = nb_voyageurs_adultes;
    }

    public int getNb_voyageurs_enfants() {
        return nb_voyageurs_enfants;
    }

    public void setNb_voyageurs_enfants(int nb_voyageurs_enfants) {
        this.nb_voyageurs_enfants = nb_voyageurs_enfants;
    }

    public String getPourquois_voyagez_vous() {
        return pourquois_voyagez_vous;
    }

    public void setPourquois_voyagez_vous(String pourquois_voyagez_vous) {
        this.pourquois_voyagez_vous = pourquois_voyagez_vous;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public int getDuree_int() {
        return duree_int;
    }

    public void setDuree_int(int duree_int) {
        this.duree_int = duree_int;
    }

    public Boolean getDuree_modifiable() {
        return duree_modifiable;
    }

    public void setDuree_modifiable(Boolean duree_modifiable) {
        this.duree_modifiable = duree_modifiable;
    }

    public String getCategorie_hebergement() {
        return categorie_hebergement;
    }

    public void setCategorie_hebergement(String categorie_hebergement) {
        this.categorie_hebergement = categorie_hebergement;
    }

    public String getCabine() {
        return cabine;
    }

    public void setCabine(String cabine) {
        this.cabine = cabine;
    }

    public String getExperience_souhaitez() {
        return experience_souhaitez;
    }

    public void setExperience_souhaitez(String experience_souhaitez) {
        this.experience_souhaitez = experience_souhaitez;
    }

    public Offre getOffre_reservations() {
        return offre_reservations;
    }

    public void setOffre_reservations(Offre offre_reservations) {
        this.offre_reservations = offre_reservations;
    }

    public AppUser getAppUser() {
        return appUser_reservations;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser_reservations = appUser;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Reservation(Long id, String destination, int nb_voyageurs_adultes, int nb_voyageurs_enfants, String pourquois_voyagez_vous, Date date_depart, Date date_retour, int duree_int, Boolean duree_modifiable, String categorie_hebergement, String cabine, String experience_souhaitez, Offre offre_reservations, AppUser appUser_reservations, Facture facture) {
        this.id = id;
        this.destination = destination;
        this.nb_voyageurs_adultes = nb_voyageurs_adultes;
        this.nb_voyageurs_enfants = nb_voyageurs_enfants;
        this.pourquois_voyagez_vous = pourquois_voyagez_vous;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.duree_int = duree_int;
        this.duree_modifiable = duree_modifiable;
        this.categorie_hebergement = categorie_hebergement;
        this.cabine = cabine;
        this.experience_souhaitez = experience_souhaitez;
        this.offre_reservations = offre_reservations;
        this.appUser_reservations = appUser_reservations;
        this.facture = facture;
    }
}
