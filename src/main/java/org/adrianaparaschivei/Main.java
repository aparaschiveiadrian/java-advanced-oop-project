package org.adrianaparaschivei;

import org.adrianaparaschivei.models.*;
import org.adrianaparaschivei.services.*;
import org.adrianaparaschivei.services.impl.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = StudentServiceImpl.getInstance();
        TeacherService teacherService = TeacherServiceImpl.getInstance();
        QuizService quizService = QuizServiceImpl.getInstance();
        ClassroomService classroomService = ClassroomServiceImpl.getInstance();

        Teacher teacher = new Teacher("Mihai", "Ionescu", "mihai_ionescu", "mihai.ionescu@example.com", "parola123");

        Course mathCourse = new Course("Matematica 6", "Inmultiri si Impartiri");
        Course physicsCourse = new Course("Fizica 1", "Introducere in fizica");
        Course chemistryCourse = new Course("Chemistry", "apa e buna");

        teacherService.addCourse(teacher, mathCourse);
        teacherService.addCourse(teacher, physicsCourse);

        Student student1 = new Student("Vlad", "Moisanu", "vlad_moisanu", "vlad.moisanu@example.com", "parola123");
        Student student2 = new Student("Alexandru", "Mircea", "alexandru_mircea", "alexandru.mircea@example.com", "parola123");

        studentService.enrollStudentInCourse(student1, mathCourse);
        studentService.enrollStudentInCourse(student1, chemistryCourse);
        studentService.enrollStudentInCourse(student2, physicsCourse);

        studentService.printEnrolledCoursesDetails(student1);
        studentService.printProgressForCourses(student1);

        studentService.printEnrolledCoursesDetails(student2);
        studentService.printProgressForCourses(student2);

        teacherService.printCourses(teacher);

        studentService.updateProgress(student1, mathCourse, 50);
        studentService.updateProgress(student2, physicsCourse, 70);

        studentService.printProgressForCourses(student1);
        studentService.printProgressForCourses(student2);

        teacherService.removeCourse(teacher, physicsCourse);
        teacherService.printCourses(teacher);

        Quiz quiz = new Quiz("Matematica Quiz",
                new ArrayList<>(Arrays.asList("Ce este 2+2?", "Ce este 3+3?")),
                new ArrayList<>(Arrays.asList("4", "6")));
        quizService.addQuestion(quiz, "Ce este 5+5?", "10");

        quizService.printQuizDetails(quiz);

        Classroom classroom = new Classroom("Room A", teacher);
        classroomService.addStudentToClass(classroom, student1);
        classroomService.addStudentToClass(classroom, student2);

        classroomService.printClassroomDetails(classroom);
    }
}
