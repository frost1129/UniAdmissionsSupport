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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "admission_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmissionType.findAll", query = "SELECT a FROM AdmissionType a"),
    @NamedQuery(name = "AdmissionType.findById", query = "SELECT a FROM AdmissionType a WHERE a.id = :id"),
    @NamedQuery(name = "AdmissionType.findByName", query = "SELECT a FROM AdmissionType a WHERE a.name = :name")})
public class AdmissionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "admissionType")
    @JsonIgnore
    private Set<LivestreamQuesion> livestreamQuesionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admissionType")
    @JsonIgnore
    private Set<Post> postSet;
    @OneToMany(mappedBy = "userAdmissionType")
    @JsonIgnore
    private Set<User> userSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admissionType")
    @JsonIgnore
    private Set<UserQuestion> userQuestionSet;

    public AdmissionType() {
    }

    public AdmissionType(Integer id) {
        this.id = id;
    }

    public AdmissionType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<LivestreamQuesion> getLivestreamQuesionSet() {
        return livestreamQuesionSet;
    }

    public void setLivestreamQuesionSet(Set<LivestreamQuesion> livestreamQuesionSet) {
        this.livestreamQuesionSet = livestreamQuesionSet;
    }

    @XmlTransient
    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }

    @XmlTransient
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @XmlTransient
    public Set<UserQuestion> getUserQuestionSet() {
        return userQuestionSet;
    }

    public void setUserQuestionSet(Set<UserQuestion> userQuestionSet) {
        this.userQuestionSet = userQuestionSet;
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
        if (!(object instanceof AdmissionType)) {
            return false;
        }
        AdmissionType other = (AdmissionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.AdmissionType[ id=" + id + " ]";
    }

}
