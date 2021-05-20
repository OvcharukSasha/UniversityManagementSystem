/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.Faculty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Root
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>{
    @Query(value="SELECT DISTINCT f.* FROM Faculty f join Team t on t.faculty_id = f.id " +
        "join TeamCourse tc on tc.idTeam = t.id join TeacherCourse ts on ts.idCourse = tc.idCourse" +
         " where ts.idTeacher = :idTeacher",nativeQuery = true)
    List<Faculty> findFacultyByTeacher(int idTeacher);
}
