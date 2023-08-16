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
import javax.persistence.Id;
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
@Table(name = "question_settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionSettings.findAll", query = "SELECT q FROM QuestionSettings q"),
    @NamedQuery(name = "QuestionSettings.findById", query = "SELECT q FROM QuestionSettings q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionSettings.findByFromTime", query = "SELECT q FROM QuestionSettings q WHERE q.fromTime = :fromTime"),
    @NamedQuery(name = "QuestionSettings.findByToTime", query = "SELECT q FROM QuestionSettings q WHERE q.toTime = :toTime")})
public class QuestionSettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Column(name = "fromTime")
    @Temporal(TemporalType.TIME)
    private Date fromTime;
    @Column(name = "toTime")
    @Temporal(TemporalType.TIME)
    private Date toTime;

    public QuestionSettings() {
    }

    public QuestionSettings(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
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
        if (!(object instanceof QuestionSettings)) {
            return false;
        }
        QuestionSettings other = (QuestionSettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.QuestionSettings[ id=" + id + " ]";
    }

}
