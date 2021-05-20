/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;

/**
 *
 * @author Root
 */
public enum RoleEnum {
    
    ROLE_ADMIN("ROLE_ADMIN"),ROLE_TEACHER("ROLE_TEACHER"),ROLE_STUDENT("ROLE_STUDENT");
    private String name;

    private RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    public static RoleEnum valueOfName(String name){
        for(RoleEnum role:RoleEnum.values()){
            if (role.name().equals(name)) return role;
        }
        return null;
    }

    

    
    
    
}
