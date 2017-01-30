package com.tu.ziik.lms.repository;

import com.tu.ziik.lms.model.lecturer.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ahmadjawid on 1/30/17.
 */

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {
}
