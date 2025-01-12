package com.adm.projet_adm.app.entities;


import com.adm.projet_adm.security.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Pays {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String description;
    private String continent;
    private Float latitude;
    private Float longitude;
    private String label;
    private int reviews;

    @OneToMany(mappedBy = "pays_offres", fetch = FetchType.EAGER)
    @JsonBackReference("pays-offres")
    private Collection<Offre> offres = new ArrayList<>();

    @OneToMany(mappedBy = "pays_photos", fetch = FetchType.LAZY)
    @JsonBackReference("pays-photos")
    private Collection<Photo> photos;

    @ManyToMany(mappedBy = "pays_favs", fetch = FetchType.LAZY)
    @JsonBackReference("pays-appUsers")
    private Collection<AppUser> appUsers;


    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }


    public Pays() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Collection<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Collection<Offre> offres) {
        this.offres = offres;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Collection<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Collection<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pays(String description, Long id,String image, String continent, Float latitude, Float longitude, String label, int reviews, Collection<Offre> offres, Collection<Photo> photos, Collection<AppUser> appUsers) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.reviews = reviews;
        this.offres = offres;
        this.photos = photos;
        this.appUsers = appUsers;
    }

}
