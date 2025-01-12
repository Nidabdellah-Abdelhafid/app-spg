package com.adm.projet_adm.app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Offre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private Float price;
    private String image;
    private Float latitude;
    private Float longitude;

    @OneToMany(mappedBy = "offre_planings", fetch = FetchType.LAZY)
    @JsonBackReference("offre-planings")
    private Collection<Planing> planings;

    @OneToMany(mappedBy = "offre_reservations", fetch = FetchType.LAZY)
    @JsonBackReference("offre-reservations")
    private Collection<Reservation> reservations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("offre-pays")
    private Pays pays_offres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference("offre-themes")
    private Collection<Theme> themes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference("offre-badges")
    private Collection<Badge> badges = new ArrayList<>();

    public Offre() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Collection<Planing> getPlanings() {
        return planings;
    }

    public void setPlanings(Collection<Planing> planings) {
        this.planings = planings;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Pays getPays() {
        return pays_offres;
    }

    public void setPays(Pays pays) {
        this.pays_offres = pays;
    }

    public Collection<Theme> getThemes() {
        return themes;
    }

    public void setThemes(Collection<Theme> themes) {
        this.themes = themes;
    }

    public Collection<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Collection<Badge> badges) {
        this.badges = badges;
    }

    public Offre(Long id, String label, String description, Float price, String image, Float latitude, Float longitude, Collection<Planing> planings, Collection<Reservation> reservations, Pays pays_offres, Collection<Theme> themes, Collection<Badge> badges) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.price = price;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.planings = planings;
        this.reservations = reservations;
        this.pays_offres = pays_offres;
        this.themes = themes;
        this.badges = badges;
    }
}
