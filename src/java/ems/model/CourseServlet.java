/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.model;

import ems.DAO.CoursesDAO;
import ems.DAO.UserRoleDAO;
import ems.beans.CoursesBean;
import ems.beans.UserRoleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class CourseServlet extends HttpServlet {

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
            
            String courseName = request.getParameter("courseName");
            int totalScore = Integer.parseInt(request.getParameter("totalScore"));
            String time = request.getParameter("time");
            
            CoursesDAO fdao = new CoursesDAO();
            
//            String currentDT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            
            String buttonName = request.getParameter("buttonName");
//            out.print("Button Name: " + buttonName);
            if(buttonName.equals("Submit")){
                
                CoursesBean ubean = new CoursesBean();
                ubean.setCourseName(courseName);
                ubean.setTotalScore(totalScore);
                ubean.setTime(time);
           
                int status = fdao.addNewCourse(ubean);
                if (status==0) {
                    
                    CoursesBean bean = new CoursesBean();
                        bean.setCourseName(courseName);
                        bean.setTotalScore(totalScore);
                        bean.setTime(time);
                        
                    getServletContext().getRequestDispatcher("/ems/course_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/course_form.jsp").forward(request, response);
                }

             }
             else if(buttonName.equals("Update")){
                
                CoursesBean ubean = new CoursesBean();
                ubean.setCourseName(courseName);
                ubean.setTotalScore(totalScore);
                ubean.setTime(time);
           
                int status = fdao.updateCourse(ubean);
                if (status==0) {
                    
                    CoursesBean bean = new CoursesBean();
                        bean.setCourseName(courseName);
                        bean.setTotalScore(totalScore);
                        bean.setTime(time);
                        
                    getServletContext().getRequestDispatcher("/ems/course_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/course_form.jsp").forward(request, response);
                }

            }
             else if(buttonName.equals("Delete")){
                 
                int status = fdao.deleteCourse(courseName);
                 if (status==0) {
                    
                    CoursesBean bean = new CoursesBean();
                        bean.setCourseName(courseName);
                        bean.setTotalScore(totalScore);
                        bean.setTime(time);
                        
                    getServletContext().getRequestDispatcher("/ems/course_list.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("err_msg", "Operation Fail");
                    getServletContext().getRequestDispatcher("/ems/course_form.jsp").forward(request, response);
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
