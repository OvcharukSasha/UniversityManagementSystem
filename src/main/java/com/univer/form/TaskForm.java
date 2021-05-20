/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.form;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class TaskForm {
    private Integer id;
    private int maxMark;
    private String info="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    public TaskForm() {
    }

    @Override
    public String toString() {
        return "TaskForm{" + "id=" + id + ", maxMark=" + maxMark + ", info=" + info + ", startDate=" + startDate + ", deadline=" + deadline + '}';
    }
    
    
}
