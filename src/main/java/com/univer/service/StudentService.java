/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.service;

import com.univer.model.BuilderMonth;
import com.univer.model.Day;
import com.univer.model.MonthView;
import com.univer.model.Week;
import com.univer.entity.AttendanceData;
import com.univer.entity.Course;
import com.univer.entity.CourseMark;
import com.univer.entity.CourseTask;
import com.univer.entity.Student;
import com.univer.entity.Teacher;
import com.univer.entity.TeacherCourse;
import com.univer.entity.TeamCourse;
import com.univer.entity.User;
import com.univer.model.student.CourseView;
import com.univer.model.student.MarkView;
import com.univer.model.student.StudentCourseView;
import com.univer.model.student.StudentView;
import com.univer.model.student.TaskView;
import com.univer.model.student.TeacherView;
import com.univer.repos.AttendanceDataRepository;
import com.univer.repos.StudentRepository;
import com.univer.repos.TeacherRepository;
import com.univer.repos.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Root
 * сервіс студента
 */
@Service
public class StudentService {
    @Autowired StudentRepository studentRepository;
    @Autowired UserRepository userRepository;
    @Autowired TeacherRepository teacherRepository;
    
    @Autowired AttendanceDataRepository attendanceDataRepository;
       @Autowired
    private PasswordEncoder passwordEncoder;
    public MonthView getMonth(int y,int m, String login){
        
        User user = userRepository.findByLogin(login).orElse(null);
        if (user==null) return null;
        MonthView month = BuilderMonth.getInstance(m, y);
        Student student = studentRepository.findById(user.getId()).get();
        List<AttendanceData> list = attendanceDataRepository.findByUidAndDate(student.getUidTag(),
                convertToDate(month.getFirstDay()),convertToDate(month.getLastDay()));
        for(AttendanceData data:list){
            for(Week week:month.getWeeks()){
                for(Day day:week.getDays()){
                    if (day.getDate().equals(data.getDate().toInstant()
                     .atZone(ZoneId.systemDefault()).toLocalDate())){
                        day.setSelected(true);
                    }
                }
            }
        }
        return month;
    }
    
    public void updatePassword(String login,String passwrod){
       User user = userRepository.findByLogin(login).get();
       user.setPassword(passwordEncoder.encode(passwrod));
       userRepository.save(user);

    }
    public TeacherView getTeacher(int id){
        TeacherView teacherView = new TeacherView();
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            teacherView.setId(teacher.getId());
            teacherView.setName(teacher.getName());
            teacherView.setSurname(teacher.getSurname());
            teacherView.setPosition(teacher.getPosition());
            teacherView.setEmail(teacher.getEmail());
        }
        return teacherView;
                
    }
    
    public StudentCourseView getStudentCourses(String login){
        User user = userRepository.findByLogin(login).get();
        StudentCourseView  studentCourseView= new StudentCourseView();
        Student student = studentRepository.findById(user.getId()).get();
        Set<TeamCourse> teamCurses =  student.getTeam().getTeamCourses();
        for(TeamCourse teamCourse:teamCurses){
            Course course =  teamCourse.getCourse();
            CourseView courseView = new CourseView();
            courseView.setId(course.getId());
            courseView.setName(course.getName());
            courseView.setInfo(course.getInfo());
            for(TeacherCourse teachCourse :course.getTeacherCourses()){
                Teacher teacher =  teachCourse.getTeacher();
                TeacherView teacherView = new TeacherView();
                teacherView.setId(teacher.getId());
                teacherView.setName(teacher.getName());
                teacherView.setSurname(teacher.getSurname());
                teacherView.setPosition(teacher.getPosition());
                courseView.addTeacher(teacherView);
            }
            Set<CourseTask> courseTasks = teamCourse.getCourseTasks();
            for(CourseTask courseTask:courseTasks){
                TaskView task = new TaskView();
                task.setId(courseTask.getId());
                task.setStartDate(convertToLocalDate(courseTask.getStartDate()));
                task.setDeadline(convertToLocalDate(courseTask.getDeadline()));
                task.setInfo(courseTask.getInfo());
                task.setMaxMark(courseTask.getMaxMark());
                Optional<CourseMark> courseMark =  courseTask.getCourseMarks().stream().filter(m->m.getStudent().getId()==student.getId()).findFirst();
                if (courseMark.isPresent()){
                    MarkView mark = new MarkView();
                    mark.setDate(convertToLocalDate(courseMark.get().getDate()));
                    mark.setMark(courseMark.get().getMark());
                    task.setMark(mark);
                }
                courseView.addTask(task);
            }
            studentCourseView.addCoures(courseView);
        }
        
        return  studentCourseView;
    }
    
    public StudentView getStudentView (String login){
        StudentView studentView = new StudentView();
        User user = userRepository.findByLogin(login).get();
        Student student = studentRepository.findById(user.getId()).get();
        studentView.setName(student.getName());
        studentView.setSurname(student.getSurname());
        studentView.setPhoneNumber(student.getPhoneNumber());
        studentView.setEmail(student.getEmail());
        studentView.setTeam(student.getTeam().getName());
        studentView.setFaculty(student.getTeam().getFaculty().getName());
        return studentView;
    }
    public Date convertToDate(LocalDate dateToConvert) {
    return java.util.Date.from(dateToConvert.atStartOfDay()
      .atZone(ZoneId.systemDefault())
      .toInstant());
    }
    public LocalDate convertToLocalDate(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
}
    
}
