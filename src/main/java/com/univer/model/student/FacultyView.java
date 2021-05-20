/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.student;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class FacultyView implements Serializable,Comparable<FacultyView>{
    String name;
    Set<TeamView> teams = new TreeSet<>();

    public FacultyView() {
    }
    
    public void addTeam(TeamView team){
        if (team==null) return;
        this.teams.add(team);
                
    }

    @Override
    public int compareTo(FacultyView o) {
       if (o==null) return 1;
       return name.compareTo(o.getName());
    }
}
