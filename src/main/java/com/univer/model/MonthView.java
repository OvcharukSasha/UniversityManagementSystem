/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class MonthView {
    int month;
    int year;
    String info;
    LocalDate firstDay;
    LocalDate lastDay;
    int studentId;
    String fullName;
    String phone;
    String email;
    String team;
    String faculty;
    
    
    static String[] months = {"січень","лютий","березень","квітень","травень",
    "червень","липень","серпень","вересень","жовтень","листопад","грудень"};
    
    Set<Week> weeks = new LinkedHashSet<>();

    public MonthView(int month,int year) {
        this.month = month;
        this.year = year;
        this.info = String.format("%s, %d", months[month-1],year);
    }
    
}
