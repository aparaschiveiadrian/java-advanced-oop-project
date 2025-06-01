package org.adrianaparaschivei;

import org.adrianaparaschivei.data.DBConnection;
import org.adrianaparaschivei.data.SchemaInit;
import org.adrianaparaschivei.models.*;
import org.adrianaparaschivei.services.*;
import org.adrianaparaschivei.services.impl.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var studentService = StudentServiceImpl.getInstance();
        var courseService = CourseServiceImpl.getInstance();
        var progressService = CourseProgressServiceImpl.getInstance();
        var teacherService = TeacherServiceImpl.getInstance();
        QuizService quizService = QuizServiceImpl.getInstance();
        ClassroomService classroomService = ClassroomServiceImpl.getInstance();
        Connection connection = DBConnection.getInstance().getConnection();

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS users (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        username VARCHAR(100) UNIQUE NOT NULL,
                        email VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        user_type VARCHAR(20) NOT NULL
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS courses (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(100) UNIQUE NOT NULL,
                        description TEXT
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS quizzes (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        course_id INT REFERENCES courses(id)
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS quiz_questions (
                        id SERIAL PRIMARY KEY,
                        quiz_id INT REFERENCES quizzes(id),
                        question TEXT NOT NULL,
                        answer TEXT NOT NULL
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS enrollments (
                        id SERIAL PRIMARY KEY,
                        student_id INT REFERENCES users(id),
                        course_id INT REFERENCES courses(id),
                        UNIQUE (student_id, course_id)
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS course_progress (
                        id SERIAL PRIMARY KEY,
                        student_id INT REFERENCES users(id),
                        course_id INT REFERENCES courses(id),
                        completion_percentage INT CHECK (completion_percentage >= 0 AND completion_percentage <= 100),
                        UNIQUE (student_id, course_id)
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS teacher_courses (
                        id SERIAL PRIMARY KEY,
                        teacher_id INT REFERENCES users(id),
                        course_id INT REFERENCES courses(id),
                        UNIQUE (teacher_id, course_id)
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS classrooms (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) UNIQUE NOT NULL,
                        teacher_id INT REFERENCES users(id)
                    );
                """);

        SchemaInit.createTable(connection, """
                    CREATE TABLE IF NOT EXISTS classroom_students (
                        id SERIAL PRIMARY KEY,
                        classroom_id INT REFERENCES classrooms(id),
                        student_id INT REFERENCES users(id),
                        UNIQUE (classroom_id, student_id)
                    );
                """);

        Student student = new Student("Ion", "Popescu", "ionpop", "ion@email.com", "parola");
        studentService.create(student);

        Teacher teacher = new Teacher("Maria", "Ionescu", "mariai", "maria@email.com", "parola123");
        teacherService.create(teacher);


        Course course = new Course("Programare Java", "bazele limbajului Java");
        courseService.create(course);

        teacherService.addCourse(teacher, course);
        teacherService.printCourses(teacher);

        teacher.setFirstName("Mariana");
        teacherService.update(teacher);

        Long teacherId = teacherService.getIdByUsername("mariai");

        Long studentId = studentService.getIdByUsername("ionpop");
        Long courseId = courseService.getIdByTitle("Programare Java");

        if (studentId != null && courseId != null) {
            CourseProgress progress = new CourseProgress();
            progress.setCompletionPercentage(40);
            progressService.create(progress, studentId, courseId);

            progress.setCompletionPercentage(85);
            progressService.update(progress, studentId, courseId);

            progressService.deleteByStudentAndCourse(studentId, courseId);

            teacherService.delete(teacherId);
            courseService.delete(courseId);
            studentService.delete(studentId);
        } else {
            System.out.println("Eroare!");
        }

        DBConnection.getInstance().closeConnection();
    }
}
