package com.tu.ziik.lms.service.admin.course;

import com.tu.ziik.lms.model.admin.course.CourseCategory;
import com.tu.ziik.lms.repository.CourseCategoryRepository;
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

    @Override
    public void saveCategory(CourseCategory courseCategory) {

        categoryRepository.save(courseCategory);

    }

    @Override
    public boolean categoryExists(String name) {

        if (categoryRepository.findByName(name).isEmpty())
            return false;
        return true;
    }

    @Override
    public List<CourseCategory> findAllCategories() {
        return categoryRepository.findAll();
    }
}
