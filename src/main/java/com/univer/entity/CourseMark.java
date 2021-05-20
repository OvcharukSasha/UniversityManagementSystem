/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Root
 */
@Entity
@Table(name = "CourseMark")
@NamedQueries({
    @NamedQuery(name = "CourseMark.findAll", query = "SELECT c FROM CourseMark c"),
    @NamedQuery(name = "CourseMark.findById", query = "SELECT c FROM CourseMark c WHERE c.id = :id"),
    @NamedQuery(name = "CourseMark.findByDate", query = "SELECT c FROM CourseMark c WHERE c.date = :date"),
    @NamedQuery(name = "CourseMark.findByMark", query = "SELECT c FROM CourseMark c WHERE c.mark = :mark")})
public class CourseMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "mark")
    private int mark;
    @JoinColumn(name = "idCourseTask", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CourseTask courseTask;
    @JoinColumn(name = "idStudent", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student student;

    public CourseMark() {
    }

    public CourseMark(Integer id) {
        this.id = id;
    }

    public CourseMark(Integer id, Date date, int mark) {
        this.id = id;
        this.date = date;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public CourseTask getCourseTask() {
        return courseTask;
    }

    public void setCourseTask(CourseTask courseTask) {
        this.courseTask = courseTask;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseMark)) {
            return false;
        }
        CourseMark other = (CourseMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseMark{" + "id=" + id + ", date=" + date + ", mark=" + mark + '}';
    }

  

    
    
}
