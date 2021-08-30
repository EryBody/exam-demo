/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import LoginModel.LoginBean;
import LoginModel.LoginModel;
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
 * @author Administrator
 */
public class DBILoginServlet extends HttpServlet {

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
            
            HttpSession session = request.getSession(true);
            
           
            String username = request.getParameter("textUsername");
            String upassword = request.getParameter("textPassword");
            String rememberme = request.getParameter("rememberme");
            
            LoginModel lm = new LoginModel();
            
            String buttonName = request.getParameter("buttonSubmit");
            if(buttonName.equals("login")){
                
                LoginBean user = lm.authenticateUser(username, upassword);
                if(user.getLoginStatus()==0){
                    
                    
                    String userId = user.getUsername();
                    String userRole = user.getUserRole();
                    String firstName = user.getFirstName();
                    String lastName = user.getLastName();
                    String gender = user.getGender();
                    String mobile = user.getMobile();
                    String email = user.getEmail();
                    int userStatus = user.getUserStatus();
                    String entryDate = user.getEntryDate();
                    
                    if (userStatus==0) {
                        
                        session.setAttribute("userRole", userRole);
                        session.setAttribute("firstName", firstName);
                        session.setAttribute("lastName", lastName);
                        session.setAttribute("gender", gender);
                        session.setAttribute("mobile", mobile);
                        session.setAttribute("lastName", lastName);
                        session.setAttribute("email", email);
                        session.setAttribute("userStatus", userStatus);
                        session.setAttribute("entryDate", entryDate);

                        session.setAttribute("userId", userId);

//                        if(userRole.equals("UR02") || userRole.equals("UR03") || userRole.equals("UR04")){
//
//                             getServletContext().getRequestDispatcher("/multifactor_screen.jsp").forward(request, response);
//                        }
//                        else if(userRole.equals("UR01")){
//                            Cart cart;
//                            synchronized (session) {  
//                                cart = (Cart) session.getAttribute("cart");
//                                    if (cart == null) { 
//                                        cart = new Cart();
//                                        session.setAttribute("cart", cart); 
//                                }
//                            } 
//                            int id = 1;
//                            session.setAttribute("id", id);
//                        }

                        if(userRole.equals(UserTranslator.ADMIN)){
                            getServletContext().getRequestDispatcher("/main_home_admin.jsp").forward(request, response);
                        }
                        else if(userRole.equals(UserTranslator.LECTURER)){
                            getServletContext().getRequestDispatcher("/main_home_lecturer.jsp").forward(request, response);
                        }
                        else if(userRole.equals(UserTranslator.STUDENT)){
                            getServletContext().getRequestDispatcher("/main_home_student.jsp").forward(request, response);
                        }
                        else{
                            getServletContext().getRequestDispatcher("/main_home_admin.jsp").forward(request, response);
                        }
                        
                    }
                    else if (userStatus==1) {
                        
                        request.setAttribute("msg", "Contact Admin for Activation!");
                        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                        
                    }
                    
                }
                else
                {
                    request.setAttribute("err_msg", "Invalid Username/Password!");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
            else
            {
                out.print("button name: " + buttonName);
            }
            
        }catch(Exception ex){
            
            ex.printStackTrace();
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
