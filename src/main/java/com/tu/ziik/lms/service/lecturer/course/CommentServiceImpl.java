package com.tu.ziik.lms.service.lecturer.course;

import com.tu.ziik.lms.model.lecturer.Comment;
import com.tu.ziik.lms.repository.CommentRepository;
import com.tu.ziik.lms.repository.CourseContentPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ahmadjawid on 1/30/17.
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CourseContentPostRepository postRepository;

    @Override
    public void setCourseContentPost(Comment comment, Long postId) {

        comment.setCourseContentPost(postRepository.findOne(postId));

    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
