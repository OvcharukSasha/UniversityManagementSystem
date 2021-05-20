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
public class TeamView implements Serializable,Comparable<TeamView>{
    int id;
    String name;

    @Override
    public int compareTo(TeamView o) {
        if (o==null) return 1;
        return name.compareTo(o.getName());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
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
        final TeamView other = (TeamView) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
