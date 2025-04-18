package org.adrianaparaschivei.models;

import java.util.*;


public class Student extends User {
    Comparator <Course> comp = new Comparator<Course>() {
        @Override
        public int compare(Course o1, Course o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };
    private final Set<Course> attendedCourses = new TreeSet<>(comp);
    private final Map<Course, CourseProgress> progressCourses = new HashMap<>();

    public Student(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }

    public Set<Course> getAttendedCourses() {
        return attendedCourses;
    }

    public Map<Course, CourseProgress> getProgressCourses() {
        return progressCourses;
    }

    public CourseProgress getProgressForCourse(Course course) {
        return progressCourses.get(course);
    }

    @Override
    public String getDetails() {
        return "Student: " + getFirstName() + " " + getLastName() + ", username: " + getUsername();
    }
}
