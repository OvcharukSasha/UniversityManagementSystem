/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.repos;

import com.univer.entity.AttendanceData;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Root
 */
public interface AttendanceDataRepository extends JpaRepository<AttendanceData,Integer>{
    @Query("select a from AttendanceData a where a.uidTag=:uidTag and date between :start and :end")
    List<AttendanceData> findByUidAndDate(String uidTag,Date start,Date end);
      @Query("select a from AttendanceData a where a.uidTag=:uidTag and date=:start")
    AttendanceData findOneByUidAndDate(String uidTag,Date start);
    
}
