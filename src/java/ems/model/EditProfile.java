/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.StudentDAO;
import ems.beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class EditProfile extends HttpServlet {

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
            int userId = Integer.parseInt(request.getParameter("userId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = "password";
            String contact = request.getParameter("contact");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            
            StudentDAO fdao = new StudentDAO();
            String buttonName = request.getParameter("buttonName");
            
            String updateStatus = "";
            
            if(buttonName.equals("Update")){
                
                UserBean ubean = new UserBean();
                ubean.setUserId(userId);
                ubean.setFirstName(firstName);
                ubean.setLastName(lastName);
                ubean.setUserName(userName);
                ubean.setEmail(email);
                ubean.setPassword(password);
                ubean.setContact(contact);
                ubean.setCity(city);
                ubean.setAddress(address);
           
                int status = fdao.updateProfile(ubean);
                if (status==0) {
                    updateStatus = "1";
                    request.setAttribute("err_msg", updateStatus);
                    getServletContext().getRequestDispatcher("/ems/my_profile.jsp").forward(request, response);
                }
                else{
                    updateStatus = "0";
                    request.setAttribute("err_msg", updateStatus);
                }
//                request.setAttribute("err_msg", updateStatus);
                 
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
