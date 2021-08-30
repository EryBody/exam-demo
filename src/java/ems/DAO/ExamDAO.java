/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.ExamBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ExamDAO {
    
    Connection conn = null;
    JConnect jconnect = new JConnect();
    
    CoursesDAO cdao = new CoursesDAO();
    AnswerDAO adao = new AnswerDAO();

    public int startExam(String cName, int sId) {
        int examId = 0;
        conn = jconnect.getNewConnection();
        try {
            String sql = "INSERT into exams(course_name,date,start_time,exam_time,std_id,total_Marks) "
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cName);
            pstm.setString(2, getFormatedDate(LocalDate.now().toString()));
            pstm.setString(3, LocalTime.now().toString());
            pstm.setString(4, cdao.getCourseTimeByName(cName));
            pstm.setInt(5, sId);
            pstm.setInt(6, cdao.getTotalMarksByName(cName));
            pstm.executeUpdate();
            pstm.close();
            examId = getLastExamId();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examId;
    }
    
     public int startExamination (String courseName, int studentId) {
        int examId = 0;
        int status = -1;
        String query = "insert into exams values(?,?,?,?,?,?)";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseName);
            ps.setString(2, getFormatedDate(LocalDate.now().toString()));
            ps.setString(3, LocalTime.now().toString());
            ps.setString(4, cdao.getCourseTimeByName(courseName));
            ps.setInt(5, studentId);
            ps.setInt(6, cdao.getTotalMarksByName(courseName));
            ps.executeUpdate();
            ps.close();
            examId = getLastExamId();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return examId;
    }

    public int getLastExamId() {
        int id = 0;
        conn = jconnect.getNewConnection();
        
        try {

            String sql = "Select * from exams";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String getStartTime(int examId) {
        String time = "";
        conn = jconnect.getNewConnection();
        try {

            String sql = "Select start_time from exams where exam_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, examId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                time = rs.getString(1);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    private String getFormatedDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private String getNormalDate(String date) {
        String[] d = date.split("-");
        return d[2] + "-" + d[1] + "-" + d[0];
    }

    private String getFormatedTime(String time) {
        if (time != null) {
            LocalTime localTime = LocalTime.parse(time);
            return localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {

            return "-";
        }
    }

    public int getRemainingTime(int examId) {
        int time = 0;
        conn = jconnect.getNewConnection();
        try {

            String sql = "Select start_time,exam_time from exams where exam_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, examId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //totalTime-(Math.abs(currentTime-examStartTime))
                //Duration.between(first,sec) returns difference between 2 dates or 2 times
                time = Integer.parseInt(rs.getString(2)) - (int) Math.abs((Duration.between(LocalTime.now(), LocalTime.parse(rs.getString(1))).getSeconds() / 60));
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(time);
        return time;
    }
    
    public List<ExamBean> getStudentResults() {

        List<ExamBean> examBean = new ArrayList<ExamBean>();

        ResultSet rs = null;

        String query = "select * from exams";
        Statement statement = null;
        //calling the connection class 
        conn = jconnect.getNewConnection();

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                ExamBean bean = new ExamBean();

                bean.setExamId(rs.getInt("exam_id"));
                bean.setStdId(rs.getString("std_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setTotalMarks(rs.getString("total_marks"));
                bean.setObtMarks(rs.getString("obt_marks"));
                bean.setDate(rs.getString("date"));
                bean.setStartTime(rs.getString("start_time"));
                bean.setEndTime(rs.getString("end_time"));
                bean.setExamTime(rs.getString("exam_time"));
                bean.setStatus(rs.getString("status"));

                examBean.add(bean);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return examBean;
    }

    public ArrayList getAllResultsFromExams(int stdId) {
        conn = jconnect.getNewConnection();
        ArrayList list = new ArrayList();
        ExamBean exam = null;
        try {
            PreparedStatement pstm = conn.prepareStatement("select * from exams where std_id=? order by date desc");
            pstm.setInt(1, stdId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ExamBean bean = new ExamBean();

                bean.setExamId(rs.getInt("exam_id"));
                bean.setStdId(rs.getString("std_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setTotalMarks(rs.getString("total_marks"));
                bean.setObtMarks(rs.getString("obt_marks"));
                bean.setDate(rs.getString("date"));
                bean.setStartTime(rs.getString("start_time"));
                bean.setEndTime(rs.getString("end_time"));
                bean.setExamTime(rs.getString("exam_time"));
                bean.setStatus(rs.getString("status"));

                list.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void calculateResult(int eid, int tMarks, String endTime, int size) {
            conn = jconnect.getNewConnection();
        try {
            String sql = "update exams "
                    + "set obt_marks=?, end_time=?,status=? "
                    + "where exam_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            int obt = adao.getObtMarks(eid, tMarks, size);
            pstm.setInt(1, obt);
            pstm.setString(2, endTime);
            float percent = ((obt * 100) / tMarks);
            if (percent >= 70.0) {
                pstm.setString(3, "A");
            }
            else if(percent>69.0 && percent<=60.0){
                pstm.setString(3, "B");
            }
            else if(percent>59.0 && percent<=50.0) {
                pstm.setString(3, "C");
            }
            else if(percent>45.0 && percent<=40.0) {
                pstm.setString(3, "D");
            }
            else if(percent>44.0 && percent<=39.0) {
                pstm.setString(3, "E");
            }
            else if(percent<=39.0) {
                pstm.setString(3, "F");
            }
            else{
                pstm.setString(3, "Not a Valid Score");
            }
            pstm.setInt(4, eid);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ExamBean getStudentResultByExamId(int examId) {

        ExamBean bean = new ExamBean();

        ResultSet rs = null;

        String query = "select * from exams where exam_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, examId);

            rs = ps.executeQuery();
            if (rs.next()) {

                bean.setExamId(rs.getInt("exam_id"));
                bean.setStdId(rs.getString("std_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setTotalMarks(rs.getString("total_marks"));
                bean.setObtMarks(rs.getString("obt_marks"));
                bean.setDate(rs.getString("date"));
                bean.setStartTime(rs.getString("start_time"));
                bean.setEndTime(rs.getString("end_time"));
                bean.setExamTime(rs.getString("exam_time"));
                bean.setStatus(rs.getString("status"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
    
     public int getActiveStudentExam(int studentId) {

        int count = 0;

        ResultSet rs = null;

        String query = "SELECT COUNT(*) as total FROM exams where std_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentId);

            rs = ps.executeQuery();
            if (rs.next()) {
                count = (rs.getInt("total"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public ExamBean getResultByExamId(int examId) {
        conn = jconnect.getNewConnection();
        ExamBean exam = null;
        try {
            PreparedStatement pstm = conn.prepareStatement("select * from exams where exam_id=?");
            pstm.setInt(1, examId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ExamBean bean = new ExamBean();

                bean.setExamId(rs.getInt("exam_id"));
                bean.setStdId(rs.getString("std_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setTotalMarks(rs.getString("total_marks"));
                bean.setObtMarks(rs.getString("obt_marks"));
                bean.setDate(rs.getString("date"));
                bean.setStartTime(rs.getString("start_time"));
                bean.setEndTime(rs.getString("end_time"));
                bean.setExamTime(rs.getString("exam_time"));
                bean.setStatus(rs.getString("status"));

//                list.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exam;

    }
    
    public int getActiveExams() {
        int count = 0;
        ResultSet result = null;

        String query = "SELECT COUNT(*) as total FROM exams ";
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
    
    public static void main(String[] args) {
        
        ExamDAO dao = new ExamDAO();
//        int examId = dao.getLastExamId();
//        System.out.println("Exam ID: "+examId);
        
        ExamBean bean = dao.getStudentResultByExamId(5);
        int examIds = bean.getExamId();
        String stdId = bean.getStdId();
        String courseName = bean.getCourseName();
        String totalMarks = bean.getTotalMarks();
        String obtMarks = bean.getObtMarks();
        String date = bean.getDate();
        String startTime = bean.getStartTime();
        String endTime = bean.getEndTime();
        String examTime = bean.getExamTime();
        String status = bean.getStatus();
        
        System.out.println("Exam ID: "+examIds);
        System.out.println("stdId: "+stdId);
        System.out.println("courseName: "+courseName);
        System.out.println("totalMarks: "+totalMarks);
        System.out.println("obtMarks: "+obtMarks);
        System.out.println("date: "+date);
        System.out.println("startTime: "+startTime);
        System.out.println("endTime: "+endTime);
        System.out.println("examTime: "+examTime);
        System.out.println("status: "+status);
        
        System.out.println("Count: "+dao.getActiveExams());
    }

}
