package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Courses;
public class CourseDao {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentManagement", "root", "Shiv@2021");
    }
    public boolean addCourse(Courses course) {
        try (Connection con = getConnection();
             var ps = con.prepareStatement("INSERT INTO Courses (course_name,duration,fees) VALUES (?,?,?)")) {
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getDuration());
            ps.setDouble(3, course.getFees());
            int x = ps.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<Courses> getAllCourses() {
        List<Courses> list = new ArrayList<>();
        try (Connection con = getConnection();
             var ps = con.prepareStatement("SELECT * FROM Courses")) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                Courses course = new Courses(rs.getInt("course_id"), rs.getString("course_name"),
                                             rs.getInt("duration"), rs.getDouble("fees"));
                list.add(course);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean updateCourse(Courses course) {
        try (Connection con = getConnection();
             var ps = con.prepareStatement("UPDATE Courses SET course_name=?, duration=?, fees=? WHERE course_id=?")) {
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getDuration());
            ps.setDouble(3, course.getFees());
            ps.setInt(4, course.getCourseId());
            int x = ps.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean deleteCourse(int courseId) {
        try (Connection con = getConnection();
             var ps = con.prepareStatement("DELETE FROM Courses WHERE course_id=?")) {
            ps.setInt(1, courseId);
            int x = ps.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Courses searchCourse(int courseId) {
        try (Connection con = getConnection();
             var ps = con.prepareStatement("SELECT * FROM Courses WHERE course_id=?")) {
            ps.setInt(1, courseId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return new Courses(rs.getInt("course_id"), rs.getString("course_name"),
                                   rs.getInt("duration"), rs.getDouble("fees"));
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
