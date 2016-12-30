package com.tu.ziik.lms.service.admin.course;

import com.tu.ziik.lms.model.admin.course.Course;
import com.tu.ziik.lms.model.admin.course.CourseCategory;
import com.tu.ziik.lms.repository.CourseCategoryRepository;
import com.tu.ziik.lms.repository.CourseRepository;
import com.tu.ziik.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ahmadjawid on 12/27/16.
 */

@Service
public class AdminCourseServiceImp implements AdminCourseService {

    @Autowired
    private CourseCategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveCategory(CourseCategory courseCategory) {

        categoryRepository.save(courseCategory);

    }

    @Override
    public boolean categoryExists(String name) {

        if (categoryRepository.findByName(name) == null)
            return false;
        return true;
    }

    @Override
    public List<CourseCategory> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {

        course.setCourseCategory(categoryRepository.findByName(course.getCategory()));
        courseRepository.save(course);

    }

    @Override
    public List<Course> findAllCoursesByUsername(String username) {
        return courseRepository.findByUser(userRepository.findByUsername(username));
    }
}
