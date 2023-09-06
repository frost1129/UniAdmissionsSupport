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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author prodi
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByUpdatedDate", query = "SELECT p FROM Post p WHERE p.updatedDate = :updatedDate"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByPostType", query = "SELECT p FROM Post p WHERE p.postType = :postType"),
    @NamedQuery(name = "Post.findByAllowComment", query = "SELECT p FROM Post p WHERE p.allowComment = :allowComment"),
    @NamedQuery(name = "Post.findByAllowQuestion", query = "SELECT p FROM Post p WHERE p.allowQuestion = :allowQuestion")})
public class Post implements Serializable {

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, message = "{post.title.notNull}")
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, message = "{post.content.notNull}")
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "post_type")
    private String postType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_comment")
    private boolean allowComment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_question")
    private boolean allowQuestion;
    @OneToMany(mappedBy = "livestreamPostId")
    @JsonIgnore
    private Set<LivestreamQuesion> livestreamQuesionSet;
    @JoinColumn(name = "admission_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AdmissionType admissionType;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    @JsonIgnore
    private Set<PostComment> postCommentSet;
    @Column(name = "image")
    private String image;
    
    @Transient
    private MultipartFile file;

    public Post() {
    }

    public Post(String id) {
        this.id = id;
    }

    public Post(String id, Date updatedDate, String title, String content, String postType, boolean allowComment, boolean allowQuestion) {
        this.id = id;
        this.updatedDate = updatedDate;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.allowComment = allowComment;
        this.allowQuestion = allowQuestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    public boolean getAllowQuestion() {
        return allowQuestion;
    }

    public void setAllowQuestion(boolean allowQuestion) {
        this.allowQuestion = allowQuestion;
    }

    @XmlTransient
    public Set<LivestreamQuesion> getLivestreamQuesionSet() {
        return livestreamQuesionSet;
    }

    public void setLivestreamQuesionSet(Set<LivestreamQuesion> livestreamQuesionSet) {
        this.livestreamQuesionSet = livestreamQuesionSet;
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Set<PostComment> getPostCommentSet() {
        return postCommentSet;
    }

    public void setPostCommentSet(Set<PostComment> postCommentSet) {
        this.postCommentSet = postCommentSet;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linhv.pojo.Post[ id=" + id + " ]";
    }

}
