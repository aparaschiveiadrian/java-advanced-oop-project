package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.models.Teacher;
import org.adrianaparaschivei.services.TeacherService;

import java.util.Set;

public class TeacherServiceImpl implements TeacherService {
    private static TeacherServiceImpl instance;
    private TeacherServiceImpl() {}

    public static TeacherServiceImpl getInstance() {
        if (instance == null) {
            instance = new TeacherServiceImpl();
        }
        return instance;
    }

    @Override
    public void addCourse(Teacher teacher, Course course) {
        Set<Course> courses = teacher.getOwnedCourses();
        if (courses.contains(course)) {
            System.out.println("Course already exists for this teacher: " + course.getTitle());
            return;
        }

        courses.add(course);
        System.out.println("Added course \"" + course.getTitle() + "\" to teacher " + teacher.getUsername());
    }

    @Override
    public void removeCourse(Teacher teacher, Course course) {
        Set<Course> courses = teacher.getOwnedCourses();
        if (!courses.contains(course)) {
            System.out.println("Course not found for teacher: " + course.getTitle());
            return;
        }

        courses.remove(course);
        System.out.println("Removed course \"" + course.getTitle() + "\" from teacher " + teacher.getUsername());
    }

    @Override
    public void printCourses(Teacher teacher) {
        Set<Course> courses = teacher.getOwnedCourses();
        if (courses.isEmpty()) {
            System.out.println(teacher.getUsername() + " does not own any courses.");
            return;
        }

        System.out.println("Courses owned by " + teacher.getUsername() + ":");
        for (Course course : courses) {
            System.out.println("- " + course.getTitle() + ": " + course.getDescription());
        }
    }
}
