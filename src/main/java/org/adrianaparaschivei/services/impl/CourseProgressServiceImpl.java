package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.data.DBConnection;
import org.adrianaparaschivei.models.CourseProgress;
import org.adrianaparaschivei.services.CRUDService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseProgressServiceImpl implements CRUDService<CourseProgress> {
    private static CourseProgressServiceImpl instance;

    private CourseProgressServiceImpl() {}

    public static CourseProgressServiceImpl getInstance() {
        if (instance == null) {
            instance = new CourseProgressServiceImpl();
        }
        return instance;
    }

    public void create(CourseProgress progress, long studentId, long courseId) {
        String sql = "INSERT INTO course_progress (student_id, course_id, completion_percentage) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, studentId);
            stmt.setLong(2, courseId);
            stmt.setInt(3, progress.getCompletionPercentage());

            stmt.executeUpdate();
            AuditService.getInstance().log("createCourseProgress");
            System.out.println("Progresul a fost adaugat pentru student ID " + studentId + " si curs ID " + courseId);
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la crearea progresului: " + e.getMessage(), e);
        }
    }

    @Override
    public CourseProgress read(long id) {
        String sql = "SELECT completion_percentage FROM course_progress WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int percentage = rs.getInt("completion_percentage");
                CourseProgress progress = new CourseProgress();
                progress.setCompletionPercentage(percentage);
                AuditService.getInstance().log("readCourseProgress");
                System.out.println("Progres gasit: " + progress);
                return progress;
            } else {
                System.out.println("Nu exista niciun progres cu ID-ul: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea progresului: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CourseProgress> readAll() {
        String sql = "SELECT completion_percentage FROM course_progress";
        List<CourseProgress> progressList = new ArrayList<>();
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CourseProgress cp = new CourseProgress();
                cp.setCompletionPercentage(rs.getInt("completion_percentage"));
                progressList.add(cp);
            }
            AuditService.getInstance().log("readAllCourseProgress");
            System.out.println("Toate intrarile de progres au fost citite.");
            return progressList;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la citirea tuturor progreselor: " + e.getMessage(), e);
        }
    }

    public void update(CourseProgress progress, long studentId, long courseId) {
        String sql = "UPDATE course_progress SET completion_percentage = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, progress.getCompletionPercentage());
            stmt.setLong(2, studentId);
            stmt.setLong(3, courseId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("updateCourseProgress");
                System.out.println("Progresul a fost actualizat.");
            } else {
                System.out.println("Progresul nu a fost gasit pentru update.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizarea progresului: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(CourseProgress progress) {
        throw new UnsupportedOperationException("Foloseste update(progress, studentId, courseId) in schimb.");
    }

    @Override
    public void create(CourseProgress progress) {
        throw new UnsupportedOperationException("Foloseste create(progress, studentId, courseId) in schimb.");
    }

    public void deleteByStudentAndCourse(long studentId, long courseId) {
        String sql = "DELETE FROM course_progress WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, studentId);
            stmt.setLong(2, courseId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("deleteCourseProgress");
                System.out.println("Progresul a fost sters.");
            } else {
                System.out.println("Progresul nu a fost gasit pentru stergere.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea progresului: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM course_progress WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AuditService.getInstance().log("deleteCourseProgress");
                System.out.println("Progresul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("Nu exista niciun progres cu ID-ul: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea progresului: " + e.getMessage(), e);
        }
    }
}
