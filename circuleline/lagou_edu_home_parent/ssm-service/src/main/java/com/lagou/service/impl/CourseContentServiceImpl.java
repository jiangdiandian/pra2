package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override
    public List<CourseSection> findCourseSectionAndLessonById(Integer id) {
        return courseContentMapper.findCourseSectionAndLessonsById(id);
    }

    @Override
    public Course findCourseByCourseId(Integer id) {
        Course course = courseContentMapper.findCourseByCourseId(id);
        return course;
    }

    @Override
    public void saveCourseSection(CourseSection courseSection) {
        courseSection.setCreateTime(new Date());
        courseSection.setUpdateTime(new Date());
        courseContentMapper.SaveCourseSection(courseSection);
    }

    @Override
    public void updateCourseSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateCourseSection(courseSection);
    }

    @Override
    public void updateCourseStatus(Integer id,Integer status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }
}
