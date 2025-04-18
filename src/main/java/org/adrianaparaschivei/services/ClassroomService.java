package org.adrianaparaschivei.services;

import org.adrianaparaschivei.models.Classroom;
import org.adrianaparaschivei.models.Student;

public interface ClassroomService {
    void addStudentToClass(Classroom classroom, Student student);
    void removeStudentFromClass(Classroom classroom, Student student);
    void printClassroomDetails(Classroom classroom);
}
