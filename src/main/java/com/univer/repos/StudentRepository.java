/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Root
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Object>{
    @Query("SELECT u FROM Student u WHERE LOWER(u.name) like  %:name% or LOWER(u.surname) like %:name%")
    List<Student> findUser(String name);
}
