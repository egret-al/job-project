package com.controller.other;

import com.service.other.CreditGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/15 9:42
 */
@RestController
public class CreditGradeController {

    @Autowired
    private CreditGradeService creditGradeService;

    /**
     * 添加评分
     * @param score 分数
     * @param studentId 学生id
     * @param jobId 工作id
     * @return json
     */
    @RequestMapping(value = "/student/addScore", method = RequestMethod.POST)
    public Object addScore(@RequestParam Integer score, @RequestParam Integer studentId, @RequestParam Integer jobId) {
        Map<Object, Object> result = new HashMap<Object, Object>();

        int row = creditGradeService.insertCreditGrade(jobId, studentId, score);
        if (row == 0) {
            result.put("status", 0);
            result.put("msg", "您已经对该工作做出了评分！");
        } else {
            result.put("status", 1);
            result.put("msg", "评分成功！");
        }
        return result;
    }
}
