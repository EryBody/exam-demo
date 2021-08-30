/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

import CONN.JConnect;
import ems.beans.AnswerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class AnswerDAO {
    
    Connection conn = null;
    JConnect jconnect = new JConnect();
    
    QuestionDAO qdao = new QuestionDAO();
   

    public void insertAnswer(int eId, int qid, String question, String ans) {
        conn = jconnect.getNewConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement("insert into answers(exam_id,question,answer,correct_answer,status) "
                    + "Values(?,?,?,?,?)");
            pstm.setInt(1, eId);
            pstm.setString(2, question);
            pstm.setString(3, ans);
            String correct = qdao.getCorrectAnswer(qid);
            pstm.setString(4, correct);
            pstm.setString(5, getAnswerStatus(ans, correct));
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getAnswerStatus(String ans, String correct) {
        String resp = "";
        if (ans.equals(correct)) {
            resp = "correct";
        } else {
            resp = "incorrect";
        }
        return resp;

    }

    public ArrayList getAllAnswersByExamId(int examId) {
        conn = jconnect.getNewConnection();
        ArrayList list = new ArrayList();
        try {

            String sql = "Select * from answers where exam_id=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, examId);
            ResultSet rs = pstm.executeQuery();
            AnswerBean a;
            while (rs.next()) {
                AnswerBean bean = new AnswerBean();

                bean.setAnswer(rs.getString("answer"));
                bean.setCorrectAns(rs.getString("correct_answer"));
                bean.setQuestion(rs.getString("question"));
                bean.setStatus(rs.getString("status"));

                list.add(bean);
                
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getObtMarks(int examId, int tMarks, int size) {
        conn = jconnect.getNewConnection();
        int m = 0;

        try {
            PreparedStatement pstm = conn.prepareStatement("select count(answer_id) from answers "
                    + "where exam_id=? and status='correct'");
            pstm.setInt(1, examId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                m = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        float rat = (float) tMarks / size;
        System.out.println("Rat: "+rat);
        rat = m * rat;
        System.out.println("Rat After Calculation: "+rat);
        return m = (int) rat;
    }

}
