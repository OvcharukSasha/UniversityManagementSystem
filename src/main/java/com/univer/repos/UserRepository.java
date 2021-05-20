/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.Role;
import com.univer.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Root
 */
@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{
    @Query("select u From User u where u.role = :role and u.login = :login")
    Optional<User> findUserByRoleAndLogin(Role role,String login);
    
    @Query("select u From User u where u.login = :login")
    Optional<User> findByLogin(String login);
    
    @Query("select u From User u where u.login like :login%")
    List<User> findByLikeLogin(String login);
    
    @Query("select u From User u where u.role = :role")
    List<User> findUserByRole(Role role);

    
}
