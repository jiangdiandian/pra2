package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        System.out.println("daole");
        List<Course> courseByCondition = courseService.findCourseByCondition(courseVo);
        System.out.println(courseByCondition);
        return new ResponseResult(true,200,"响应成功",courseByCondition);
    }
    @RequestMapping("/courseUpload")
    public ResponseResult pictureUpload(MultipartFile file, HttpServletRequest request)  {
        if (file==null){
            throw new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String path=realPath.substring(0,realPath.indexOf("ssm_web1"));
        String uploadPath=path+"upload";
        String fileName = file.getOriginalFilename();
        String end = fileName.substring(fileName.lastIndexOf("."));
        String newFileName=System.currentTimeMillis()+end;
        File file1 = new File(uploadPath, newFileName);
        if (!file1.getParentFile().exists()){
            System.out.println("新建了文件");
            file1.getParentFile().mkdir();
        }
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", map);
        return res;
    }
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){
        if (courseVo.getId() == 0) {
            courseService.addCourseOrTeacher(courseVo);
            ResponseResult res = new ResponseResult(true, 200, "响应成功", null);
            return res;
        }else {
            courseService.updateCourseAndTeacher(courseVo);
            ResponseResult res = new ResponseResult(true, 200, "修改成功", null);
            return res;
        }
    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseAndTeacherById(id);
        System.out.println(courseVo);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", courseVo);
        return res;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(status,id);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", map);
        return res;
    }
}
