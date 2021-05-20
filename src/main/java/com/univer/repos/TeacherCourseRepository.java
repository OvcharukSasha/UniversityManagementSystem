/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.Teacher;
import com.univer.entity.TeacherCourse;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Root
 */
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Integer>{
    @Query("select t from TeacherCourse t where t.teacher = :teacher")
    Set<TeacherCourse> findByTeacher(Teacher teacher);
    
    
}
