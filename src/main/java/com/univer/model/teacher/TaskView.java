/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.model.teacher;

import com.univer.entity.CourseTask;
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
public class TaskView implements Serializable, Cloneable, Comparable<TaskView> {

    private int id;
    private int maxMark;
    private String info;
    private LocalDate startDate;
    private LocalDate deadline;
    private MarkView mark;

    private boolean delete;

    public TaskView() {
    }

    public TaskView(CourseTask task) {
        this.id = task.getId();
        this.info = task.getInfo();
        this.deadline = convertToLocalDate(task.getDeadline());
        this.startDate = convertToLocalDate(task.getStartDate());
        this.maxMark = task.getMaxMark();
        this.delete=true;
        if (task.getCourseMarks().size()>0) this.delete=false;
    }
    

    public TaskView(int id,String info, int maxMark, LocalDate startDate, LocalDate deadline, MarkView mark) {
        this.id = id;
        this.info =info; 
        this.maxMark = maxMark;
        this.startDate = startDate;
        this.deadline = deadline;
        this.mark = mark;
    }

    @Override
    public int compareTo(TaskView o) {
        if (o == null) {
            return 1;
        }
        if (startDate.equals(o.getStartDate())) {
            if (deadline.equals(o.getDeadline())) {
                return info.compareTo(o.getInfo());
            } else {
                return deadline.compareTo(o.getDeadline());
            }
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

    protected TaskView clone() {
        return new TaskView(id, info, maxMark, startDate, deadline, mark);
   }
     public static LocalDate convertToLocalDate(Date dateToConvert) {
        if (dateToConvert==null) return null;
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
