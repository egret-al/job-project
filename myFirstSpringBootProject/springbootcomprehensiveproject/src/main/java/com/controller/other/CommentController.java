package com.controller.other;

import com.entity.authentication.User;
import com.entity.other.Comment;
import com.service.authentication.UserService;
import com.service.other.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/14 9:47
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 管理员才有权利进行删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/comment/deleteComment", method = RequestMethod.GET)
    public Object deleteComment(@RequestParam("id") Integer id) {
        System.out.println(id);
        return id;
    }

    /**
     * 发表评论
     * @param comment 评论javabean
     * @return json
     */
    @RequestMapping(value = "/comment/sendComment", method = RequestMethod.POST)
    public Object addComment(@RequestBody Comment comment) {
        Map<Object, Object> result = new HashMap<Object, Object>();
        String message = comment.getMessage();

        if (message.length() > 200) {
            result.put("status", -1);
            result.put("msg", "言论过长，发表失败！");
        } else {
            int row = commentService.insertComment(comment);
            if (row > 0) {
                result.put("status", 1);
                result.put("msg", "发表评论成功！");
                int id = comment.getUserId();
                User user = userService.selectUserById(id);
                result.put("sendUserName", user.getName());
            } else {
                result.put("status", 0);
                result.put("msg", "发表失败！");
            }
        }

        return result;
    }
}
