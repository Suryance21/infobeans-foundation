package com.dao;
import com.model.Students;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class StudentDao {
    private Connection getConnection() throws  SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentManagement","root","Shiv@2021");
    }
	public boolean addStudent(Students s)  {
        try(
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Students values(0,?,?,?,?,?,?,?,?,?)");) {
            ps.setString(1, s.getRollNumber());
            ps.setString(2, s.getName());
            ps.setString(3, s.getGender());
            ps.setDate(4, Date.valueOf(s.getDob()));
            ps.setString(5, s.getPhone());
            ps.setString(6, s.getEmail());
            ps.setInt(7, s.getCourseId());
            ps.setDate(8, Date.valueOf(s.getAdmissionDate()));
            ps.setString(9, s.getAddress());
            int x = ps.executeUpdate();
            return x > 0;

        }catch(SQLException e){
            System.out.println(e.getMessage());;
            return false;
        }

    }
    public List<Students> getAllStudent(){
        List<Students> list = new ArrayList<>();  
        try(
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Students");){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Students s = new Students();
                s.setStudentId(rs.getInt(1));
                s.setRollNumber(rs.getString(2));
                s.setName(rs.getString(3));
                s.setGender(rs.getString(4));
                s.setDob(rs.getDate(5).toLocalDate());
                s.setPhone(rs.getString(6));
                s.setEmail(rs.getString(7));
                s.setCourseId(rs.getInt(8));
                s.setAdmissionDate(rs.getDate(9).toLocalDate());
                s.setAddress(rs.getString(10));
                list.add(s);
            }
            return list;

         }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;

         }
    }
    public Students searchStudent(String rollNo) {
        try(
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Students WHERE roll_Number = ?");){
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Students s = new Students();
                s.setStudentId(rs.getInt(1));
                s.setRollNumber(rs.getString(2));
                s.setName(rs.getString(3));
                s.setGender(rs.getString(4));
                s.setDob(rs.getDate(5).toLocalDate());
                s.setPhone(rs.getString(6));
                s.setEmail(rs.getString(7));
                s.setCourseId(rs.getInt(8));
                s.setAdmissionDate(rs.getDate(9).toLocalDate());
                s.setAddress(rs.getString(10));
                return s;
            }
            return null;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean updateStudent(Students s) {
        try(
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Students SET  address = ?, phone = ?, email = ? WHERE roll_Number = ?");) {
            ps.setString(1, s.getAddress());
            ps.setString(2, s.getPhone());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getRollNumber());
            int x = ps.executeUpdate();
            return x > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        } 
    }
    public boolean deleteStudent(String rollNo) {
        try(
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Students WHERE roll_Number = ?");) {
            ps.setString(1, rollNo);
            int x = ps.executeUpdate();
            return x > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
