/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.form;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Root
 */
@Getter
@Setter
public class DeleteTaskForm {
    int teamId;
    int idCourse;
    int taskId;
    String taskInfo;

    public DeleteTaskForm() {
    }

    @Override
    public String toString() {
        return "DeleteTaskForm{" + "teamId=" + teamId + ", idCourse=" + idCourse + ", taskId=" + taskId + ", taskInfo=" + taskInfo + '}';
    }
    
    
    
    
}
