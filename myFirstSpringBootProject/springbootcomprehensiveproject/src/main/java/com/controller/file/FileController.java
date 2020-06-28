package com.controller.file;

import com.entity.authentication.User;
import com.entity.student.Resume;
import com.service.student.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传下载的Controller
 * @author 冉堃赤
 * @date 2020/3/8 15:09
 */
@RestController
public class FileController {

    //注入文件服务器的位置
    @Value("${file-server-location}")
    private String fileServerLocation;

    @Autowired
    private ResumeService resumeService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public Object fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID().toString() + "_" + fileName;
        int size = (int) file.getSize();
        System.out.println(fileName + ":" + size);
        String path = "F:/files/";
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @PostMapping("multipleFileUpload")
    public Object multipleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        if (files.isEmpty()) {
            return false;
        }
        String path = "F:/files/";
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + "_" + fileName;
            int size = (int) file.getSize();
            System.out.println(fileName + ":" + size);

            if (file.isEmpty()) {
                return false;
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 投递简历，本质就是文件上传
     * 将学生的相关信息以及上传文件的文件路径保存到数据库，便于商家审核简历
     * @return
     */
    @RequestMapping(value = "/singleFileUpload", method = RequestMethod.POST)
    public Object singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("jobId") String jobId
            , HttpSession session) {

        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        //uuid设置文件名的唯一性
        fileName = UUID.randomUUID().toString() + "_" + fileName;
        int size = (int) file.getSize();
        System.out.println(fileName + ":" + size);
        //获取文件服务器的物理路径
        String path = fileServerLocation;
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        logger.info("上传的文件名：" + fileName);
        logger.info("文件后缀：" + fileName.substring(fileName.lastIndexOf(".")));
        try {
            //保存文件
            file.transferTo(dest);
            logger.info("文件上传成功");
            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            User user = (User) session.getAttribute("loginUser");

            Resume resume = new Resume();
            resume.setStudentId(user.getId());
            resume.setApplyTime(new Date());
            resume.setFilePath(path + fileName);
            resume.setJobId(Integer.valueOf(jobId));

            int influence = resumeService.insert(resume);
            logger.info("影响行数：" + influence);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件上传失败");
            return false;
        }
    }

    /**
     * 查看简历，实质上是文件下载
     * @param response
     * @param path
     * @return
     */
    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    public Object downLoadFile(HttpServletResponse response, @RequestParam("path") String path) {
        System.out.println(path);

        File file = new File(path);
        if (file.exists()) {
            response.setContentType("application/force-download");
            System.out.println(file.getName());
            try {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream is = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                OutputStream os = response.getOutputStream();
                int len = bis.read(buffer);

                while (len != -1) {
                    os.write(buffer, 0, len);
                    len = bis.read(buffer);
                }
                logger.info("Download the song successfully!");

                return "下载成功";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("找不到文件服务器！");
        }
        return "找不到文件服务器，下载失败！";
    }
}
