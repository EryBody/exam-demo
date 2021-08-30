/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.model;

import ems.DAO.UserDAO;
import ems.beans.UserBean;
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
 * @author user
 */
public class RegisterServlet extends HttpServlet {

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

            int userId = 0;
            String userRoleId = request.getParameter("userRole");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String contact = request.getParameter("mobileNumber");
            String city = request.getParameter("city");
            int loginStatus = 0;
            String entryDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

//            out.print("UserId" + userId+ "<br>");
//            out.print("UserId" + userRoleId+ "<br>");
//            out.print("UserId" + firstName+ "<br>");
//            out.print("UserId" + lastName+ "<br>");
//            out.print("UserId" + userName+ "<br>");
//            out.print("UserId" + email+ "<br>");
//            out.print("UserId" + password+ "<br>");
//            out.print("UserId" + contact+ "<br>");
//            out.print("UserId" + city+ "<br>");
//            out.print("UserId" + loginStatus+ "<br>");
//            out.print("UserId" + entryDate+ "<br>");
            
            
            UserDAO udao = new UserDAO();

            String buttonName = request.getParameter("buttonRegister");
            if (buttonName.equals("register")) {

                UserBean ubean = new UserBean();
                ubean.setUserRoleId(userRoleId);
                ubean.setFirstName(firstName);
                ubean.setLastName(lastName);
                ubean.setUserName(userName);
                ubean.setEmail(email);
                ubean.setPassword(password);
                ubean.setContact(contact);
                ubean.setCity(city);
                ubean.setLoginStatus(loginStatus);
                ubean.setEntryDate(entryDate);

                int status = udao.RegisterUser(ubean);
                if (status == 0) {
                    request.setAttribute("registerMsg", "Registration Sucessful!!!");
                    getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                } else {
                    request.setAttribute("errRegisterMsg", "Registration Failed, Please Fill The Form Again.");
                    getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                }

            } else {

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
