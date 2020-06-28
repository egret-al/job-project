package com.service.student;

import com.entity.student.Resume;
import com.service.base.BaseMapperService;

import java.util.List;

/**
 * @author 冉堃赤
 * @date 2020/3/26 14:43
 */
public interface ResumeService extends BaseMapperService<Resume> {

    List<Resume> selectAllPassedResumeByUsername(String username);

    List<Resume> selectResumeBySid(Integer sId);

    List<Resume> selectWaitCheckResumeByJid(Integer jId);

    boolean isFull(Integer jobId);

    int rejectStudentResume(String resumeId);

    int agreeStudentResume(Integer resumeId, Integer jobId);

    List<Resume> selectResumeByJid(Integer jId);

    Resume selectResumeById(Integer id);
}
