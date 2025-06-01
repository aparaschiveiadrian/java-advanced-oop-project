package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.data.DBConnection;
import org.adrianaparaschivei.models.Course;
import org.adrianaparaschivei.services.CRUDService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CRUDService<Course> {
    private static CourseServiceImpl instance;

    private CourseServiceImpl() {}

    public static CourseServiceImpl getInstance() {
        if (instance == null) {
            instance = new CourseServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO courses (title, description) VALUES (?, ?)";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getDescription());
            stmt.executeUpdate();
            AuditService.getInstance().log("createCourse");
            System.out.println("Cursul \"" + course.getTitle() + "\" a fost adaugat.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la crearea cursului: " + e.getMessage(), e);
        }
    }
    public Long getIdByTitle(String title) {
        String sql = "SELECT id FROM courses WHERE title = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la cautarea cursului dupa titlu: " + e.getMessage(), e);
        }
        return null;
    }
    @Override
    public Course read(long id) {
        String sql = "SELECT title, description FROM courses WHERE id = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            AuditService.getInstance().log("readCourse");
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                System.out.println("Curs gasit: " + title + " - " + description);
                return new Course(title, description);
            } else {
                System.out.println("Nu exista niciun curs cu ID-ul: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea cursului: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> readAll() {
        String sql = "SELECT title, description FROM courses";
        List<Course> courses = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getString("title"), rs.getString("description")));
            }
            AuditService.getInstance().log("readAllCourses");
            System.out.println("Au fost citite toate cursurile din baza de date.");
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea tuturor cursurilor: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE courses SET description = ? WHERE title = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getDescription());
            stmt.setString(2, course.getTitle());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("updateCourse");
                System.out.println("Cursul \"" + course.getTitle() + "\" a fost actualizat.");
            } else {
                System.out.println("Cursul \"" + course.getTitle() + "\" nu a fost gasit.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizarea cursului: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("deleteCourse");
                System.out.println("Cursul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("Nu exista niciun curs cu ID-ul: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea cursului: " + e.getMessage(), e);
        }
    }
}
