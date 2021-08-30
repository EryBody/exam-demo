/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.QuestionDAO;
import ems.beans.QuestionBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class QuestionServlet extends HttpServlet {

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
            
            int qid = Integer.parseInt(request.getParameter("questionId"));
            String courseName = request.getParameter("courseName");
            String question = request.getParameter("question");;
            String opt1 = request.getParameter("opt1");
            String opt2 = request.getParameter("opt2");
            String opt3 = request.getParameter("opt3");
            String opt4 = request.getParameter("opt4");
            String correct = request.getParameter("correct");
            
            QuestionDAO fdao = new QuestionDAO();
            
            String buttonName = request.getParameter("buttonName");
//            out.print("Button Name: " + buttonName);
            if(buttonName.equals("Submit")){
                
                QuestionBean ubean = new QuestionBean();
                ubean.setQuestionId(0);
                ubean.setCourseName(courseName);
                ubean.setQuestion(question);
                ubean.setOpt1(opt1);
                ubean.setOpt2(opt2);
                ubean.setOpt3(opt3);
                ubean.setOpt4(opt4);
                ubean.setCorrect(correct);
           
                int status = fdao.addNewQuestion(ubean);
                if (status==0) {
                    
                QuestionBean bean = new QuestionBean();
                    bean.setQuestionId(0);
                    bean.setCourseName(courseName);
                    bean.setQuestion(question);
                    bean.setOpt1(opt1);
                    bean.setOpt2(opt2);
                    bean.setOpt3(opt3);
                    bean.setOpt4(opt4);
                    bean.setCorrect(correct);
                        
                    getServletContext().getRequestDispatcher("/ems/question_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/question_form.jsp").forward(request, response);
                }

             }
             else if(buttonName.equals("Update")){
                
//                 out.print("questionId:"+qid);
//                 out.print("courseName:"+courseName);
//                 out.print("question:"+question);
//                 out.print("opt1:"+opt1);
//                 out.print("opt2:"+opt2);
//                 out.print("opt3:"+opt3);
//                 out.print("opt4:"+opt4);
//                 out.print("correct:"+correct);
                 
               QuestionBean ubean = new QuestionBean();
                ubean.setQuestionId(qid);
                ubean.setCourseName(courseName);
                ubean.setQuestion(question);
                ubean.setOpt1(opt1);
                ubean.setOpt2(opt2);
                ubean.setOpt3(opt3);
                ubean.setOpt4(opt4);
                ubean.setCorrect(correct);
           
                int status = fdao.updateQuestion(ubean);
                if (status==0) {
                QuestionBean bean = new QuestionBean();
                    bean.setQuestionId(qid);
                    bean.setCourseName(courseName);
                    bean.setQuestion(question);
                    bean.setOpt1(opt1);
                    bean.setOpt2(opt2);
                    bean.setOpt3(opt3);
                    bean.setOpt4(opt4);
                    bean.setCorrect(correct);
                        
                    getServletContext().getRequestDispatcher("/ems/question_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/question_form.jsp").forward(request, response);
                }

            }
             else if(buttonName.equals("Delete")){
                 
                int status = fdao.deleteQuestion(qid);
                if (status==0) {
                    
                QuestionBean bean = new QuestionBean();
                    bean.setQuestionId(qid);
                    bean.setCourseName(courseName);
                    bean.setQuestion(question);
                    bean.setOpt1(opt1);
                    bean.setOpt2(opt2);
                    bean.setOpt3(opt3);
                    bean.setOpt4(opt4);
                    bean.setCorrect(correct);
                        
                    getServletContext().getRequestDispatcher("/ems/question_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/question_form.jsp").forward(request, response);
                }

             }else {

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
