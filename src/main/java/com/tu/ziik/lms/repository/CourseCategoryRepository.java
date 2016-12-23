package com.tu.ziik.lms.repository;

import com.tu.ziik.lms.model.admin.course.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ahmadjawid on 12/23/16.
 */
@Repository
public interface CourseCategoryRepository extends JpaRepository <CourseCategory, Long> {
}
