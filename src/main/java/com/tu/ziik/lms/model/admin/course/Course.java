package com.tu.ziik.lms.model.admin.course;



import javax.persistence.*;
import javax.persistence.Id;

/**
 * Created by ahmadjawid on 12/23/16.
 */

@Entity
public class Course {
    private Long id;
    //private String name;
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

//    public Course() {
//
//    }
//
//    public Course(String title) {
//        this.title = title;
//    }
//
//    public Course(String title, CourseCategory courseCategory) {
//        this.title = title;
//        this.courseCategory = courseCategory;
//    }

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
}

//@Entity
//@Table(name = "course")
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String title;
//
//
//    private String content;
//
//    private String category;
//
//    @Version
//    private Integer version;
//
//    //private CourseCategory courseCategory;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//
////    @ManyToOne
////    @JoinColumn(name = "course_category_id")
////    public CourseCategory getCourseCategory() {
////        return courseCategory;
////    }
////
////    public void setCourseCategory(CourseCategory courseCategory) {
////        this.courseCategory = courseCategory;
////    }
//}
