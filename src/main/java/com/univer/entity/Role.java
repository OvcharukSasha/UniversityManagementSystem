/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Entity
@Table(name="Roles")
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @Column(name="role")        
    @Enumerated(EnumType.STRING)           
    RoleEnum role;

    @Column(name="login")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    List<User> users;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.role = role;
    }

    
    
}
