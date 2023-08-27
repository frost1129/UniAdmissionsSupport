/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByFacebookId", query = "SELECT u FROM User u WHERE u.facebookId = :facebookId"),
    @NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate")})
public class User implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String ADVISOR = "ADVISOR";
    public static final String USER = "USER";

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    @Column(name = "facebook_id")
    private String facebookId;
    @Lob
    @Size(max = 65535)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "user_role")
    private String userRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<LivestreamQuesion> livestreamQuesionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<Faqs> faqsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<Post> postSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    @JsonIgnore
    private Set<PostComment> postCommentSet;
    @JoinColumn(name = "user_admission_type", referencedColumnName = "id")
    @ManyToOne
    private AdmissionType userAdmissionType;
    @JoinColumn(name = "uni_id", referencedColumnName = "id")
    @ManyToOne
    private UniMainInfo uniId;
    @OneToMany(mappedBy = "answerUserId")
    @JsonIgnore
    private Set<UserQuestion> userQuestionSet;
    @OneToMany(mappedBy = "askUserId")
    @JsonIgnore
    private Set<UserQuestion> userQuestionSet1;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String password, String firstName, String lastName, String userRole, boolean active, Date createdDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.active = active;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<LivestreamQuesion> getLivestreamQuesionSet() {
        return livestreamQuesionSet;
    }

    public void setLivestreamQuesionSet(Set<LivestreamQuesion> livestreamQuesionSet) {
        this.livestreamQuesionSet = livestreamQuesionSet;
    }

    @XmlTransient
    public Set<Faqs> getFaqsSet() {
        return faqsSet;
    }

    public void setFaqsSet(Set<Faqs> faqsSet) {
        this.faqsSet = faqsSet;
    }

    @XmlTransient
    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }

    @XmlTransient
    public Set<PostComment> getPostCommentSet() {
        return postCommentSet;
    }

    public void setPostCommentSet(Set<PostComment> postCommentSet) {
        this.postCommentSet = postCommentSet;
    }

    public AdmissionType getUserAdmissionType() {
        return userAdmissionType;
    }

    public void setUserAdmissionType(AdmissionType userAdmissionType) {
        this.userAdmissionType = userAdmissionType;
    }

    public UniMainInfo getUniId() {
        return uniId;
    }

    public void setUniId(UniMainInfo uniId) {
        this.uniId = uniId;
    }

    @XmlTransient
    public Set<UserQuestion> getUserQuestionSet() {
        return userQuestionSet;
    }

    public void setUserQuestionSet(Set<UserQuestion> userQuestionSet) {
        this.userQuestionSet = userQuestionSet;
    }

    @XmlTransient
    public Set<UserQuestion> getUserQuestionSet1() {
        return userQuestionSet1;
    }

    public void setUserQuestionSet1(Set<UserQuestion> userQuestionSet1) {
        this.userQuestionSet1 = userQuestionSet1;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.User[ id=" + id + " ]";
    }

}
