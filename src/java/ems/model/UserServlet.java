/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.StudentDAO;
import ems.DAO.UserDAO;
import ems.DAO.UserRoleDAO;
import ems.beans.UserBean;
import ems.beans.UserRoleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class UserServlet extends HttpServlet {

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
            
            int userId = Integer.parseInt(request.getParameter("userId"));
            String userRoleId = request.getParameter("userRoleId");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String contact = request.getParameter("contact");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            int loginStatus = Integer.parseInt(request.getParameter("loginStatus"));
            String entryDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            StudentDAO fdao = new StudentDAO();
            UserDAO udao = new UserDAO();
            
//            String currentDT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            String buttonName = request.getParameter("buttonName");
//            out.print("Button Name: " + buttonName);
            if(buttonName.equals("Submit")){
                
                UserBean ubean = new UserBean();
                ubean.setUserRoleId(userRoleId);
                ubean.setFirstName(firstName);
                ubean.setLastName(lastName);
                ubean.setUserName(userName);
                ubean.setEmail(email);
                ubean.setPassword(password);
                ubean.setContact(contact);
                ubean.setCity(city);
                ubean.setAddress(address);
                ubean.setLoginStatus(loginStatus);
                ubean.setEntryDate(entryDate);
           
                int status = fdao.addNewStudent(ubean);
                if (status==0) {
                    getServletContext().getRequestDispatcher("/ems/user_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_form.jsp").forward(request, response);
                }

             }
             else if(buttonName.equals("Update")){
                 
                UserBean ubean = new UserBean();
                ubean.setUserId(userId);
                ubean.setFirstName(firstName);
                ubean.setLastName(lastName);
                ubean.setUserName(userName);
                ubean.setEmail(email);
                ubean.setPassword(password);
                ubean.setUserRoleId(userRoleId);
                ubean.setContact(contact);
                ubean.setCity(city);
                ubean.setAddress(address);
                ubean.setLoginStatus(loginStatus);
           
                int status = fdao.updateStudent(ubean);
                if (status==0) {
                    getServletContext().getRequestDispatcher("/ems/user_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_form.jsp").forward(request, response);
                }

            }
             else if(buttonName.equals("Delete")){
                 
                int status = udao.delUser(userId);
                if (status==0) {
                    getServletContext().getRequestDispatcher("/ems/user_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_form.jsp").forward(request, response);
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
