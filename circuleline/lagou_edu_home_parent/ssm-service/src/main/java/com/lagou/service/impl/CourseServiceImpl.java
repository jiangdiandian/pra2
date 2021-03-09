package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void addCourseOrTeacher(CourseVo courseVo) {
        Course course = new Course();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,course);
        System.out.println(course);
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        System.out.println(course);
        courseMapper.addCourse(course);
        BeanUtils.copyProperties(courseVo,teacher);
        teacher.setCourseId(course.getId());
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(new Date());
        System.out.println(teacher);
        courseMapper.addTeacher(teacher);
    }

    @Override
    public CourseVo findCourseAndTeacherById(Integer id) {
        return courseMapper.findCourseAndTeacherById(id);
    }

    @Override
    public void updateCourseAndTeacher(CourseVo courseVo) {
        Course course = new Course();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,course);
        course.setUpdateTime(new Date());
        courseMapper.updateCourse(course);
        BeanUtils.copyProperties(courseVo,teacher);
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(new Date());
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer status, Integer id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }
}
