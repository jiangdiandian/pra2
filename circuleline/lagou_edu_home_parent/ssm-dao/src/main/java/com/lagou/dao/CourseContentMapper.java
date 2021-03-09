package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    public List<CourseSection> findCourseSectionAndLessonsById(Integer id);
    public Course findCourseByCourseId(Integer id);
    public void SaveCourseSection(CourseSection courseSection);
    public void updateCourseSection(CourseSection courseSection);
    public void updateSectionStatus(CourseSection courseSection);
}
