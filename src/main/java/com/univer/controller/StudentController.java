/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.controller;

import com.univer.form.PasswordForm;
import com.univer.model.MonthView;
import com.univer.model.student.StudentCourseView;
import com.univer.model.student.StudentView;
import com.univer.model.student.TeacherView;
import com.univer.service.StudentService;
import com.univer.validation.PasswordValidator;
import java.security.Principal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Root
 * відповідає за маршрутизацію для студента
 */
@Controller
public class StudentController {

    @Autowired
    StudentService service;
    
    @Autowired
    private PasswordValidator passwordValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        
        if (target.getClass() == PasswordForm.class) {
            dataBinder.setValidator(passwordValidator);
        }


    }
    // домашня сторінка
    @GetMapping("/student")
    public String index(Model model, Principal principal) {
        StudentView view = service.getStudentView(principal.getName());
        model.addAttribute("student", view);
        return "/student/index";
    }

    // сторінка відвідування
    @GetMapping("/student/visit")
    public String visit(Model model, Principal principal) {
        LocalDate now = LocalDate.now();
        int m = now.getMonthValue();
        int y = now.getYear();
        String login = principal.getName();
        MonthView month = service.getMonth(y, m, login);
        model.addAttribute("calendar", month);
        return "/student/visit";
    }
    // "перелистування відвідування вперед на 1 місяць
    @GetMapping("/student/visit/{y}/{m}/add")
    public String visitAdd(Model model, Principal principal, @PathVariable int y, @PathVariable int m) {
        m++;
        if (m > 12) {
            m = 1;
            y++;
        }
        String login = principal.getName();
        MonthView month = service.getMonth(y, m, login);
        model.addAttribute("calendar", month);
        return "/student/visit";
    }

    // "перелистування відвідування назад на 1 місяць
    @GetMapping("/student/visit/{y}/{m}/minus")
    public String visitMinus(Model model, Principal principal, @PathVariable int y, @PathVariable int m) {
        m--;
        if (m <= 0) {
            m = 12;
            y--;
        }
        String login = principal.getName();
        MonthView month = service.getMonth(y, m, login);
        model.addAttribute("calendar", month);
        return "/student/visit";
    }

    // предмети студента
    @GetMapping("/student/course")
    public String course(Model model, Principal principal) {
        StudentCourseView view = service.getStudentCourses(principal.getName());
        model.addAttribute("view", view);
        return "/student/course";
    }

    // отримування форми скидання паролю
    @GetMapping("/student/resetPassword")
    public String ressetPassword(Model model, Principal principal) {
        String login = principal.getName();
        PasswordForm form = new PasswordForm(login);
        model.addAttribute("passForm", form);
        return "/student/resetPassword";
    }

    // скидання паролю
    @PostMapping("/student/resetPassword")
    public String ressetingPassword(Model model, //
            @ModelAttribute("passForm") @Validated PasswordForm passForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes, Principal principal) {
        // Validate result
        if (result.hasErrors()) {
            return "/student/resetPassword";
        }
        service.updatePassword(passForm.getLogin(), passForm.getPassword());
        return "redirect:/logout";
    }
    // отримання інформації про вчителя
    @GetMapping("/student/teacher/{id}")
    public String teacher(Model model,@PathVariable int id){
        TeacherView teacher = service.getTeacher(id);
        model.addAttribute("teacher",teacher);
        return "/student/teacher";
    }

}
