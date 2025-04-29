package com.adm.projet_adm.app.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Planing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private int jourNumero;
    private String mapPlaningImage;

    @OneToMany(mappedBy = "planing_programmes", fetch = FetchType.LAZY)
    @JsonBackReference("planing-programme")
    private Collection<Programme> programmes = new ArrayList<>();


    @OneToMany(mappedBy = "planing_photos", fetch = FetchType.EAGER)
    @JsonBackReference("planing-photos")
    private Collection<Photo> photos;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("planing-offre")
    private Offre offre_planings;


    public Planing() {

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

    public int getJourNumero() {
        return jourNumero;
    }

    public void setJourNumero(int jourNumero) {
        this.jourNumero = jourNumero;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Collection<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(Collection<Programme> programmes) {
        this.programmes = programmes;
    }

    public Offre getOffre() {
        return offre_planings;
    }

    public void setOffre(Offre offre) {
        this.offre_planings = offre;
    }

    public String getMapPlaningImage() {
        return mapPlaningImage;
    }

    public void setMapPlaningImage(String mapPlaningImage) {
        this.mapPlaningImage = mapPlaningImage;
    }

    // Update constructor
    public Planing(Long id, String label, String description, int jourNumero, 
                  String mapPlaningImage, Collection<Photo> photos, 
                  Collection<Programme> programmes, Offre offre_planings) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.jourNumero = jourNumero;
        this.mapPlaningImage = mapPlaningImage;
        this.photos = photos;
        this.programmes = programmes;
        this.offre_planings = offre_planings;
    }
}
