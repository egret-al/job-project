package com.other;

import com.SpringbootApplication;
import com.entity.other.Comment;
import com.service.other.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/12 21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestComment {

    @Autowired
    private CommentService commentService;

    @Test
    public void testSelectAll() throws Exception {
        List<Comment> commentList = commentService.selectAll();
        commentList.forEach(System.out::println);
    }
}
