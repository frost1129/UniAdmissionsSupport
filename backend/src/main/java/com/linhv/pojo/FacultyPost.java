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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "faculty_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacultyPost.findAll", query = "SELECT f FROM FacultyPost f"),
    @NamedQuery(name = "FacultyPost.findById", query = "SELECT f FROM FacultyPost f WHERE f.id = :id"),
    @NamedQuery(name = "FacultyPost.findByUpdatedDate", query = "SELECT f FROM FacultyPost f WHERE f.updatedDate = :updatedDate")})
public class FacultyPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(min = 1, message = "{facultyPost.content.notNull}")
    @Column(name = "content")
    private String content;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public FacultyPost() {
    }

    public FacultyPost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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
        if (!(object instanceof FacultyPost)) {
            return false;
        }
        FacultyPost other = (FacultyPost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.FacultyPost[ id=" + id + " ]";
    }

}
