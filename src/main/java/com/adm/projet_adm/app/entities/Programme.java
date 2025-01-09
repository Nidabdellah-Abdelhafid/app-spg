package com.adm.projet_adm.app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Programme {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heure;
    private String label;
    private String description;

    @OneToMany(mappedBy = "programme_photos", fetch = FetchType.EAGER)
    @JsonBackReference("programme-photos")
    private Collection<Photo> photos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("programme-planing")  // This should be the back reference on the Programme side
    private Planing planing_programmes;


    public Programme() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Planing getPlaning_programmes() {
        return planing_programmes;
    }

    public void setPlaning_programmes(Planing planing_programmes) {
        this.planing_programmes = planing_programmes;
    }


    public Programme(Long id, Planing planing_programmes, Collection<Photo> photos, String description, String label, String heure) {
        this.id = id;
        this.planing_programmes = planing_programmes;
        this.photos = photos;
        this.description = description;
        this.label = label;
        this.heure = heure;
    }
}
