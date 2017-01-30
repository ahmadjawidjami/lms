package com.tu.ziik.lms.service.lecturer.course;

import com.tu.ziik.lms.model.lecturer.Comment;

/**
 * Created by ahmadjawid on 1/30/17.
 */
public interface CommentService {
    void setCourseContentPost(Comment comment, Long postId);

    void saveComment(Comment comment);
}
