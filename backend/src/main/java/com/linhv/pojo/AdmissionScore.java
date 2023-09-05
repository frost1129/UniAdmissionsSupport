/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "admission_score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmissionScore.findAll", query = "SELECT a FROM AdmissionScore a"),
    @NamedQuery(name = "AdmissionScore.findById", query = "SELECT a FROM AdmissionScore a WHERE a.id = :id"),
    @NamedQuery(name = "AdmissionScore.findByYear", query = "SELECT a FROM AdmissionScore a WHERE a.year = :year")})
public class AdmissionScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{admissionScore.year.notNull}")
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, message = "{admissionScore.content.notNull}")
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Faculty facultyId;

    public AdmissionScore() {
    }

    public AdmissionScore(Integer id) {
        this.id = id;
    }

    public AdmissionScore(Integer id, Date year, String content) {
        this.id = id;
        this.year = year;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
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
        if (!(object instanceof AdmissionScore)) {
            return false;
        }
        AdmissionScore other = (AdmissionScore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.AdmissionScore[ id=" + id + " ]";
    }

}
