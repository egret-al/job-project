package com.dao.other;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.other.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/12 19:34
 */
@Mapper
@Repository("commentDao")
public interface CommentDao {

    /**
     * 查询所有评论
     * @return 评论的集合
     */
    @Select("SELECT * FROM t_comment")
    @Results(id = "commentMapper", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "message", column = "message"),
            @Result(property = "releasesTime", column = "releases_time"),
            @Result(property = "userId", column = "u_id"),
            @Result(property = "jobId", column = "j_id"),
            @Result(column = "u_id", property = "user", many = @Many(
                    select = "com.dao.authentication.UserDao.selectUserById",
                    fetchType = FetchType.EAGER
            )),
            @Result(column = "j_id", property = "job", many = @Many(
                    select = "com.dao.merchant.JobDao.selectJobById",
                    fetchType = FetchType.EAGER
            ))
    })
    List<Comment> selectAll();

    /**
     * 根据工作查询评论列表
     * @param jobId 工作id
     * @return 评论的集合
     */
    @ResultMap("commentMapper")
    @Select("SELECT * FROM t_comment WHERE j_id = #{id}")
    List<Comment> selectCommentByJobId(@Param("id") Integer jobId);

    /**
     * 添加评论
     * @param comment 评论的POJO对象
     * @return 影响的行数
     */
    @Options(useGeneratedKeys = true, keyProperty = "comment.id", keyColumn = "id")
    @Insert("INSERT INTO t_comment(message, releases_time, u_id, j_id) VALUES(#{comment.message}, " +
            "#{comment.releasesTime}, #{comment.userId}, #{comment.jobId})")
    int insertComment(@Param("comment") Comment comment);

    /**
     * 根据id删除评论
     * @param id 评论的id
     * @return
     */
    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    int deleteCommentById(@Param("id") Integer id);
}
