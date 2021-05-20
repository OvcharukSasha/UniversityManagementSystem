/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.CourseMark;
import com.univer.entity.CourseTask;
import com.univer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Root
 */
@Repository
public interface CourseMarkRepository extends JpaRepository<CourseMark,Integer>{
    @Query("select c from CourseMark c where c.courseTask=:courseTask and c.student=:student" )
    CourseMark findByTaskAndStudent(CourseTask courseTask,Student student);
}
