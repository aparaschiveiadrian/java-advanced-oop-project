package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.models.CourseProgress;
import org.adrianaparaschivei.models.Student;
import org.adrianaparaschivei.services.StudentService;

import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl instance;
    private StudentServiceImpl() {}

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public void enrollStudentInCourse(Student student, Course course) {
        if (student.getAttendedCourses().contains(course)) {
            System.out.println("Student is already enrolled in course: " + course.getTitle());
            return;
        }

        student.getAttendedCourses().add(course);
        student.getProgressCourses().put(course, new CourseProgress());
        System.out.println("Student " + student.getUsername() + " enrolled in course: " + course.getTitle());
    }

    @Override
    public void updateProgress(Student student, Course course, int percentage) {
        CourseProgress progress = student.getProgressCourses().get(course);
        if (progress != null) {
            progress.setCompletionPercentage(percentage);
            System.out.println("Progress updated for " + student.getUsername() + " in course: " + course.getTitle());
        } else {
            System.out.println("Student is not enrolled in course: " + course.getTitle());
        }
    }

    @Override
    public void printEnrolledCoursesDetails(Student student) {
        if (student.getAttendedCourses().isEmpty()) {
            System.out.println(student.getUsername() + " is not enrolled in any courses.");
            return;
        }

        System.out.println("Courses for student " + student.getUsername() + ":");
        for (Course course : student.getAttendedCourses()) {
            System.out.println("- " + course.getTitle() + ": " + course.getDescription());
        }
    }

    @Override
    public void printProgressForCourses(Student student) {
        if (student.getProgressCourses().isEmpty()) {
            System.out.println("No progress found for " + student.getUsername());
            return;
        }

        System.out.println("Progress for " + student.getUsername() + ":");
        for (Map.Entry<Course, CourseProgress> entry : student.getProgressCourses().entrySet()) {
            Course course = entry.getKey();
            CourseProgress progress = entry.getValue();
            System.out.println("- " + course.getTitle() + ": " + progress.getCompletionPercentage() + "%");
        }
    }
}
