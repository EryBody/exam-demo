package org.apache.jsp.ems;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import ems.DAO.ExamDAO;
import Utility.UserTranslator;
import java.util.List;
import ems.beans.QuestionBean;
import ems.DAO.QuestionDAO;
import ems.beans.CoursesBean;
import ems.DAO.CoursesDAO;
import ems.beans.UserRoleBean;
import ems.DAO.UserRoleDAO;

public final class student_005fexams_005fbk_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_css_style.jsp", out, true);
      out.write("\n");
      out.write("        <style>\n");
      out.write("            .bodr{\n");
      out.write("                border: 1px solid blue;\n");
      out.write("                padding-left: 15px;\n");
      out.write("                padding-right: 15px;\n");
      out.write("                padding-top: 10px;\n");
      out.write("                padding-bottom: 10px;\n");
      out.write("                border-radius: 10px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .border:hover{\n");
      out.write("                background-color: #90ee90\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        ");

            String userId = "";
            if (session.getAttribute("userRoleId") != null) {
                userId = (String) session.getAttribute("userRoleId");
            }
        
      out.write("\n");
      out.write("        ");

            String loggedUser = "";
            String dashboard = "";
            String sideNav = "";
            if (userId.equals(UserTranslator.ADMIN)) {
                dashboard = "../main_home_admin.jsp";
                sideNav = "../side_nav_admin.jsp";
                loggedUser = "ADMINISTRATOR";
            } else if (userId.equals(UserTranslator.LECTURER)) {
                dashboard = "../main_home_lecturer.jsp";
                sideNav = "../side_nav_lecturer.jsp";
                loggedUser = "LECTURER";
            } else if (userId.equals(UserTranslator.STUDENT)) {
                dashboard = "../main_home_student.jsp";
                sideNav = "../side_nav_student.jsp";
                loggedUser = "STUDENT";
            }
        
      out.write("\n");
      out.write("        ");

            ExamDAO edao = new ExamDAO();
            int studentId = 0;
            if (session.getAttribute("userId") != null) {
                studentId = (int) session.getAttribute("userId");
            }

            String cName = "";
            if (request.getParameter("courseName") != null) {
                cName = request.getParameter("courseName");
            }else{
                cName = request.getParameter("cn");
            }

            int examinationId = edao.startExam(cName, studentId);
            session.setAttribute("examinationId", examinationId);
            session.setAttribute("CNAME", cName);

            String courseTime = "";
            CoursesDAO cdao = new CoursesDAO();

            courseTime = cdao.getCourseTimeByName(cName);
        
      out.write("\n");
      out.write("        ");

            String spageid = request.getParameter("page");
            int pageid = Integer.parseInt(spageid);
            int total = 1;
            if (pageid == 1) {
            } else {
                pageid = pageid - 1;
                pageid = pageid * total + 1;
            }
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body id=\"page-top\">\n");
      out.write("\n");
      out.write("        <!-- Page Wrapper -->\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("\n");
      out.write("            <!--top of the page-->\n");
      out.write("\n");
      out.write("            <!-- Sidebar -->\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, sideNav, out, true);
      out.write("\n");
      out.write("            <!-- End of Sidebar -->\n");
      out.write("\n");
      out.write("            <!-- Content Wrapper -->\n");
      out.write("            <div id=\"content-wrapper\" class=\"d-flex flex-column\">\n");
      out.write("\n");
      out.write("                <!-- Main Content -->\n");
      out.write("                <div id=\"content\">\n");
      out.write("\n");
      out.write("                    <!-- Topbar -->\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_header.jsp", out, true);
      out.write("\n");
      out.write("                    <!-- End of Topbar -->\n");
      out.write("\n");
      out.write("                    <!-- Begin main content-->\n");
      out.write("\n");
      out.write("                    <div class=\"container\">\n");
      out.write("\n");
      out.write("                        <!-- Page Heading -->\n");
      out.write("                        <div class=\"d-sm-flex align-items-center justify-content-between mb-4\">\n");
      out.write("                            <h1 class=\"h3 mb-0 text-gray-800\">");
      out.print(loggedUser);
      out.write(" </h1>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div>\n");
      out.write("                            <p name=\"courseTime\" id=\"courseTimer\" style=\"display: none;\">");
      out.print(courseTime);
      out.write("</p>\n");
      out.write("                            <span id=\"remainingTime\" style=\"position: absolute;top:85px;right: 50px;z-index:3000;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;\">02:40</span>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!--Start Main Body-->\n");
      out.write("                        <div class=\"row justify-content\">\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <div class=\"card o-hidden border-0 shadow-lg my-5\">\n");
      out.write("                                    <div class=\"card-body p-0\">\n");
      out.write("                                        <!-- Nested Row within Card Body -->\n");
      out.write("                                        <div class=\"row\">\n");
      out.write("                                            <div class=\"col-lg-12\">\n");
      out.write("                                                <div class=\"p-5\">\n");
      out.write("                                                    <div class=\"text-uppercase\">\n");
      out.write("                                                        <h1 class=\"h4 text-gray-900 mb-4\">Take Examination</h1>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <form id=\"myform\" class=\"user\" action=\"jp.do?action=student-exam-action\" method=\"post\">\n");
      out.write("                                                        ");

                                                            int counter = 1;
                                                            int qid = 0;
                                                            String courseName = "";
                                                            String question = "";
                                                            String opt1 = "";
                                                            String opt2 = "";
                                                            String opt3 = "";
                                                            String opt4 = "";
                                                            String correct = "";

                                                            QuestionDAO dao = new QuestionDAO();
                                                            List<QuestionBean> qbean = dao.getQuestions(cName, pageid, total);
                                                            for (QuestionBean bean : qbean) {
                                                                qid = bean.getQuestionId();
                                                                courseName = bean.getCourseName();
                                                                question = bean.getQuestion();
                                                                opt1 = bean.getOpt1();
                                                                opt2 = bean.getOpt2();
                                                                opt3 = bean.getOpt3();
                                                                opt4 = bean.getOpt4();
                                                                correct = bean.getCorrect();

                                                        
      out.write("\n");
      out.write("                                                        <div class=\"container\">\n");
      out.write("                                                            <div class=\"form-group\">\n");
      out.write("                                                                <h5 class=\"text-uppercase\">Question ");
      out.print(spageid);
      out.write("</h5>\n");
      out.write("                                                            </div>\n");
      out.write("                                                            <input type=\"hidden\" value=\"");
      out.print(qid);
      out.write("\" name=\"qid\" id=\"qid\">\n");
      out.write("                                                            <input type=\"hidden\" value=\"");
      out.print(courseTime);
      out.write("\" name=\"courseTime\" id=\"courseTime\">\n");
      out.write("\n");
      out.write("                                                            <div class=\"form-group\">\n");
      out.write("                                                                <!--<textArea class=\"form-control\" name=\"question\" id=\"question\">-->\n");
      out.write("                                                                <div class=\"bodr\">\n");
      out.write("                                                                    ");
      out.print(question);
      out.write("\n");
      out.write("                                                                </div>\n");
      out.write("                                                                <!--</textArea>-->\n");
      out.write("                                                            </div>\n");
      out.write("                                                            <div class=\"row\">\n");
      out.write("                                                                <div class=\"col-md-6\" style=\"\">\n");
      out.write("                                                                    <div class=\"form-group\">\n");
      out.write("                                                                        <input type=\"checkbox\" value=\"");
      out.print(opt1);
      out.write("\" name=\"opt\" id=\"opt1\">\n");
      out.write("                                                                        <label for=\"opt1\" >");
      out.print(opt1);
      out.write("</label>\n");
      out.write("                                                                    </div>\n");
      out.write("                                                                </div>\n");
      out.write("                                                                <div class=\"col-md-6\">\n");
      out.write("                                                                    <div class=\"form-group\">\n");
      out.write("                                                                        <input type=\"checkbox\" value=\"");
      out.print(opt2);
      out.write("\" name=\"opt\" id=\"opt2\">\n");
      out.write("                                                                        <label for=\"opt2\" >");
      out.print(opt2);
      out.write("</label>\n");
      out.write("                                                                    </div>\n");
      out.write("                                                                </div>\n");
      out.write("                                                            </div>\n");
      out.write("                                                            <div class=\"row\">\n");
      out.write("                                                                <div class=\"col-md-6\">\n");
      out.write("                                                                    <div class=\"form-group\">\n");
      out.write("                                                                        <input type=\"checkbox\" value=\"");
      out.print(opt3);
      out.write("\" name=\"opt\" id=\"opt3\">\n");
      out.write("                                                                        <label for=\"opt3\" >");
      out.print(opt3);
      out.write("</label>\n");
      out.write("                                                                    </div>\n");
      out.write("                                                                </div>\n");
      out.write("                                                                <div class=\"col-md-6\">\n");
      out.write("                                                                    <div class=\"form-group\">\n");
      out.write("                                                                        <input type=\"checkbox\" value=\"");
      out.print(opt4);
      out.write("\" name=\"opt\" id=\"opt4\">\n");
      out.write("                                                                        <label for=\"opt4\" >");
      out.print(opt4);
      out.write("</label>\n");
      out.write("                                                                    </div>\n");
      out.write("                                                                </div>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <hr class=\"bg-info\">\n");
      out.write("<!--                                                        <a class=\"bodr border float-left\" href=\"jp.do?action=student-exam&page=");
      out.print(counter);
      out.write("&cn=");
      out.print(cName);
      out.write("\">Previous</a> \n");
      out.write("                                                        <a class=\"bodr border float-right\" href=\"jp.do?action=student-exam&page=");
      out.print(counter);
      out.write("&cn=");
      out.print(cName);
      out.write("\">Next</a>-->\n");
      out.write("                                                        <!--<br>-->\n");
      out.write("                                                        ");

                                                                counter++;
                                                            }
                                                        
      out.write("\n");
      out.write("                                                        <input class=\"form-control\" type=\"hidden\" value=\"");
      out.print(qid);
      out.write("\" name=\"validatingId\" id=\"validatingId\">\n");
      out.write("                                                        <input class=\"form-control\" type=\"hidden\" value=\"");
      out.print(courseName);
      out.write("\" name=\"courseName\" id=\"courseName\">\n");
      out.write("                                                        <a class=\"bodr border float-left\" href=\"jp.do?action=student-exam&page=");
      out.print(counter);
      out.write("&cn=");
      out.print(cName);
      out.write("\">Previous</a> \n");
      out.write("                                                        <a class=\"bodr border float-right\" href=\"jp.do?action=student-exam&page=");
      out.print(counter);
      out.write("&cn=");
      out.print(cName);
      out.write("\">Next</a>\n");
      out.write("                                                        <br>\n");
      out.write("                                                        <br>\n");
      out.write("                                                        <hr>\n");
      out.write("                                                        <center>\n");
      out.write("                                                            <a href=\"jp.do?action=student-exam-action\"><button type=\"button\" value=\"cancel\" name=\"cancelName\" class=\"btn btn-danger\">Cancel</button></a>\n");
      out.write("                                                            <button type=\"submit\" name=\"buttonName\" value=\"submit\" class=\"btn btn-success\">Submit</button>\n");
      out.write("                                                        </center>\n");
      out.write("                                                    </form>\n");
      out.write("                                                    \n");
      out.write("                                                </div>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-4\">\n");
      out.write("                                <div class=\"card o-hidden border-0 shadow-lg my-5\">\n");
      out.write("                                    <div class=\"card-body p-0\">\n");
      out.write("                                        <!-- Nested Row within Card Body -->\n");
      out.write("                                        <h4 class=\"m-4\">QUICK SCROLL</h4>\n");
      out.write("                                        <div class=\"m-3 p-3 col-md-12\">\n");
      out.write("                                            ");

                                                
                                                int pageCounter = 1;

                                                List<QuestionBean> qabean = dao.getAllQuestions(cName);
                                                for (QuestionBean bean : qabean) {
                                            
      out.write("\n");
      out.write("                                                <a class=\"bodr border\" href=\"jp.do?action=student-exam&page=");
      out.print(pageCounter);
      out.write("&cn=");
      out.print(cName);
      out.write('"');
      out.write('>');
      out.print(pageCounter);
      out.write("</a>  \n");
      out.write("                                            ");

                                                    pageCounter++;
                                                }
                                            
      out.write("\n");
      out.write("                                        </div>\n");
      out.write("                                        <br>\n");
      out.write("                                        <br>\n");
      out.write("                                        <br>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>     \n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <!--End Main Body-->\n");
      out.write("\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <!-- Footer -->\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_footer.jsp", out, true);
      out.write("\n");
      out.write("                    <!-- End of Footer -->\n");
      out.write("                </div>\n");
      out.write("                <!-- End of Content Wrapper -->\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <!-- End of Page Wrapper -->\n");
      out.write("\n");
      out.write("            <!-- Scroll to Top Button-->\n");
      out.write("            <a class=\"scroll-to-top rounded\" href=\"#page-top\">\n");
      out.write("                <i class=\"fas fa-angle-up\"></i>\n");
      out.write("            </a>\n");
      out.write("\n");
      out.write("            <!-- Logout Modal-->\n");
      out.write("            <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("                <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                    <div class=\"modal-content\">\n");
      out.write("                        <div class=\"modal-header\">\n");
      out.write("                            <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>\n");
      out.write("                            <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                                <span aria-hidden=\"true\">×</span>\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n");
      out.write("                            <a class=\"btn btn-primary\" href=\"login.html\">Logout</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <script>\n");
      out.write("                var time = document.getElementById('courseTimer').innerHTML;\n");
      out.write("                time--;\n");
      out.write("                var sec = 60;\n");
      out.write("                document.getElementById(\"remainingTime\").innerHTML = time + \" : \" + sec;\n");
      out.write("                //it calls fuction after specific time again and again                  \n");
      out.write("                var x = window.setInterval(timerFunction, 1000);\n");
      out.write("\n");
      out.write("                function timerFunction() {\n");
      out.write("                    sec--;\n");
      out.write("                    // Display the result in the element with id=\"demo\"\n");
      out.write("                    if (time < 0) {\n");
      out.write("                        clearInterval(x);\n");
      out.write("                        document.getElementById(\"remainingTime\").innerHTML = \"00 : 00\";\n");
      out.write("                        document.getElementById(\"myform\").submit();\n");
      out.write("                    }\n");
      out.write("                    document.getElementById(\"remainingTime\").innerHTML = time + \" : \" + sec;\n");
      out.write("                    if (sec == 0) {\n");
      out.write("                        sec = 60;\n");
      out.write("                        time--;\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            </script>\n");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_scripts.jsp", out, true);
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
