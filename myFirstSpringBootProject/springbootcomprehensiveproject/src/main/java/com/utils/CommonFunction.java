package com.utils;

import com.service.other.CreditGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 冉堃赤
 * @version 1.0
 * @date 2020/5/22 9:19
 */
@Component
public class CommonFunction {

    @Autowired
    private CreditGradeService creditGradeService;

    /**
     * 根据jobId查询评分和评分人数
     * @param jobId 工作id
     * @return map结果
     */
    public Map<String, Integer> getAvgScoreByJobId(Integer jobId) {
        Map<String, Integer> result = new HashMap<String, Integer>(2);

        //查询该工作的评分
        Integer avgScore = creditGradeService.getAverageScoreByJobId(jobId);
        result.put("avgScore", avgScore);

        //查询该工作的评分人数
        int countWithScore = creditGradeService.getCountByJobId(jobId);
        result.put("countWithScore", countWithScore);

        return result;
    }
}
