/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.validation;

import com.univer.form.PasswordForm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 *
 * @author Root
 */
@Component
public class PasswordValidator implements Validator{
    private  final String PASSWORD_PATTERN = "((?=.*[a-zA-Z])(?=.*\\d).{6,40})";
    Pattern pattern;
    Matcher matcher;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == PasswordForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passsForm = (PasswordForm) target;
    
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.passForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.passForm.confirmPassword");
        
        
         if (!errors.hasErrors()) {
            if (!passsForm.getConfirmPassword().equals(passsForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.userForm.confirmPassword");
            }
        }
        if (!passsForm.getPassword().isEmpty() && !validate(PASSWORD_PATTERN, passsForm.getPassword())) {
                errors.rejectValue("password", "Match.userForm.password");
            } 
         
    }
     private boolean validate(String pat, String str) {
        pattern = Pattern.compile(pat);
        matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    
}


