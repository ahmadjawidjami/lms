package com.tu.ziik.lms.repository;


import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.model.admin.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ahmadjawid on 12/23/16.
 */
@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {

    List<Course> findByUser(User user);

}
