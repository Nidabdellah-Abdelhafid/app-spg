package com.adm.projet_adm.app.entities;

import com.adm.projet_adm.security.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity
@Data
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date date;
    private boolean status;

    @ManyToMany(mappedBy = "notifications", fetch = FetchType.EAGER)
    @JsonBackReference("notification-appUsers")
    private Collection<AppUser> appUsers = new ArrayList<>();

    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Collection<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public Notification(Long id, String content, Date date, boolean status, Collection<AppUser> appUsers) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.status = status;
        this.appUsers = appUsers;
    }
}
