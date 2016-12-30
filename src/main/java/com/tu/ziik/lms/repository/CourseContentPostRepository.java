package com.tu.ziik.lms.repository;

import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.model.admin.course.Course;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ahmadjawid on 12/29/16.
 */

@Repository
public interface CourseContentPostRepository extends JpaRepository<CourseContentPost, Long> {

    List<CourseContentPost> findByCourseAndUser(Course course, User user);
}
