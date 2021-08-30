/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.UserRoleDAO;
import ems.beans.UserRoleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "UserRoleServlet", urlPatterns = {"/UserRoleServlet"})
public class UserRoleServlet extends HttpServlet {

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
            
//            LoginBean user = (LoginBean)session.getAttribute("user");
            
            String userRoleId = request.getParameter("userRoleId");
            String userRole = request.getParameter("userRole");
            String description = request.getParameter("desc");
            String entryDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            UserRoleDAO fdao = new UserRoleDAO();
            
//            String currentDT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            String buttonName = request.getParameter("buttonName");
//            out.print("Button Name: " + buttonName);
            if(buttonName.equals("Submit")){
                
                UserRoleBean ubean = new UserRoleBean();
                ubean.setUserRoleId(userRoleId);
                ubean.setUserRole(userRole);
                ubean.setDescription(description);
                ubean.setEntryDate(entryDate);
           
                int status = fdao.addUserRole(ubean);
                if (status==0) {
                    
                    UserRoleBean bean = new UserRoleBean();
                        bean.setUserRoleId(userRoleId);
                        bean.setUserRole(userRole);
                        bean.setDescription(description);
                        bean.setEntryDate(entryDate);
                        
                    getServletContext().getRequestDispatcher("/ems/user_role_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_role_form.jsp").forward(request, response);
                }

             }
             else if(buttonName.equals("Update")){
                
                UserRoleBean ubean = new UserRoleBean();
                ubean.setUserRoleId(userRoleId);
                ubean.setUserRole(userRole);
                ubean.setDescription(description);
                ubean.setEntryDate(entryDate);
           
                int status = fdao.updateUserRoleRecord(ubean);
                if (status==0) {
                    
                    UserRoleBean bean = new UserRoleBean();
                        bean.setUserRoleId(userRoleId);
                        bean.setUserRole(userRole);
                        bean.setDescription(description);
                        
                    getServletContext().getRequestDispatcher("/ems/user_role_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_role_form.jsp").forward(request, response);
                }

            }
             else if(buttonName.equals("Delete")){
                 
                int status = fdao.deleteUserRole(userRoleId);
                if (status==0) {
                    
                    UserRoleBean bean = new UserRoleBean();
                        bean.setUserRoleId(userRoleId);
                        bean.setUserRole(userRole);
                        bean.setDescription(description);
                        bean.setEntryDate(entryDate);
                    
                    getServletContext().getRequestDispatcher("/ems/user_role_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/user_role_form.jsp").forward(request, response);
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
