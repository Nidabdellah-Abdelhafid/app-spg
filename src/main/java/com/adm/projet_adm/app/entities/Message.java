package com.adm.projet_adm.app.entities;

import com.adm.projet_adm.security.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    @Column(length = 1000)
    private String contenu;
    
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    
    private String mediaUrl;
    
    private String mediaType;
    
    private String fileName;
    
    private Long fileSize;
    
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("message-sender")
    @JoinColumn(name = "sender_id")
    private AppUser sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("message-receiver")
    @JoinColumn(name = "receiver_id")
    private AppUser receiver;

    public enum MessageType {
        TEXT,
        IMAGE,
        DOCUMENT
    }

    // Add getters and setters for new fields
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    // Update constructor
    public Message(Long id, Date date, String contenu, MessageType messageType, 
                  String mediaUrl, String mediaType, String fileName, Long fileSize,
                  Boolean status, AppUser sender, AppUser receiver) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.messageType = messageType;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }

    // Default constructor
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
}
