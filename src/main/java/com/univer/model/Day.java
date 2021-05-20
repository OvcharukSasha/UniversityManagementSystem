/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class Day {
     int day;
    boolean selected;
    boolean disabled;
    LocalDate date;
    String css="day";
    
        

    public Day() {
    }

    public Day(LocalDate date,boolean disabled,boolean selected) {
        this.disabled = disabled;
        this.date = date;
        this.day = date.getDayOfMonth();
        if (disabled) css="disabled";
        setSelected(selected);
        if (date.getDayOfWeek()==DayOfWeek.SUNDAY || date.getDayOfWeek()==DayOfWeek.SATURDAY){
            css=css+" holiday";
        }
    }
    
    public void setSelected(boolean selected){
        this.selected = selected;
        if (selected) {
             css=css+" selected";
        }
    }
}
