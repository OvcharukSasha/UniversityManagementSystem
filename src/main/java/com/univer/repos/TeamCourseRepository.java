/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.TeamCourse;
import java.util.Set;
import com.univer.entity.Course;
import com.univer.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Root
 */
@Repository
public interface TeamCourseRepository extends JpaRepository<TeamCourse,Integer>{
    @Query("select t from TeamCourse t where t.course = :course")
    Set<TeamCourse> findByCourse(Course course);
    @Query("select t from TeamCourse t where t.team = :team and t.course = :course")
    Set<TeamCourse> findByTeamAndCourse(Team team, Course course);
}
