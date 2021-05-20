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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "AttendanceData")
@NamedQueries({
    @NamedQuery(name = "AttendanceData.findAll", query = "SELECT a FROM AttendanceData a"),
    @NamedQuery(name = "AttendanceData.findByUidTag", query = "SELECT a FROM AttendanceData a WHERE a.uidTag = :uidTag"),
    @NamedQuery(name = "AttendanceData.findById", query = "SELECT a FROM AttendanceData a WHERE a.id = :id"),
    @NamedQuery(name = "AttendanceData.findByDate", query = "SELECT a FROM AttendanceData a WHERE a.date = :date")})
public class AttendanceData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "uid_tag")
    private String uidTag;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public AttendanceData() {
    }

    public AttendanceData(Integer id) {
        this.id = id;
    }

    public AttendanceData(Integer id, String uidTag, Date date) {
        this.id = id;
        this.uidTag = uidTag;
        this.date = date;
    }

    public String getUidTag() {
        return uidTag;
    }

    public void setUidTag(String uidTag) {
        this.uidTag = uidTag;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttendanceData)) {
            return false;
        }
        AttendanceData other = (AttendanceData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univer.AttendanceData[ id=" + id + " ]";
    }
    
}
