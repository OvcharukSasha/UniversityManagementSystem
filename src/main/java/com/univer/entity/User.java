/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Entity
@Table(name="Users")
@Inheritance(
    strategy = InheritanceType.JOINED
)
@Getter
@Setter
public class User implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
    
  @Column(name="login",length = 100,nullable = false)
  public String login;
  
  @Column(name="password",length = 128,nullable = false)
  public String password;
  
  
  
   @ManyToOne
   @JoinColumn(name = "role",nullable = false)
   private Role role;


    public User() {
    }
  
  
  
}

