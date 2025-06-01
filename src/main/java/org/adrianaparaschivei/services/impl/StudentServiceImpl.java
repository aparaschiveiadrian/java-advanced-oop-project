package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.data.DBConnection;
import org.adrianaparaschivei.models.Student;
import org.adrianaparaschivei.services.CRUDService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements CRUDService<Student> {
    private static StudentServiceImpl instance;

    private StudentServiceImpl() {}

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, user_type) VALUES (?, ?, ?, ?, ?, 'student')";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getUsername());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, "encrypted");

            stmt.executeUpdate();
            AuditService.getInstance().log("createStudent");
            System.out.println("Studentul \"" + student.getUsername() + "\" a fost adaugat.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la crearea studentului: " + e.getMessage(), e);
        }
    }
    public Long getIdByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ? AND user_type = 'student'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la cautarea studentului dupa username: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Student read(long id) {
        String sql = "SELECT first_name, last_name, username, email, password FROM users WHERE id = ? AND user_type = 'student'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                AuditService.getInstance().log("readStudent");
                System.out.println("Student gasit: " + student.getUsername());
                return student;
            } else {
                System.out.println("Nu exista student cu ID-ul: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea studentului: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> readAll() {
        String sql = "SELECT first_name, last_name, username, email, password FROM users WHERE user_type = 'student'";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            AuditService.getInstance().log("readAllStudents");
            System.out.println("Toti studentii au fost cititi din baza de date.");
            return students;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea studentilor: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ? WHERE username = ? AND user_type = 'student'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, "encrypted");
            stmt.setString(5, student.getUsername());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("updateStudent");
                System.out.println("Studentul \"" + student.getUsername() + "\" a fost actualizat.");
            } else {
                System.out.println("Studentul nu a fost gasit: " + student.getUsername());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizarea studentului: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM users WHERE id = ? AND user_type = 'student'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("deleteStudent");
                System.out.println("Studentul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("Nu exista student cu ID-ul: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea studentului: " + e.getMessage(), e);
        }
    }
}
