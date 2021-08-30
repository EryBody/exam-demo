/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.model;

import Utility.UserTranslator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class LoginServlet extends HttpServlet {

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
            String rememberme = request.getParameter("checkRememberme");

            LoginModel lm = new LoginModel();

            String buttonName = request.getParameter("buttonSubmit");
            if (buttonName.equals("login")) {

                LoginBean user = lm.authenticateUser(username, upassword);
                if (user.getLoginStatus() == 0) {
                        request.setAttribute("loginMsg", "Please Wait While Administrator Approves Your Registration!!!");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                else if(user.getLoginStatus() == 1){
                    int userId = user.getUserId();
                    String userRoleId = user.getUserType();
                    String password = user.getPassword();
                    String firstName = user.getFirstName();
                    String lastName = user.getLastName();
                    String userName = user.getUserName();
                    String email = user.getEmail();
                    String contactNo = user.getContactNo();
                    String city = user.getCity();
                    String address = user.getAddress();
                    String entryDate = user.getEntryDate();

                    session.setAttribute("userId", userId);
                    session.setAttribute("userRoleId", userRoleId);
                    session.setAttribute("firstName", firstName);
                    session.setAttribute("lastName", lastName);
//                    session.setAttribute("password", password);
                    session.setAttribute("userName", userName);
                    session.setAttribute("email", email);
                    session.setAttribute("contactNo", contactNo);
                    session.setAttribute("city", city);
                    session.setAttribute("address", address);
                    session.setAttribute("entryDate", entryDate);
                    
                    if (userRoleId.equals(UserTranslator.ADMIN)) {
                        request.getRequestDispatcher("/main_home.jsp").forward(request, response);
                    } else if (userRoleId.equals(UserTranslator.LECTURER)) {
                        request.getRequestDispatcher("/main_home.jsp").forward(request, response);
                    }
                    else {
                        request.getRequestDispatcher("/main_home.jsp").forward(request, response);
                    }
                }
                else if(user.getLoginStatus() == 2){
                    int userId = user.getUserId();
                    String userRoleId = user.getUserType();
                    String password = user.getPassword();
                    String firstName = user.getFirstName();
                    String lastName = user.getLastName();
                    String userName = user.getUserName();
                    String email = user.getEmail();
                    String contactNo = user.getContactNo();
                    String city = user.getCity();
                    String address = user.getAddress();
                    String entryDate = user.getEntryDate();

                    session.setAttribute("userId", userId);
                    session.setAttribute("userRoleId", userRoleId);
                    session.setAttribute("firstName", firstName);
                    session.setAttribute("lastName", lastName);
//                    session.setAttribute("password", password);
                    session.setAttribute("userName", userName);
                    session.setAttribute("email", email);
                    session.setAttribute("contactNo", contactNo);
                    session.setAttribute("city", city);
                    session.setAttribute("address", address);
                    session.setAttribute("entryDate", entryDate);
                    
                    if (userRoleId.equals(UserTranslator.STUDENT)) {
                        request.getRequestDispatcher("/main_home.jsp").forward(request, response);
                    }
                    else {
                        request.getRequestDispatcher("/ems/student_error.jsp").forward(request, response);
                    }
                }
                else {
                    out.print("Login Faileds");
                    request.getRequestDispatcher("/main_home.jsp").forward(request, response);
                }
            } else {
                out.print("Button Name " + buttonName);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    out.print("userId: " + userId + "<br>");
//    out.print("userRoleId: " + userRoleId + "<br> ");
//    out.print("uPassword: " + password + " <br>");
//    out.print("firstName: " + firstName + "<br> ");
//    out.print("lastName: " + lastName + " <br>");
//    out.print("userName: " + userName + "<br> ");
//    out.print("email: " + email + " <br>");
//    out.print("contactNo: " + contactNo + " <br>");
//    out.print("city: " + city + " <br>");
//    out.print("address: " + address + " <br>");
//    out.print("entryDate: " + entryDate + " <br>");
    
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
