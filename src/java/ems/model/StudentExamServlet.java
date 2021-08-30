/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.AnswerDAO;
import ems.DAO.CoursesDAO;
import ems.DAO.ExamDAO;
import ems.DAO.QuestionDAO;
import ems.beans.CoursesBean;
import ems.beans.QuestionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author user
 */
public class StudentExamServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            QuestionBean bean = new QuestionBean();
            CoursesBean cbean = new CoursesBean();
            QuestionDAO dao = new QuestionDAO();
            ExamDAO examdao = new ExamDAO();
            AnswerDAO ansDao = new AnswerDAO();

//            String buttonName = request.getParameter("buttonName");
            String courseName = request.getParameter("courseName");
            String[] selectedAnswers = request.getParameterValues("opt");
            String[] questionId = request.getParameterValues("qid");

            String value = "";
            int qid = 0;
            String question = "";

            int examId = 0;
            if (session.getAttribute("examinationId") != null) {
                examId = (int) session.getAttribute("examinationId");
            }
            if (selectedAnswers != null) {

//                out.print("examId: "+examId+"<br>");
//                out.print("vId: "+vId+"<br>");
//                out.print("courseName: "+courseName+"<br>");
//                out.print("selectedAnswers: "+Arrays.toString(selectedAnswers)+"<br>");
//                out.print("questionId: "+Arrays.toString(questionId)+"<br>");

                for (int i = 0; i < selectedAnswers.length; i++) {
                    value = selectedAnswers[i];
                    qid = Integer.parseInt(questionId[i]);
                    bean = dao.getQuestionRecord(qid);
                    question = bean.getQuestion();
                    ansDao.insertAnswer(examId, qid, question, value);
                }

                if (examId != 0) {
                    String endtime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    int questionSize = dao.getQuestionSize(courseName);
                    int tMarks = 0;

                    cbean = new CoursesDAO().getCourseRecord(courseName);
                    tMarks = cbean.getTotalScore();
                    
//                    System.out.println("Total Score: "+tMarks);
//                    System.out.println("Question Size: "+questionSize);
//                    System.out.println("examId: "+examId);

                    examdao.calculateResult(examId, tMarks, endtime, questionSize);
                    getServletContext().getRequestDispatcher("/ems/check_student_result.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/ems/question_error.jsp").forward(request, response);
                }
            } else {
                getServletContext().getRequestDispatcher("/ems/question_error.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
