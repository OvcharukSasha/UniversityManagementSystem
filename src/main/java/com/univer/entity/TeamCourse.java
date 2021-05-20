/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Root
 */
@Entity
@Table(name = "TeamCourse")
@NamedQueries({
    @NamedQuery(name = "TeamCourse.findAll", query = "SELECT t FROM TeamCourse t"),
    @NamedQuery(name = "TeamCourse.findById", query = "SELECT t FROM TeamCourse t WHERE t.id = :id")})
public class TeamCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idTeam", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team team;
    @JoinColumn(name = "idCourse", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamCourse", fetch = FetchType.LAZY)
    private Set<CourseTask> courseTasks;

    public TeamCourse() {
    }

    public TeamCourse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<CourseTask> getCourseTasks() {
        return courseTasks;
    }

    public void setCourseTasks(Set<CourseTask> courseTasks) {
        this.courseTasks = courseTasks;
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
        if (!(object instanceof TeamCourse)) {
            return false;
        }
        TeamCourse other = (TeamCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univer.TeamCourse[ id=" + id + " ]";
    }
    
}
