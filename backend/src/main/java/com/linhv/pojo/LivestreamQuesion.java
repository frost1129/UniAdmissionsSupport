/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "livestream_quesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LivestreamQuesion.findAll", query = "SELECT l FROM LivestreamQuesion l"),
    @NamedQuery(name = "LivestreamQuesion.findById", query = "SELECT l FROM LivestreamQuesion l WHERE l.id = :id")})
public class LivestreamQuesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "admission_type", referencedColumnName = "id")
    @ManyToOne
    private AdmissionType admissionType;
    @JoinColumn(name = "livestream_post_id", referencedColumnName = "id")
    @ManyToOne
    private Post livestreamPostId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public LivestreamQuesion() {
    }

    public LivestreamQuesion(String id) {
        this.id = id;
    }

    public LivestreamQuesion(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public Post getLivestreamPostId() {
        return livestreamPostId;
    }

    public void setLivestreamPostId(Post livestreamPostId) {
        this.livestreamPostId = livestreamPostId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof LivestreamQuesion)) {
            return false;
        }
        LivestreamQuesion other = (LivestreamQuesion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.LivestreamQuesion[ id=" + id + " ]";
    }

}
