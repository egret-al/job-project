package com.service.other.impl;

import com.dao.other.CommentDao;
import com.entity.other.Comment;
import com.service.other.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/12 21:35
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    /**
     * 判断是否有删除的权利
     * @param currentUserId 当前登录用户的id
     * @param targetUserId 被删除评论的工作的用户id
     * @return 是否有删除的权利
     */
    @Override
    public boolean hasAuthorityWithDeleting(int currentUserId, int targetUserId) {
        return currentUserId == targetUserId;
    }

    @Override
    public List<Comment> selectAll() {
        return commentDao.selectAll();
    }

    @Override
    public List<Comment> selectCommentByJobId(Integer jobId) {
        return commentDao.selectCommentByJobId(jobId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCommentById(Integer id) {
        return commentDao.deleteCommentById(id);
    }
}
