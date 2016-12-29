package com.tu.ziik.lms.model.lecturer;

import com.tu.ziik.lms.model.admin.course.Course;

import javax.persistence.*;

/**
 * Created by ahmadjawid on 12/28/16.
 */

@Entity
public class CourseContentPost {



    private Long id;
    private String title;
    private String description;
    private String filePath;

    private Course course;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }
}
