/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "faculty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f"),
    @NamedQuery(name = "Faculty.findById", query = "SELECT f FROM Faculty f WHERE f.id = :id"),
    @NamedQuery(name = "Faculty.findByName", query = "SELECT f FROM Faculty f WHERE f.name = :name"),
    @NamedQuery(name = "Faculty.findByNameEng", query = "SELECT f FROM Faculty f WHERE f.nameEng = :nameEng"),
    @NamedQuery(name = "Faculty.findByEmail", query = "SELECT f FROM Faculty f WHERE f.email = :email")})
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "website")
    private String website;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "name_eng")
    private String nameEng;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "facultyId")
    private FacultyPost facultyPost;
    @OneToMany(mappedBy = "facultyId")
    @JsonIgnore
    private Set<Banner> bannerSet;
    @JoinColumn(name = "uni_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UniMainInfo uniId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId")
    @JsonIgnore
    private Set<AdmissionScore> admissionScoreSet;

    public Faculty() {
    }

    public Faculty(Integer id) {
        this.id = id;
    }

    public Faculty(Integer id, String website, String name) {
        this.id = id;
        this.website = website;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FacultyPost getFacultyPost() {
        return facultyPost;
    }

    public void setFacultyPost(FacultyPost facultyPost) {
        this.facultyPost = facultyPost;
    }

    @XmlTransient
    public Set<Banner> getBannerSet() {
        return bannerSet;
    }

    public void setBannerSet(Set<Banner> bannerSet) {
        this.bannerSet = bannerSet;
    }

    public UniMainInfo getUniId() {
        return uniId;
    }

    public void setUniId(UniMainInfo uniId) {
        this.uniId = uniId;
    }

    @XmlTransient
    public Set<AdmissionScore> getAdmissionScoreSet() {
        return admissionScoreSet;
    }

    public void setAdmissionScoreSet(Set<AdmissionScore> admissionScoreSet) {
        this.admissionScoreSet = admissionScoreSet;
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
        if (!(object instanceof Faculty)) {
            return false;
        }
        Faculty other = (Faculty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.Faculty[ id=" + id + " ]";
    }

}
