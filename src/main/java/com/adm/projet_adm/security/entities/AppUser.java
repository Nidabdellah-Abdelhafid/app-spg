package com.adm.projet_adm.security.entities;

import com.adm.projet_adm.app.entities.Message;
import com.adm.projet_adm.app.entities.Notification;
import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullname;

    @Column(unique = true)
    @NotNull
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Lob
    private byte[] userPhoto;

    private String telephone;
    private String pays;

    @ManyToMany(fetch = FetchType.EAGER )
    private Collection<AppRole> appRoles = new ArrayList<>();

    @OneToMany(mappedBy = "appUser_reservations")
    @JsonBackReference("appUser-reservation")
    private Collection<Reservation> reservations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference("appUser-notification")
    private Collection<Notification> notifications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference("appUser-pays")
    private Collection<Pays> pays_favs = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    @JsonBackReference("appUser-sentMessages")
    private Collection<Message> sentMessages = new ArrayList<>(); // Messages where the user is the sender

    @OneToMany(mappedBy = "receiver")
    @JsonBackReference("appUser-receivedMessages")
    private Collection<Message> receivedMessages = new ArrayList<>(); // Messages where the user is the receiver

    public AppUser() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Collection<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    public Collection<Pays> getPays_favs() {
        return pays_favs;
    }

    public void setPays_favs(Collection<Pays> pays_favs) {
        this.pays_favs = pays_favs;
    }

    public Collection<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(Collection<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public Collection<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Collection<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public AppUser(long id, String fullname, String email, String password, byte[] userPhoto, String telephone, String pays, Collection<AppRole> appRoles, Collection<Reservation> reservations, Collection<Notification> notifications, Collection<Pays> pays_favs, Collection<Message> sentMessages, Collection<Message> receivedMessages) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.userPhoto = userPhoto;
        this.telephone = telephone;
        this.pays = pays;
        this.appRoles = appRoles;
        this.reservations = reservations;
        this.notifications = notifications;
        this.pays_favs = pays_favs;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
    }
}
