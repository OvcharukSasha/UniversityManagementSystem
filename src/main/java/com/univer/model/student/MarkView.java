/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.student;

import com.univer.entity.CourseMark;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class MarkView implements Serializable{
    private int id;
    private LocalDate date;
    private int mark;

    public MarkView() {
    }

    public MarkView(CourseMark mark) {
       this.id = mark.getId();
       this.mark =mark.getMark();
       this.date = convertToLocalDate(mark.getDate());
    }
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        if (dateToConvert==null) return null;
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
     
}
