/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.teacher;

import com.univer.entity.CourseTask;
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
public class StudentMarkView {
    int teamId;
    String team;
    int courseId;
    String course;
    Set<TaskView> tasks = new TreeSet<>();
    Set<StudentView> students = new TreeSet<>();

    public StudentMarkView() {
    }
    public void addTask(TaskView task){
        this.tasks.add(task);
    }
    public void addTask(CourseTask task){
        this.tasks.add(new TaskView(task));
    }
    public StudentView addStudent(Student student){
        if (student==null) return null;
        StudentView studentView = new StudentView(student);
        for(TaskView task:tasks){
            studentView.addTask(task.clone());
        }
        this.students.add(studentView);
        return studentView;
    }
    
}
