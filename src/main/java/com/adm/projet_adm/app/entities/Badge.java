package com.adm.projet_adm.app.entities;

import javax.persistence.*;


@Entity
public class Badge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String label;

    public Badge() {

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


    public Badge(Long id, String label) {
        this.id = id;
        this.label = label;
    }


}
