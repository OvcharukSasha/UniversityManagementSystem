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
public class TeacherView implements Serializable,Comparable<TeacherView>{
    public int id;
    public String name;
    public String surname;
    public String position;
    public String email;
    public Set<CourseView> courses=new TreeSet<>();

    public TeacherView() {
    }
    
    
    
    public void addCourse(CourseView course){
        if (course!=null) courses.add(course);
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
