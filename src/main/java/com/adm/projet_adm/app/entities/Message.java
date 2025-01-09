package com.adm.projet_adm.app.entities;

import com.adm.projet_adm.security.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String contenu;
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("message-sender")
    @JoinColumn(name = "sender_id")
    private AppUser sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("message-receiver")
    @JoinColumn(name = "receiver_id")
    private AppUser receiver;


    public Message() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public AppUser getSender() {
        return sender;
    }

    public void setSender(AppUser sender) {
        this.sender = sender;
    }

    public AppUser getReceiver() {
        return receiver;
    }

    public void setReceiver(AppUser receiver) {
        this.receiver = receiver;
    }

    public Message(Long id, Date date, String contenu, Boolean status, AppUser sender, AppUser receiver) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }
}
