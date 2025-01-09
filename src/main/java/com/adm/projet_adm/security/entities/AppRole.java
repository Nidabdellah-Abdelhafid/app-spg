package com.adm.projet_adm.security.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the id
    private Long id;
    private String roleName;

    public AppRole() {
        // This can be empty or initialize default values
    }

    public AppRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
