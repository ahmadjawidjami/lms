package com.tu.ziik.lms.model.lecturer;

import javax.persistence.*;

/**
 * Created by ahmadjawid on 1/30/17.
 */

@Entity
public class Comment {

    private Long id;
    private String commenter;
    private String description;

    private CourseContentPost courseContentPost;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "course_content_post_id")
    public CourseContentPost getCourseContentPost() {
        return courseContentPost;
    }

    public void setCourseContentPost(CourseContentPost courseContentPost) {
        this.courseContentPost = courseContentPost;
    }
}
