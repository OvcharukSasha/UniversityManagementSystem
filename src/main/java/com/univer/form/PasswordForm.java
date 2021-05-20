/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.form;

import com.univer.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForm {
    boolean old = true;
    private String login;
    private String password;
    private String confirmPassword;
    private String role;

    public PasswordForm(String login) {
        this.login = login;
    }

    public PasswordForm() {
    }
    public PasswordForm(User user) {
        this.login = user.getLogin();
        this.role = user.getRole().getRole().getName();
    }
    
    
    
}
