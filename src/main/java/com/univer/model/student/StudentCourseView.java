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
public class StudentCourseView implements Serializable {
    public Set<CourseView> courses = new TreeSet<>();

    public StudentCourseView() {
    }
    public void addCoures(CourseView course){
        if (course==null)  return;
        this.courses.add(course);
    }
    
    
    
    
}
