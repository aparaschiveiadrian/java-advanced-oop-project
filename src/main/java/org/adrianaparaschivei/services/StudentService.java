package org.adrianaparaschivei.services;

import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.models.Student;

public interface StudentService {
    void enrollStudentInCourse(Student student, Course course);
    void updateProgress(Student student, Course course, int percentage);
    void printEnrolledCoursesDetails(Student student);
    void printProgressForCourses(Student student);
}