/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.service;

import com.univer.entity.AttendanceData;
import com.univer.entity.Course;
import com.univer.entity.CourseMark;
import com.univer.entity.CourseTask;
import com.univer.entity.Faculty;
import com.univer.entity.Student;
import com.univer.entity.Teacher;
import com.univer.entity.TeacherCourse;
import com.univer.entity.Team;
import com.univer.entity.TeamCourse;
import com.univer.entity.User;
import com.univer.form.TaskForm;
import com.univer.model.BuilderMonth;
import com.univer.model.Day;
import com.univer.model.MonthView;
import com.univer.model.Week;
import com.univer.model.teacher.MarkView;
import com.univer.model.teacher.TeamView;
import com.univer.model.teacher.CourseView;
import com.univer.model.teacher.FacultyView;
import com.univer.model.teacher.StudentMarkView;
import com.univer.model.teacher.StudentView;
import com.univer.model.teacher.TaskView;

import com.univer.model.teacher.TeacherView;
import com.univer.repos.AttendanceDataRepository;
import com.univer.repos.CourseMarkRepository;
import com.univer.repos.CourseRepository;
import com.univer.repos.CourseTaskRepository;
import com.univer.repos.FacultyRepository;
import com.univer.repos.StudentRepository;
import com.univer.repos.TeacherCourseRepository;
import com.univer.repos.TeacherRepository;
import com.univer.repos.TeamCourseRepository;
import com.univer.repos.TeamRepository;
import com.univer.repos.UserRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Root
 * сервіс вчителя

 */
@Service
public class TeacherService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherCourseRepository teacherCourseRepository;
    @Autowired
    TeamCourseRepository teamCourseRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    CourseTaskRepository courseTaskRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired CourseMarkRepository courseMarkRepository;

     @Autowired AttendanceDataRepository attendanceDataRepository;
     @Autowired
private PasswordEncoder passwordEncoder;



    /**
     * додавання оцінкии
     * @param studentId  судента
     * @param taskId задачі
     * @param mark оцінка
     * @return збережений запис
     */
    public CourseMark addMark(int studentId, int taskId, int mark){
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student==null) return null;
        CourseTask courseTask = courseTaskRepository.findById(taskId).orElse(null);
        if (courseTask==null) return null;
        CourseMark courseMark = courseMarkRepository.findByTaskAndStudent(courseTask, student);
        if (courseMark==null) {courseMark = new CourseMark();
        courseMark.setCourseTask(courseTask);
        courseMark.setStudent(student);}
        courseMark.setMark(mark);
        courseMark.setDate(new Date());
        return courseMarkRepository.save(courseMark);
    }

    public String delMark(int studentId, int taskId, int mark){
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student==null) return null;
        CourseTask courseTask = courseTaskRepository.findById(taskId).orElse(null);
        if (courseTask==null) return null;
        CourseMark courseMark = courseMarkRepository.findByTaskAndStudent(courseTask, student);
        if (courseMark!=null) {
            courseMarkRepository.delete(courseMark);
            return "OK";
        }
        return null;
    }

    /**
     * інформація по студентам
     * @param teamId групи
     * @param courseId предмену
     * @return предмети та задачі
     */
    public StudentMarkView getStudentsView(int teamId, int courseId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            return null;
        }
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return null;
        }

        Set<TeamCourse> teamCourses = teamCourseRepository.findByTeamAndCourse(team, course);
        if (teamCourses == null || teamCourses.size() == 0) {
            return null;
        }
        StudentMarkView studentView = new StudentMarkView();
        studentView.setCourseId(courseId);
        studentView.setTeamId(teamId);
        TeamCourse teamCourse = teamCourses.iterator().next();
        studentView.setTeam(team.getName());
        studentView.setCourse(course.getName());
        Set<CourseTask> tasks = teamCourse.getCourseTasks();
        for (CourseTask task : tasks) {
            studentView.addTask(task);
        }

        Set<Student> students = teamCourse.getTeam().getStudents();
        for (Student student : students) {
            StudentView s = studentView.addStudent(student);
            Set<CourseMark> marks = student.getCourseMarks();
                for (CourseMark mark : marks) {
                    for (TaskView task : s.getTasks()) {
                        if (task.getId() == mark.getCourseTask().getId()) {
                            MarkView markView = new MarkView(mark);
                            task.setMark(markView);
                        }
                    }
                }
        }

        return studentView;
    }

    public TeamView getTeam(int teamId, int courseId) {

        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            return null;
        }
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return null;
        }

        TeamView teamView = new TeamView();
        teamView.setName(team.getName());
        teamView.setId(team.getId());
        CourseView courseView = new CourseView();
        courseView.setId(course.getId());
        courseView.setName(course.getName());
        courseView.setInfo(course.getInfo());
        teamView.setCourse(courseView);
        Set<TaskView> taskViews = new TreeSet<>();
        if (course != null) {
            Set<TeamCourse> teamCourses = teamCourseRepository.findByTeamAndCourse(team, course);
            for (TeamCourse teamCourse : teamCourses) {
                Set<CourseTask> tasks = teamCourse.getCourseTasks();
                for (CourseTask task : tasks) {
                    TaskView taskView = new TaskView(task);

                    taskViews.add(taskView);
                }
            }

        }
        teamView.setTasks(taskViews);
        /*
        for(Student student:students){
            StudentView studentView = new StudentView();
            
        }*/

        return teamView;
    }

    public String addVisit(String studentId,String date){
        try {
            int id = Integer.valueOf(studentId);
            Student student = studentRepository.findById(id).orElse(null);
            if (student==null) return null;
            Date dateAdd = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            AttendanceData att = attendanceDataRepository.findOneByUidAndDate(student.getUidTag(),dateAdd);
            if (att!=null) {
                attendanceDataRepository.delete(att);
                return "REMOVE";
            }
            att = new AttendanceData();
            att.setDate(dateAdd);att.setUidTag(student.getUidTag());
            attendanceDataRepository.save(att);
            return "ADD";
        } catch (ParseException ex) {

        }
        return  null;
    }
    public void addTask(TaskForm form, int idTeam, int idCourse) {

        Team team = teamRepository.findById(idTeam).get();
        Course course = courseRepository.findById(idCourse).get();
        if (team == null || course == null) {
            return;
        }

        CourseTask task = null;
        if (form.getId() == null || form.getId() == 0) {
            task = new CourseTask();
        } else {
            task = courseTaskRepository.findById(form.getId()).orElse(null);
            if (task == null) {
                task = new CourseTask();
            }
        }
        task.setDeadline(form.getDeadline());
        task.setStartDate(form.getStartDate());
        task.setInfo(form.getInfo());
        task.setMaxMark(form.getMaxMark());
        if (task.getTeamCourse() == null) {
            TeamCourse teamCourse = teamCourseRepository.findByTeamAndCourse(team, course).stream().findFirst().orElse(null);
            if (teamCourse == null) {
                teamCourse = new TeamCourse();
                teamCourse = teamCourseRepository.save(teamCourse);
            }
            task.setTeamCourse(teamCourse);

        }
        courseTaskRepository.save(task);
    }

    public String deleteCourseTask(int taskId) {
        CourseTask courseTask = courseTaskRepository.findById(taskId).orElse(null);
        if (courseTask == null) {
            return "Не знайдено";
        }
        if (courseTask.getCourseMarks().size() > 0) {
            return "По задачі є оцінки!";
        }
        courseTaskRepository.delete(courseTask);
        courseTask = courseTaskRepository.findById(taskId).orElse(null);
        if (courseTask == null) {
            return null;
        }
        return "Не видалено! Зверніться до адміністратора!";
    }

    public CourseTask getCourseTask(int id) {
        CourseTask courseTask = courseTaskRepository.findById(id).orElse(new CourseTask());
        return courseTask;
    }

    public TeacherView getTeacher(String login) {
        TeacherView teacherView = new TeacherView();
        User user = userRepository.findByLogin(login).get();
        Teacher teacher = teacherRepository.findById(user.getId()).get();
        teacherView.setId(teacher.getId());
        teacherView.setName(teacher.getName());
        teacherView.setSurname(teacher.getSurname());
        teacherView.setPosition(teacher.getPosition());
        Set<TeacherCourse> teacherCourses = teacherCourseRepository.findByTeacher(teacher);
        for (TeacherCourse teacherCourse : teacherCourses) {
            Course course = teacherCourse.getCourse();
            CourseView courseView = new CourseView();
            courseView.setId(course.getId());
            courseView.setName(course.getName());
            courseView.setInfo(course.getInfo());
            Set<TeamCourse> teamCourses = teamCourseRepository.findByCourse(course);
            for (TeamCourse teamCourse : teamCourses) {
                Team team = teamCourse.getTeam();
                TeamView teamView = new TeamView();
                teamView.setId(team.getId());
                teamView.setName(team.getName());
                Faculty faculty = team.getFaculty();
                FacultyView facultyView = courseView.getFaculteis().stream().filter(f -> f.getId() == faculty.getId()).findFirst().orElse(null);
                if (facultyView == null) {
                    facultyView = new FacultyView();
                    facultyView.setId(faculty.getId());
                    facultyView.setName(faculty.getName());
                }
                facultyView.addTeam(teamView);
                courseView.addFaculty(facultyView);
            }
            teacherView.addCourse(courseView);
        }
        return teacherView;
    }

    public Set<StudentView> findUser(String name){
        Set<StudentView> list = new TreeSet<>();
        List<Student> students = studentRepository.findUser(name.toLowerCase());
        for(Student student:students){
            StudentView view = new StudentView(student);
            view.setTeam(student.getTeam().getName());
            view.setFaculty(student.getTeam().getFaculty().getName());
            list.add(view);
        }
        return  list;
    }



     public MonthView getMonth(int y,int m, int id){
        MonthView month = BuilderMonth.getInstance(m, y);

        Student student = studentRepository.findById(id).get();
        if (student==null) return null;
        month.setStudentId(student.getId());
        month.setFullName(student.getSurname()+" "+student.getName());
        month.setPhone(student.getPhoneNumber());
        month.setEmail(student.getEmail());
        month.setTeam(student.getTeam().getName());
        month.setFaculty(student.getTeam().getFaculty().getName());
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
       User user = userRepository.findByLogin(login).orElse(null);
       if (user==null) return;
       user.setPassword(passwordEncoder.encode(passwrod));
       userRepository.save(user);

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
