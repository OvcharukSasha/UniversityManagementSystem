/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.teacher;


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
public class TeamView implements Serializable,Comparable<TeamView>{
    int id;
    String name = "";
    CourseView course;

    Set<StudentView> students = new TreeSet<>();
    Set<TaskView> tasks = new TreeSet<>();
    
    

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

    @Override
    public String toString() {
        return "TeamView{" + "id=" + id + ", name=" + name + ", course=" + course + ", students=" + students + ", tasks=" + tasks + '}';
    }

    
    
    
    
    
}
