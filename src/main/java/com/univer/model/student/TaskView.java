/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.student;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class TaskView implements Serializable, Comparable<TaskView>{
    private int id;
    private int maxMark;
    private String info="";
    private LocalDate startDate;
    private LocalDate deadline;
    private MarkView mark ;

    public TaskView() {
    }

    @Override
    public int compareTo(TaskView o) {
        if (o==null) return 1;
        if (startDate.equals(o.getStartDate())) {
            if (deadline.equals(o.getDeadline())) return info.compareTo(o.getInfo());
            else return deadline.compareTo(o.getDeadline());
        }
        return startDate.compareTo(o.getStartDate());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskView other = (TaskView) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
