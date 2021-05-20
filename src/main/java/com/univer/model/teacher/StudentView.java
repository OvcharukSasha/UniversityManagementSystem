/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.teacher;


import com.univer.entity.Student;
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
public class StudentView implements Comparable<StudentView>{
    int id;
    String name;
    String surname;
    String team;
    String faculty;
    String phoneNumber;
    String email;
    Set<TaskView> tasks = new TreeSet<>();
    

    public StudentView() {
    }
    
    public String fullName(){
        return String.format("%s %s " ,surname==null?"":surname,name==null?"":name);
    }

    public StudentView(Student student) {
        this.id=student.getId();
        this.name=student.getName();
        this.surname= student.getSurname();
        this.phoneNumber = student.getPhoneNumber();
        this.email = student.getEmail();
        
    }
    
    public String totalMark(){
        int sum =0;
        for(TaskView task:tasks){
            if(task.getMark()!=null) sum+=task.getMark().getMark();
        }
        return sum==0?"":String.format("%d",sum);
    }
    
    
    public void addTask(TaskView taskView){
        if (taskView!=null) this.tasks.add(taskView);
    }

    @Override
    public int compareTo(StudentView o) {
        if (o==null) return 1;
        if (surname.equals(o.getSurname())) return name.compareTo(o.getName());
        return surname.compareTo(o.getSurname());
    }
    
    
    
    
}
