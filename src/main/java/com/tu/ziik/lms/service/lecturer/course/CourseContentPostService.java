package com.tu.ziik.lms.service.lecturer.course;

import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.model.admin.course.Course;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;

import java.util.List;

/**
 * Created by ahmadjawid on 12/29/16.
 */
public interface CourseContentPostService {
    void savePost(CourseContentPost courseContentPost);

    void setCourse(CourseContentPost courseContentPost, Long courseId);

    Course findCourseById(Long courseId);

    List<CourseContentPost> findAllPosts();

    List<CourseContentPost> findAllPostsByCourseIdAndUsername(Long courseId, String username);

    void setUser(CourseContentPost courseContentPost, String userId);
}
