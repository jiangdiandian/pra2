package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.util.List;

public interface CourseService {
    public List<Course> findCourseByCondition(CourseVo courseVo);
    public void addCourseOrTeacher(CourseVo courseVo);
    public CourseVo findCourseAndTeacherById(Integer id);
    public void updateCourseAndTeacher(CourseVo courseVo);
    public void updateCourseStatus(Integer status,Integer id);
}
