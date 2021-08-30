/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import LoginModel.LoginModel;
import Utility.UserTranslator;
import ems.DAO.UserDAO;
import ems.beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class LoginServletTemp extends HttpServlet {

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
            
            HttpSession session = request.getSession(true);

            String username = request.getParameter("textUsername");
            String upassword = request.getParameter("textPassword");
            String rememberme = request.getParameter("rememberme");
            
            String convertedUsername = ""; 
            
            if(username.equals("admin@gmail.com")){
                convertedUsername = UserTranslator.ADMIN;
            }else if(username.equals("lecturer@gmail.com")){
                convertedUsername = UserTranslator.LECTURER;
            }else if(username.equals("student@gmail.com")){
                convertedUsername = UserTranslator.STUDENT;
            }else{
//                session.setAttribute("login_error", "Invalid Username or Password");
                out.print("<center><h1>Invalid Username or Password</h1></center>");
            }
            
            session.setAttribute("tempUser", username);

//            LoginModel lm = new LoginModel();
            String buttonName = request.getParameter("buttonSubmit");
            if (buttonName.equals("login")) {
                
                if (convertedUsername.equals(UserTranslator.ADMIN)) {
                    getServletContext().getRequestDispatcher("/main_home.jsp").forward(request, response);
                } else if (convertedUsername.equals(UserTranslator.LECTURER)) {
                    getServletContext().getRequestDispatcher("/main_home.jsp").forward(request, response);
                } else if (convertedUsername.equals(UserTranslator.STUDENT)) {
                    getServletContext().getRequestDispatcher("/main_home.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/main_home.jsp").forward(request, response);
                }
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
