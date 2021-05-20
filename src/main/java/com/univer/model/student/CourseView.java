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
public class CourseView implements Serializable, Comparable<CourseView>{
    int id;
    String name;
    String info;
    
    public Set<TeacherView> teachers = new TreeSet<>();
    public Set<TaskView> tasks = new TreeSet<>();
    
   
    

    
    public CourseView() {
    }
    
    public TaskView getFirst(){
        return tasks.iterator().next();
    }

    @Override
    public int compareTo(CourseView o) {
        if (o==null) return 1;
        if (name.equals(o.getName())) return info.compareTo(o.getInfo());
        return name.compareTo(o.getName());
    }
    
    public void addTeacher(TeacherView techer){
        if (techer==null) return;
        this.teachers.add(techer);
    }
    public void addTask(TaskView task){
        if (task==null) return;
        this.tasks.add(task);
    }
    public int totalMark(){
        int mark = 0;
        for(TaskView task:tasks){
            if (task.getMark()!=null) mark+=task.getMark().getMark();
        }
        return  mark;
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
