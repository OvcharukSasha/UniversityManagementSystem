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
public class MarkForm {
    int taskId;
    int studentId;
    String studentName;
    String taskInfo;
    int teamId;
    int courseId;
    int mark;

    public MarkForm() {
    }
}
