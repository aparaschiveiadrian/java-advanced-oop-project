package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.models.Classroom;
import org.adrianaparaschivei.models.Student;
import org.adrianaparaschivei.services.ClassroomService;

public class ClassroomServiceImpl implements ClassroomService {
    private static ClassroomServiceImpl instance;

    private ClassroomServiceImpl() {}

    public static ClassroomServiceImpl getInstance() {
        if (instance == null) {
            instance = new ClassroomServiceImpl();
        }
        return instance;
    }

    @Override
    public void addStudentToClass(Classroom classroom, Student student) {
        classroom.addStudent(student);
        System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " added to class: " + classroom.getRoomName());
    }

    @Override
    public void removeStudentFromClass(Classroom classroom, Student student) {
        classroom.removeStudent(student);
        System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " removed from class: " + classroom.getRoomName());
    }

    @Override
    public void printClassroomDetails(Classroom classroom) {
        classroom.printClassroomDetails();
    }
}
