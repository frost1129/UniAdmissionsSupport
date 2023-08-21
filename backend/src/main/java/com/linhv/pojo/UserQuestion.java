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
@Table(name = "user_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserQuestion.findAll", query = "SELECT u FROM UserQuestion u"),
    @NamedQuery(name = "UserQuestion.findById", query = "SELECT u FROM UserQuestion u WHERE u.id = :id"),
    @NamedQuery(name = "UserQuestion.findBySubmitTime", query = "SELECT u FROM UserQuestion u WHERE u.submitTime = :submitTime")})
public class UserQuestion implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "submit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitTime;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "admission_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdmissionType admissionType;
    @JoinColumn(name = "answer_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User answerUserId;
    @JoinColumn(name = "ask_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User askUserId;
    @Size(min = 1, max = 65535)
    @Column(name = "ask_user_email")
    private String askUserEmail;

    public UserQuestion() {
    }

    public UserQuestion(String id) {
        this.id = id;
    }

    public UserQuestion(String id, String content, Date submitTime, String answer) {
        this.id = id;
        this.content = content;
        this.submitTime = submitTime;
        this.answer = answer;
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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public User getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(User answerUserId) {
        this.answerUserId = answerUserId;
    }

    public User getAskUserId() {
        return askUserId;
    }

    public void setAskUserId(User askUserId) {
        this.askUserId = askUserId;
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
        if (!(object instanceof UserQuestion)) {
            return false;
        }
        UserQuestion other = (UserQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.UserQuestion[ id=" + id + " ]";
    }

}