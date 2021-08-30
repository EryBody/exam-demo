/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ControllerServlet extends HttpServlet {

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
             String action = request.getParameter("action");
             
             if (action.equals("dashboard")) {
                getServletContext().getRequestDispatcher("/main_home.jsp").forward(request, response);
            }
             if (action.equals("login")) {
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
             if (action.equals("logout")) {
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
             if (action.equals("student-action")) {
                getServletContext().getRequestDispatcher("/student_list.jsp").forward(request, response);
            }
              if(action.trim().equals("temp-login-action")){
                getServletContext().getRequestDispatcher("/LoginServletTemp").forward(request, response);
            }
              if(action.trim().equals("login-action")){
                getServletContext().getRequestDispatcher("/LoginServlet").forward(request, response);
            }
              if(action.trim().equals("user-role-form")){
                getServletContext().getRequestDispatcher("/ems/user_role_form.jsp").forward(request, response);
            }
              if(action.trim().equals("user-role-list")){
                getServletContext().getRequestDispatcher("/ems/user_role_list.jsp").forward(request, response);
            }
              if(action.trim().equals("user-role-action")){
                getServletContext().getRequestDispatcher("/UserRoleServlet").forward(request, response);
            }
              if(action.trim().equals("user-list")){
                getServletContext().getRequestDispatcher("/ems/user_list.jsp").forward(request, response);
            }
              if(action.trim().equals("user-form")){
                getServletContext().getRequestDispatcher("/ems/user_form.jsp").forward(request, response);
            }
              if(action.trim().equals("user-action")){
                getServletContext().getRequestDispatcher("/UserServlet").forward(request, response);
            }
              if(action.trim().equals("my-profile")){
                getServletContext().getRequestDispatcher("/ems/my_profile.jsp").forward(request, response);
            }
              if(action.trim().equals("edit-profile-action")){
                getServletContext().getRequestDispatcher("/EditProfile").forward(request, response);
            }
              if(action.trim().equals("course-form")){
                getServletContext().getRequestDispatcher("/ems/course_form.jsp").forward(request, response);
            }
              if(action.trim().equals("course-list")){
                getServletContext().getRequestDispatcher("/ems/course_list.jsp").forward(request, response);
            }
              if(action.trim().equals("course-action")){
                getServletContext().getRequestDispatcher("/CourseServlet").forward(request, response);
            }
              if(action.trim().equals("question-list")){
                getServletContext().getRequestDispatcher("/ems/question_list.jsp").forward(request, response);
            }
              if(action.trim().equals("question-form")){
                getServletContext().getRequestDispatcher("/ems/question_form.jsp").forward(request, response);
            }
              if(action.trim().equals("question-action")){
                getServletContext().getRequestDispatcher("/QuestionServlet").forward(request, response);
            }
              if(action.trim().equals("cascade-by-course")){
                getServletContext().getRequestDispatcher("/ems/question_by_course_form.jsp").forward(request, response);
            }
              if(action.trim().equals("student-course")){
                getServletContext().getRequestDispatcher("/ems/question_by_student.jsp").forward(request, response);
            }
              if(action.trim().equals("student-exam")){
                getServletContext().getRequestDispatcher("/ems/student_exams.jsp").forward(request, response);
            }
              if(action.trim().equals("student-exam-action")){
                getServletContext().getRequestDispatcher("/StudentExamServlet").forward(request, response);
            }
              if(action.trim().equals("student-result-list")){
                getServletContext().getRequestDispatcher("/ems/student_result_list.jsp").forward(request, response);
            }
              if(action.trim().equals("admin-result-list")){
                getServletContext().getRequestDispatcher("/ems/admin_result_list.jsp").forward(request, response);
            }
              if(action.trim().equals("check-result")){
                getServletContext().getRequestDispatcher("/ems/check_student_result.jsp").forward(request, response);
            }
              if(action.trim().equals("register")){
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            }
              if(action.trim().equals("register-action")){
                getServletContext().getRequestDispatcher("/RegisterServlet").forward(request, response);
            }
              if(action.trim().equals("student-list")){
                getServletContext().getRequestDispatcher("/ems/student_list.jsp").forward(request, response);
            }
              if(action.trim().equals("get-in-touch")){
                getServletContext().getRequestDispatcher("/ems/student_help.jsp").forward(request, response);
            }
              if(action.trim().equals("do")){
                getServletContext().getRequestDispatcher("/ems/student_exams.jsp").forward(request, response);
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
