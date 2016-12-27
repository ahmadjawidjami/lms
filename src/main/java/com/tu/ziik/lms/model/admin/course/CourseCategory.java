package com.tu.ziik.lms.model.admin.course;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ahmadjawid on 12/23/16.
 */


@Entity
@Table(name = "course_category")
public class CourseCategory {
    private Long id;
    private String name;
    private Set<Course> courses;

//    public CourseCategory(){
//
//    }
//
//    public CourseCategory(String name) {
//        this.name = name;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "courseCategory", cascade = CascadeType.ALL)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}











//@Entity
//public class CourseCategory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    //private Set<Course> courses;
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//
////    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
////    public Set<Course> getCourses() {
////        return courses;
////    }
////
////    public void setBooks(Set<Course> courses) {
////        this.courses = courses;
////    }
//}
