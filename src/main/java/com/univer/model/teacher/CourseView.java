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
 * курси вчителя
 */
@Getter
@Setter
public class CourseView implements Serializable, Comparable<CourseView>{
    int id;
    String name;
    String info;
    
    public Set<FacultyView> faculteis = new TreeSet<>(); 
    
    public CourseView() {
    }
    public FacultyView getFirst(){
        return faculteis.iterator().next();
    }
    
    
    public int sizeFaculty(){
        if (faculteis.isEmpty()) return 0;
        int size = 0;
        for(FacultyView f:faculteis){
            size+=f.sizeTeam();
        }
        //size +=faculteis.size();
        if (size==0)size=1;
        return size;
    }
    
    public void addFaculty(FacultyView faculty){
        if (faculty!=null) faculteis.add(faculty);
    }

    @Override
    public int compareTo(CourseView o) {
        if (o==null) return 1;
        if (name.equals(o.getName())) return info.compareTo(o.getInfo());
        return name.compareTo(o.getName());
    }
    
   
   

    @Override
    public int hashCode() {
        int hash = 3;
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
        final CourseView other = (CourseView) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
