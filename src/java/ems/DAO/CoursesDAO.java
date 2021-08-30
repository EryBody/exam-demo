/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.CoursesBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class CoursesDAO {
    
    Connection conn = null;
    JConnect jconnect = new JConnect();

    public List<CoursesBean> getAllCourses() {
        List<CoursesBean> list = new ArrayList<>();
        
        conn = jconnect.getNewConnection();
        
        try {
            String sql = "SELECT * from courses";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CoursesBean bean = new CoursesBean();

                bean.setCourseName(rs.getString("course_name"));
                bean.setTime(rs.getString("time"));
                bean.setTotalScore(rs.getInt("total_marks"));

                list.add(bean);
                
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<CoursesBean> getCourses() {

        List<CoursesBean> courses = new ArrayList<CoursesBean>();

        ResultSet result = null;

        String query = "select * from courses";
        Statement statement = null;
        //calling the connection class 
        conn = jconnect.getNewConnection();

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                CoursesBean bean = new CoursesBean();

                bean.setCourseName(result.getString("course_name"));
                bean.setTime(result.getString("time"));
                bean.setTotalScore(result.getInt("total_marks"));

                courses.add(bean);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }
    
    public int addNewCourse(CoursesBean bean) {

        int status = -1;
        String query = "insert into courses values(?,?,?)";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, bean.getCourseName());
            ps.setInt(2, bean.getTotalScore());
            ps.setString(3, bean.getTime());

            int count = ps.executeUpdate();
            
            if (count == 1) {
                status = 0;
            } else {
                status = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int updateCourse(CoursesBean bean) {

        int status = -1;
        String query = "update courses set total_marks = ?, time = ? where course_name = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, bean.getTotalScore());
            ps.setString(2, bean.getTime());
            ps.setString(3, bean.getCourseName());

            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
                
            } else {
                status = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int deleteCourse(String courseName) {

        int status = -1;
        String query = "delete from courses where course_name = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseName);

            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
            } else {
                status = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

//    public void addNewCourse(String courseName, int tMarks, String time) {
//        try {
//            String sql = "INSERT into courses(course_name,total_marks,time) Values(?,?,?)";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(1, courseName);
//            pstm.setInt(2, tMarks);
//            pstm.setString(3, time);
//            pstm.executeUpdate();
//            pstm.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void delCourse(String cName) {
        conn = jconnect.getNewConnection();
        try {
            String sql = "DELETE from courses where course_name=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cName);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCourseTimeByName(String cName) {
        String c = null;
        conn = jconnect.getNewConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement("Select time from courses where course_name=?");
            pstm.setString(1, cName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                c = rs.getString(1);
            }
            pstm.close();
        } catch (Exception e) {
            Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return c;
    }

    public int getTotalMarksByName(String cName) {
        conn = jconnect.getNewConnection();
        int marks = 0;
        try {
            PreparedStatement pstm = conn.prepareStatement("Select total_marks from courses where course_name=?");
            pstm.setString(1, cName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                marks = rs.getInt(1);
                System.out.println(rs.getInt(1));
            }
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return marks;
    }
    
     public CoursesBean getCourseRecord(String courseName) {

        CoursesBean bean = new CoursesBean();

        ResultSet rs = null;

        String query = "select * from courses where course_name = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseName);

            rs = ps.executeQuery();
            if (rs.next()) {

                bean.setCourseName(rs.getString("course_name"));
                bean.setTime(rs.getString("time"));
                bean.setTotalScore(rs.getInt("total_marks"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
     
     public int getActiveCourses() {
        int count = 0;
        ResultSet result = null;

        String query = "SELECT COUNT(*) as total FROM courses ";
        Statement stmt = null;

        conn = jconnect.getNewConnection();

        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
            if (result.next()) {
                count = result.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

}
