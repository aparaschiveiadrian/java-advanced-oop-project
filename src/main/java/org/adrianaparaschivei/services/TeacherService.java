package org.adrianaparaschivei.services;

import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.models.Teacher;

public interface TeacherService {
    void addCourse(Teacher teacher, Course course);
    void removeCourse(Teacher teacher, Course course);
    void printCourses(Teacher teacher);
}
