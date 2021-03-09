package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    public List<CourseSection> findCourseSectionAndLessonById(Integer id);
    public Course findCourseByCourseId(Integer id);
    public void saveCourseSection(CourseSection courseSection);
    public void updateCourseSection(CourseSection courseSection);
    public void updateCourseStatus(Integer id,Integer status);
}
