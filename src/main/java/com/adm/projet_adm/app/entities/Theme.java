package com.adm.projet_adm.app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Theme {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    /*
    @ManyToMany(mappedBy = "themes", fetch = FetchType.EAGER)
    @JsonBackReference("theme-offres")
    private Collection<Offre> offres = new ArrayList<>();
*/
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
