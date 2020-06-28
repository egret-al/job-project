package com.service.other;

import com.entity.other.Comment;

import java.util.List;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/12 21:33
 */
public interface CommentService {

    boolean hasAuthorityWithDeleting(int currentUserId, int targetUserId);

    List<Comment> selectAll();

    List<Comment> selectCommentByJobId(Integer jobId);

    int insertComment(Comment comment);

    int deleteCommentById(Integer id);
}
