package com.adm.projet_adm.app.entities;

import javax.persistence.*;

@Entity
public class Theme {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String label;
    
    public Theme() {

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



    public Theme(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
