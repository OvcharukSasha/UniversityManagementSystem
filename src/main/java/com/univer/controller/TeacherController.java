/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.controller;
import com.univer.entity.AttendanceData;
import com.univer.entity.CourseTask;
import com.univer.form.DeleteTaskForm;
import com.univer.form.MarkForm;
import com.univer.form.PasswordForm;
import com.univer.form.TaskForm;
import com.univer.model.MonthView;
import com.univer.model.teacher.StudentMarkView;
import com.univer.model.teacher.StudentView;
import com.univer.model.teacher.TeacherView;
import com.univer.model.teacher.TeamView;
import com.univer.service.TeacherService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import com.univer.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Root
 * ?????????????????? ??????????????
 */
@Controller
public class TeacherController {

    @Autowired
    TeacherService service;
      String searchStr;

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
       
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        //Custom Date Editor
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));

    }
    // ?????????????? ????????????????, ???????????????? ???????????????? ?????????????? ???? ??????????
    @GetMapping("/teacher")
    public String index(Model model, Principal principal) {
        TeacherView teacher = service.getTeacher(principal.getName());
        if (teacher==null)  return "/teacher/404";
        //Set<FacultyView> faculties = service.getTeacherGroup(principal.getName());
        model.addAttribute("teacher", teacher);
        //model.addAttribute("faculties",faculties);
        return "/teacher/index";
    }

    // ???????????????? ???????????? ???? ??????????
    @GetMapping("/teacher/team/{idTeam}/{idTask}")
    public String team(Model model, Principal principal, @PathVariable int idTeam,
            @PathVariable int idTask) {
        TeamView teamView = service.getTeam(idTeam, idTask);
        if (teamView==null)  return "/teacher/404";
        model.addAttribute("team", teamView);
        return "/teacher/team";
    }

    // ???????????????? ???????????? ???? id
    @GetMapping("/teacher/task/{id}")
    @ResponseBody
    public CourseTask course(Model model,@PathVariable int id){
        CourseTask courseTask = service.getCourseTask(id);
        return  courseTask;
    }
    
    // ?????????? ???? ???????????? ????????????????????????
    @PostMapping("/teacher/visit/add")
    @ResponseBody
    public ResponseEntity<String>  addVisit(Model model, @RequestParam("studentId") String studentId, @RequestParam("date") String date){
        if (date==null) new ResponseEntity("Error",HttpStatus.FORBIDDEN);
        if (studentId==null) new ResponseEntity("Error",HttpStatus.FORBIDDEN);
        String res = service.addVisit(studentId, date);
        if (res==null) new ResponseEntity("Error",HttpStatus.FORBIDDEN);
        return new ResponseEntity(res,HttpStatus.OK);
    }
    // ?????????????? ????????????
    @PostMapping("/teacher/task/del")
    public String taskDel(Model model,@ModelAttribute("taskForm") DeleteTaskForm taskForm){
        String res = service.deleteCourseTask(taskForm.getTaskId());
        if (res!=null) model.addAttribute("mgs",res);
        return "redirect:/teacher/team/"+taskForm.getTeamId()+"/"+taskForm.getIdCourse();
    }
     // ?????????? ????????????
    @PostMapping(value = "/teacher/mark/add",params = "add")
    public String addMark(Model model,@ModelAttribute("markForm") MarkForm markForm){
        //String res = service.deleteCourseTask(taskForm.getTaskId());
        if (markForm==null) return "404";
        service.addMark(markForm.getStudentId(),markForm.getTaskId(), markForm.getMark());

        return "redirect:/teacher/students/"+markForm.getTeamId()+"/"+markForm.getCourseId();

    }
    // ?????????????? ????????????
    @PostMapping(value = "/teacher/mark/add",params = "del")
    public String delMark(Model model,@ModelAttribute("markForm") MarkForm markForm){
        //String res = service.deleteCourseTask(taskForm.getTaskId());
        if (markForm==null) return "404";
        service.delMark(markForm.getStudentId(),markForm.getTaskId(), markForm.getMark());

        return "redirect:/teacher/students/"+markForm.getTeamId()+"/"+markForm.getCourseId();

    }
    // ???????????????? ????????????
    @PostMapping("/teacher/team/{idTeam}/{idCourse}")
    public String saveTeam(Model model, Principal principal, @PathVariable int idTeam,
            @PathVariable int idCourse, @ModelAttribute("taskForm") TaskForm taskForm,
             BindingResult result,RedirectAttributes atts ) {
        if (taskForm!=null){
        service.addTask(taskForm,idTeam,idCourse);}
        return "redirect:/teacher/team/"+idTeam+"/"+idCourse;
    }
    // ???????????????? ???????????? ?????????????????? ?? ????????????????
     @GetMapping("/teacher/students/{idTeam}/{idCourse}")
    public String getStudentsView(Model model, Principal principal, 
            @PathVariable int idTeam,
            @PathVariable int idCourse
            ) {
        
        StudentMarkView students = service.getStudentsView(idTeam, idCourse);
        if (students==null) return "/teacher/404";
        model.addAttribute("view",students);
        return "/teacher/students";
    }
     // ???????????????????????? ?????????????? ???? ???????? id
    @GetMapping("/teacher/visit/{id}")
    public String visit(Model model, Principal principal,@PathVariable int id) {
        LocalDate now = LocalDate.now();
        int m = now.getMonthValue();
        int y = now.getYear();
        String login = principal.getName();
        MonthView month = service.getMonth(y, m, id);
        if (month==null) return "404";
        model.addAttribute("calendar", month);
        return "/teacher/visit";
    }
    // ???????????????????????????? ????????????????????????
    @GetMapping("/teacher/visit/{id}/{y}/{m}/add")
    public String visitAdd(Model model, Principal principal,@PathVariable int id, @PathVariable int y, @PathVariable int m) {
        m++;
        if (m > 12) {
            m = 1;
            y++;
        }
        String login = principal.getName();
        
        MonthView month = service.getMonth(y, m, id);
        if (month==null) return "404";
        model.addAttribute("calendar", month);
        return "/teacher/visit";
    }

    // ???????????????????????????? ????????????????????????
    @GetMapping("/teacher/visit/{id}/{y}/{m}/minus")
    public String visitMinus(Model model, Principal principal,@PathVariable int id,  @PathVariable int y, @PathVariable int m) {
        m--;
        if (m <= 0) {
            m = 12;
            y--;
        }
        
        MonthView month = service.getMonth(y, m, id);
        if (month==null) return "404";
        model.addAttribute("calendar", month);
        return "/teacher/visit";
    }
    // ?????????? ????????????????
    @PostMapping("/teacher/find")
    public String findUser(@RequestParam(name = "search", required = false, defaultValue = "") String search, 
            Model model, HttpServletRequest request) {
    {
        if (search.isEmpty() && searchStr == null) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        } else if (!search.isEmpty()) {
            searchStr = search;
        }

        Set<StudentView> list = service.findUser(searchStr);
        model.addAttribute("users", list);;
        return "/teacher/find";
      }
    }
    // ?????????????????????? ?????????? ???????????????????? ????????????
    @GetMapping("/teacher/resetPassword")
    public String ressetPassword(Model model, Principal principal) {
        String login = principal.getName();
        PasswordForm form = new PasswordForm(login);
        model.addAttribute("passForm", form);
        return "/teacher/resetPassword";
    }

    // ???????????????????? ????????????
    @PostMapping("/teacher/resetPassword")
    public String ressetingPassword(Model model, //
            @ModelAttribute("passForm") @Validated PasswordForm passForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes, Principal principal) {
        // Validate result
        if (result.hasErrors()) {
            return "/teacher/resetPassword";
        }
        service.updatePassword(passForm.getLogin(), passForm.getPassword());
        return "redirect:/logout";
    }
}