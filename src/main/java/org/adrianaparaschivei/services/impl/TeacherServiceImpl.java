package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.data.DBConnection;
import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.models.Teacher;
import org.adrianaparaschivei.services.CRUDService;
import org.adrianaparaschivei.services.TeacherService;

import java.sql.*;
import java.util.*;

public class TeacherServiceImpl implements TeacherService, CRUDService<Teacher> {
    private static TeacherServiceImpl instance;

    private TeacherServiceImpl() {}

    public static TeacherServiceImpl getInstance() {
        if (instance == null) {
            instance = new TeacherServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Teacher teacher) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, user_type) VALUES (?, ?, ?, ?, ?, 'teacher')";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getUsername());
            stmt.setString(4, teacher.getEmail());
            stmt.setString(5, "encrypted");

            stmt.executeUpdate();
            AuditService.getInstance().log("createTeacher");
            System.out.println("Teacher \"" + teacher.getUsername() + "\" a fost adaugat.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la crearea teacher-ului: " + e.getMessage(), e);
        }
    }

    @Override
    public Teacher read(long id) {
        String sql = "SELECT first_name, last_name, username, email, password FROM users WHERE id = ? AND user_type = 'teacher'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                AuditService.getInstance().log("readTeacher");
                System.out.println("Teacher gasit: " + teacher.getUsername());
                return teacher;
            } else {
                System.out.println("Nu exista teacher cu ID-ul: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea teacher-ului: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Teacher> readAll() {
        String sql = "SELECT first_name, last_name, username, email, password FROM users WHERE user_type = 'teacher'";
        List<Teacher> teachers = new ArrayList<>();
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            AuditService.getInstance().log("readAllTeachers");
            System.out.println("Toti profesorii au fost cititi din baza de date.");
            return teachers;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea profesorilor: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ? WHERE username = ? AND user_type = 'teacher'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setString(4, "encrypted");
            stmt.setString(5, teacher.getUsername());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("updateTeacher");
                System.out.println("Teacher-ul \"" + teacher.getUsername() + "\" a fost actualizat.");
            } else {
                System.out.println("Teacher-ul nu a fost gasit: " + teacher.getUsername());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizarea teacher-ului: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM users WHERE id = ? AND user_type = 'teacher'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("deleteTeacher");
                System.out.println("Teacher-ul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("Nu exista teacher cu ID-ul: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea teacher-ului: " + e.getMessage(), e);
        }
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

    public Long getIdByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ? AND user_type = 'teacher'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la cautarea teacher-ului dupa username: " + e.getMessage(), e);
        }
    }
}
