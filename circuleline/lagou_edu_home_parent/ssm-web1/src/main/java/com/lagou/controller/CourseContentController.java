package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findCourseContent(Integer courseId){
        List<CourseSection> list = courseContentService.findCourseSectionAndLessonById(courseId);
        ResponseResult resp = new ResponseResult(true, 200, "查询课程信息成功", list);
        return resp;
    }
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseById(Integer courseId){
        System.out.println(courseId);
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", course);
        return res;
    }
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId()==null){
            courseContentService.saveCourseSection(courseSection);
            ResponseResult res = new ResponseResult(true, 200, "响应成功", null);
            return res;
        }else {
            courseContentService.updateCourseSection(courseSection);
            ResponseResult res = new ResponseResult(true, 200, "响应成功", null);
            return res;
        }
    }
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        courseContentService.updateCourseStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult res = new ResponseResult(true, 200, "响应成功", status);
        return res;
    }
}
