package com.adm.projet_adm.app.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("photo-pays")
    private Pays pays_photos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("photo-planing")
    private Planing planing_photos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("photo-programme")
    private Programme programme_photos;


    public Photo() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Pays getPays() {
        return pays_photos;
    }

    public void setPays(Pays pays) {
        this.pays_photos = pays;
    }

    public Planing getPlaning() {
        return planing_photos;
    }

    public void setPlaning(Planing planing) {
        this.planing_photos = planing;
    }

    public Programme getProgramme() {
        return programme_photos;
    }

    public void setProgramme(Programme programme) {
        this.programme_photos = programme;
    }

    public Photo(Long id, String url, Pays pays_photos, Planing planing_photos, Programme programme_photos) {
        this.id = id;
        this.url = url;
        this.pays_photos = pays_photos;
        this.planing_photos = planing_photos;
        this.programme_photos = programme_photos;
    }
}
