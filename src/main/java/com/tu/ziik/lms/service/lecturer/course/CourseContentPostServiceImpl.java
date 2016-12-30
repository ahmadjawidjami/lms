package com.tu.ziik.lms.service.lecturer.course;

import com.tu.ziik.lms.model.admin.course.Course;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import com.tu.ziik.lms.repository.CourseContentPostRepository;
import com.tu.ziik.lms.repository.CourseRepository;
import com.tu.ziik.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ahmadjawid on 12/29/16.
 */
@Service
public class CourseContentPostServiceImpl implements CourseContentPostService {

    @Autowired
    private CourseContentPostRepository postRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void savePost(CourseContentPost courseContentPost) {

        postRepository.save(courseContentPost);

    }

    @Override
    public void setCourse(CourseContentPost courseContentPost, Long courseId) {

        courseContentPost.setCourse(courseRepository.findOne(courseId));

    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findOne(courseId);
    }

    @Override
    public List<CourseContentPost> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<CourseContentPost> findAllPostsByCourseIdAndUsername(Long courseId, String username) {
        return postRepository.findByCourseAndUser(courseRepository.findOne(courseId), userRepository.findByUsername(username));
    }

    @Override
    public void setUser(CourseContentPost courseContentPost, String username) {

        courseContentPost.setUser(userRepository.findByUsername(username));

    }
}
