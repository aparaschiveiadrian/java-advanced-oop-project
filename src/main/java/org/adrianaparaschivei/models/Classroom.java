package org.adrianaparaschivei.models;

import java.util.HashSet;
import java.util.Set;

public class Classroom {
    private String roomName;
    private Teacher teacher;
    private Set<Student> students;

    public Classroom(String roomName, Teacher teacher) {
        this.roomName = roomName;
        this.teacher = teacher;
        this.students = new HashSet<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void printClassroomDetails() {
        System.out.println("Classroom: " + roomName);
        System.out.println("Teacher: " + teacher.getFirstName() + " " + teacher.getLastName());
        System.out.println("Students in the class: ");
        for (Student student : students) {
            System.out.println("- " + student.getFirstName() + " " + student.getLastName());
        }
    }
}
