/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Root
 */
@Entity
@Table(name = "CourseTask")
@NamedQueries({
    @NamedQuery(name = "CourseTask.findAll", query = "SELECT c FROM CourseTask c"),
    @NamedQuery(name = "CourseTask.findById", query = "SELECT c FROM CourseTask c WHERE c.id = :id"),
    @NamedQuery(name = "CourseTask.findByMaxMark", query = "SELECT c FROM CourseTask c WHERE c.maxMark = :maxMark"),
    @NamedQuery(name = "CourseTask.findByStartDate", query = "SELECT c FROM CourseTask c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CourseTask.findByDeadline", query = "SELECT c FROM CourseTask c WHERE c.deadline = :deadline")})
public class CourseTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "maxMark")
    private int maxMark;
    @Basic(optional = false)
    @Lob
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    
    
    @JoinColumn(name = "idTeamCourse", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private TeamCourse teamCourse;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseTask", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CourseMark> courseMarks;

    public CourseTask() {
    }

    public CourseTask(Integer id) {
        this.id = id;
    }

    public CourseTask(Integer id, int maxMark, String info, Date startDate, Date deadline) {
        this.id = id;
        this.maxMark = maxMark;
        this.info = info;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

  
    public Set<CourseMark> getCourseMarks() {
        return courseMarks;
    }

    public void setCourseMarks(Set<CourseMark> courseMarks) {
        this.courseMarks = courseMarks;
    }

    public TeamCourse getTeamCourse() {
        return teamCourse;
    }

    public void setTeamCourse(TeamCourse teamCourse) {
        this.teamCourse = teamCourse;
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
        if (!(object instanceof CourseTask)) {
            return false;
        }
        CourseTask other = (CourseTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return "com.univer.CourseTask[ id=" + id + " ]";
    }*/

    @Override
    public String toString() {
        return "CourseTask{" + "id=" + id + ", maxMark=" + maxMark + ", info=" + info + ", startDate=" + startDate + ", deadline=" + deadline + ", courseMarks=" + courseMarks + '}';
    }

    
    
    
}
