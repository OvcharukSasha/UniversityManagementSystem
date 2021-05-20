/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.student;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class TeacherView implements Serializable,Comparable<TeacherView>{
    int id;
    String name;
    String surname;
    String position;
    String email;
    
        

    public TeacherView() {
    }

    @Override
    public int compareTo(TeacherView o) {
        if (o==null) return 1;
        if (surname.equals(o.getSurname())) return surname.compareTo(o.getSurname());
        return name.compareTo(o.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeacherView other = (TeacherView) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
