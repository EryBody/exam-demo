/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.CoursesBean;
import ems.beans.QuestionBean;
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
public class QuestionDAO {
    
    Connection conn = null;
    JConnect jconnect = new JConnect();
    
    public int addNewQuestion(QuestionBean bean) {

        int status = -1;
        String query = "insert into questions values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, bean.getQuestionId());
            ps.setString(2, bean.getCourseName());
            ps.setString(3, bean.getQuestion());
            ps.setString(4, bean.getOpt1());
            ps.setString(5, bean.getOpt2());
            ps.setString(6, bean.getOpt3());
            ps.setString(7, bean.getOpt4());
            ps.setString(8, bean.getCorrect());

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

    public int updateQuestion(QuestionBean bean) {

        int status = -1;
        String query = "update questions set course_name = ?, question = ?, opt1 = ?, opt2 = ?, opt3 = ?, opt4 = ?, correct = ? where question_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            
            ps.setString(1, bean.getCourseName());
            ps.setString(2, bean.getQuestion());
            ps.setString(3, bean.getOpt1());
            ps.setString(4, bean.getOpt2());
            ps.setString(5, bean.getOpt3());
            ps.setString(6, bean.getOpt4());
            ps.setString(7, bean.getCorrect());
            ps.setInt(8, bean.getQuestionId());

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

    public int deleteQuestion(int questionId) {

        int status = -1;
        String query = "delete from questions where question_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, questionId);

            int count = ps.executeUpdate();
            if (count == 1) {
                status = 0;
            } else {
                status = 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public void delQuestion(int qId) {
        conn = jconnect.getNewConnection();
        try {
            String sql = "DELETE from questions where question_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, qId);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<QuestionBean> getAllQuestions(String courseName) {
        List<QuestionBean> list = new ArrayList<>();
        
        conn = jconnect.getNewConnection();
        try {

            String sql = "Select * from questions where course_name=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, courseName);
            
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QuestionBean bean = new QuestionBean();
                    bean.setQuestionId(rs.getInt("question_id"));
                    bean.setCourseName(rs.getString("course_name"));
                    bean.setQuestion(rs.getString("question"));
                    bean.setOpt1(rs.getString("opt1"));
                    bean.setOpt2(rs.getString("opt2"));
                    bean.setOpt3(rs.getString("opt3"));
                    bean.setOpt4(rs.getString("opt4"));
                    bean.setCorrect(rs.getString("correct"));
                    
                    list.add(bean);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getCorrectAnswer(int qid) {
        String ans = "";
        conn = jconnect.getNewConnection();

        try {
            PreparedStatement pstm = conn.prepareStatement("Select correct from questions where question_id=?");
            pstm.setInt(1, qid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ans = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ans;
    }

    public void addQuestion(String cName, String question, String opt1, String opt2, String opt3,
             String opt4, String correct) {
        conn = jconnect.getNewConnection();

        try {
            String sql = "INSERT into questions( `question`, `opt1`, `opt2`, `opt3`, `opt4`, `correct`,course_name)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, question);
            pstm.setString(2, opt1);
            pstm.setString(3, opt2);
            pstm.setString(4, opt3);
            pstm.setString(5, opt4);
            pstm.setString(6, correct);
            pstm.setString(7, cName);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<QuestionBean> getQuestions(String courseName, int start, int total) {
        List<QuestionBean> list = new ArrayList<>();
        conn = jconnect.getNewConnection();
        try {

            String sql = "Select * from questions where course_name=? LIMIT " +(start-1)+","+total;
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, courseName);
            ResultSet rs = pstm.executeQuery();
            QuestionBean question;
            while (rs.next()) {
                QuestionBean bean = new QuestionBean();
                    bean.setQuestionId(rs.getInt("question_id"));
                    bean.setCourseName(rs.getString("course_name"));
                    bean.setQuestion(rs.getString("question"));
                    bean.setOpt1(rs.getString("opt1"));
                    bean.setOpt2(rs.getString("opt2"));
                    bean.setOpt3(rs.getString("opt3"));
                    bean.setOpt4(rs.getString("opt4"));
                    bean.setCorrect(rs.getString("correct"));
                    
                list.add(bean);
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public QuestionBean getQuestionRecord(int questionId) {

        QuestionBean bean = new QuestionBean();

        ResultSet rs = null;

        String query = "select * from questions where question_id = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, questionId);

            rs = ps.executeQuery();
            if (rs.next()) {

                bean.setQuestionId(rs.getInt("question_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setQuestion(rs.getString("question"));
                bean.setOpt1(rs.getString("opt1"));
                bean.setOpt2(rs.getString("opt2"));
                bean.setOpt3(rs.getString("opt3"));
                bean.setOpt4(rs.getString("opt4"));
                bean.setCorrect(rs.getString("correct"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bean;
    }
     
     public List<QuestionBean> getQuestionByCourse(String courseName) {

        List<QuestionBean> list = new ArrayList<>();

        ResultSet rs = null;

        String query = "select * from questions where course_name = ?";
        PreparedStatement ps = null;

        conn = jconnect.getNewConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, courseName);

            rs = ps.executeQuery();
            if (rs.next()) {
                QuestionBean bean = new QuestionBean();
                bean.setQuestionId(rs.getInt("question_id"));
                bean.setCourseName(rs.getString("course_name"));
                bean.setQuestion(rs.getString("question"));
                bean.setOpt1(rs.getString("opt1"));
                bean.setOpt2(rs.getString("opt2"));
                bean.setOpt3(rs.getString("opt3"));
                bean.setOpt4(rs.getString("opt4"));
                bean.setCorrect(rs.getString("correct"));
                
                list.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
     
      public int getActiveQuestion() {
        int count = 0;
        ResultSet result = null;

        String query = "SELECT COUNT(*) as total FROM questions ";
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
      
      public int getQuestionSize(String courseName){
          
          int size = 0;
          ResultSet rs = null;
          String query = "select count(*) as total from questions where course_name =?";
          PreparedStatement ps = null;
          conn = jconnect.getNewConnection();
          
          try {
              ps = conn.prepareStatement (query);
              ps.setString(1, courseName);
              
              rs = ps.executeQuery();
              if (rs.next()) {
                  size = (rs.getInt("total"));
              }
          } catch (SQLException ex) {
              Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          return size;
      }
     
     public static void main(String[] args) {
         QuestionDAO dao = new QuestionDAO();
         QuestionBean bean = new QuestionBean();
         
//        //To Insert Records
//        bean.setQuestionId(10);
//        bean.setCourseName("CHE123");
//        bean.setQuestion("Perform Activities");
//        bean.setOpt1("Perform Activities");
//        bean.setOpt2("Perform Activities");
//        bean.setOpt3("Perform Activities");
//        bean.setOpt4("Perform Activities");
//        bean.setCorrect("Perform Activities");
//
//        int status = dao.updateQuestion(bean);
//        System.out.println("\tStatus: " + status);
         
        List<QuestionBean> userrole = dao.getQuestions("CHE123",1,2);
        for (QuestionBean user : userrole) {
//            
            System.out.println("\tUser Role Id: " + user.getQuestionId());
            System.out.println("\tUser Role: " + user.getCourseName());
            System.out.println("\tDescription: " + user.getQuestion());
            System.out.println("\tDescription: " + user.getOpt1());
            System.out.println("\tDescription: " + user.getOpt2());
            System.out.println("\tDescription: " + user.getOpt3());
            System.out.println("\tDescription: " + user.getOpt4());
            System.out.println("\tDescription: " + user.getCorrect());
//            
            System.out.println("*****************************************");
            System.out.println("");
        }
    }
}

