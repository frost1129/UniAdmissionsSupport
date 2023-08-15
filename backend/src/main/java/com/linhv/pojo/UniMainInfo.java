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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "uni_main_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniMainInfo.findAll", query = "SELECT u FROM UniMainInfo u"),
    @NamedQuery(name = "UniMainInfo.findById", query = "SELECT u FROM UniMainInfo u WHERE u.id = :id"),
    @NamedQuery(name = "UniMainInfo.findByName", query = "SELECT u FROM UniMainInfo u WHERE u.name = :name"),
    @NamedQuery(name = "UniMainInfo.findByCode", query = "SELECT u FROM UniMainInfo u WHERE u.code = :code"),
    @NamedQuery(name = "UniMainInfo.findByAdmissionEmail", query = "SELECT u FROM UniMainInfo u WHERE u.admissionEmail = :admissionEmail"),
    @NamedQuery(name = "UniMainInfo.findByAdmissionPhone", query = "SELECT u FROM UniMainInfo u WHERE u.admissionPhone = :admissionPhone")})
public class UniMainInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "admission_email")
    private String admissionEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "admission_phone")
    private String admissionPhone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniId")
    @JsonIgnore
    private Set<Banner> bannerSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniId")
    @JsonIgnore
    private Set<Faculty> facultySet;
    @JoinColumn(name = "admission_address", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Branch admissionAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniId")
    @JsonIgnore
    private Set<User> userSet;

    public UniMainInfo() {
    }

    public UniMainInfo(String id) {
        this.id = id;
    }

    public UniMainInfo(String id, String name, String code, String admissionEmail, String admissionPhone) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.admissionEmail = admissionEmail;
        this.admissionPhone = admissionPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdmissionEmail() {
        return admissionEmail;
    }

    public void setAdmissionEmail(String admissionEmail) {
        this.admissionEmail = admissionEmail;
    }

    public String getAdmissionPhone() {
        return admissionPhone;
    }

    public void setAdmissionPhone(String admissionPhone) {
        this.admissionPhone = admissionPhone;
    }

    @XmlTransient
    public Set<Banner> getBannerSet() {
        return bannerSet;
    }

    public void setBannerSet(Set<Banner> bannerSet) {
        this.bannerSet = bannerSet;
    }

    @XmlTransient
    public Set<Faculty> getFacultySet() {
        return facultySet;
    }

    public void setFacultySet(Set<Faculty> facultySet) {
        this.facultySet = facultySet;
    }

    public Branch getAdmissionAddress() {
        return admissionAddress;
    }

    public void setAdmissionAddress(Branch admissionAddress) {
        this.admissionAddress = admissionAddress;
    }

    @XmlTransient
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
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
        if (!(object instanceof UniMainInfo)) {
            return false;
        }
        UniMainInfo other = (UniMainInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.UniMainInfo[ id=" + id + " ]";
    }

}
