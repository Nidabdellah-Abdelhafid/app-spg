package com.adm.projet_adm.app.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String typePaiement;
    private Date date;

    @OneToMany(mappedBy = "paiement", fetch = FetchType.EAGER)
    @JsonBackReference("paiement-factures")
    private Collection<Facture> factures = new ArrayList<>();
    public Paiement() {

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

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<Facture> getFactures() {
        return factures;
    }

    public void setFactures(Collection<Facture> factures) {
        this.factures = factures;
    }

    public Paiement(Long id, String reference, String typePaiement, Date date, Collection<Facture> factures) {
        this.id = id;
        this.reference = reference;
        this.typePaiement = typePaiement;
        this.date = date;
        this.factures = factures;
    }
}
