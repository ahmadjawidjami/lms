package com.tu.ziik.lms.model.admin.course;



import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Set;

/**
 * Created by ahmadjawid on 12/23/16.
 */

@Entity
public class Course {
    private Long id;
    private String title;
//
//
 private String content;
//

   private String category;
//
   @Version
    private Integer version;
    private CourseCategory courseCategory;

    private Set<CourseContentPost> courseContentPosts;

    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Transient
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne
    @JoinColumn(name = "course_category_id")
    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    public Set<CourseContentPost> getCourseContentPosts(){
        return courseContentPosts;
    }

    public void setCourseContentPosts(Set<CourseContentPost> courseContentPosts) {
        this.courseContentPosts = courseContentPosts;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}