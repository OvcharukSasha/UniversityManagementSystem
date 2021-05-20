/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 *
 * @author Root
 */
public class BuilderMonth {
      public static MonthView getInstance(int m,int y){
       MonthView month = new MonthView(m, y);
       
       LocalDate firstDateOfMonth = LocalDate.of(y,m, 1);
       month.setFirstDay(firstDateOfMonth);
       int currMonth = firstDateOfMonth.getMonthValue();
       LocalDate lastDayOfMonth = firstDateOfMonth.withDayOfMonth(
                                firstDateOfMonth.getMonth().length(firstDateOfMonth.isLeapYear()));
       month.setLastDay(lastDayOfMonth);
        DayOfWeek dw = firstDateOfMonth.getDayOfWeek();
        if (dw !=DayOfWeek.MONDAY){
            while(firstDateOfMonth.getDayOfWeek()!=DayOfWeek.MONDAY){
                firstDateOfMonth = firstDateOfMonth.minusDays(1);
            }
        }
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(); 

        while(lastDayOfMonth.getDayOfWeek()!=DayOfWeek.SUNDAY){
                lastDayOfMonth = lastDayOfMonth.plusDays(1);
        }
        int startW = firstDateOfMonth.get(woy);
        Week week = new Week();
        for(LocalDate start = firstDateOfMonth;start.isBefore(lastDayOfMonth.plusDays(1) );start = start.plusDays(1)){
            if (startW!=start.get(woy)){
                month.getWeeks().add(week);
                week = new Week();
                startW = start.get(woy);
            }
            boolean disable = start.getMonthValue()!=currMonth;
            week.getDays().add(new Day(start, disable,false));
        }
        month.getWeeks().add(week);
        return month;
        
    }
    
}
