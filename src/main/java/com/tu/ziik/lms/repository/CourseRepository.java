package com.tu.ziik.lms.repository;


import com.tu.ziik.lms.model.admin.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ahmadjawid on 12/23/16.
 */
@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {

}
