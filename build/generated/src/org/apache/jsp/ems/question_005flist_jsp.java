package org.apache.jsp.ems;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Utility.UserTranslator;
import ems.beans.QuestionBean;
import ems.DAO.QuestionDAO;
import ems.beans.CoursesBean;
import ems.DAO.CoursesDAO;
import ems.beans.UserRoleBean;
import java.util.List;
import ems.DAO.UserRoleDAO;

public final class question_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("    <head>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_css_style.jsp", out, true);
      out.write("\n");
      out.write("        \n");
      out.write("        ");

            String userId = "";
            if (session.getAttribute("userRoleId")!=null) {
                userId = (String)session.getAttribute("userRoleId");
            }
        
      out.write("\n");
      out.write("        ");

            String loggedUser = "";
            String dashboard = "";
            String sideNav = "";
            if(userId.equals(UserTranslator.ADMIN)){
                dashboard = "../main_home_admin.jsp";
                sideNav = "../side_nav_admin.jsp";
                loggedUser = "ADMINISTRATOR";
            }
            else if(userId.equals(UserTranslator.LECTURER)){
                dashboard = "../main_home_lecturer.jsp";
                sideNav = "../side_nav_lecturer.jsp";
                loggedUser = "LECTURER";
            }
            else if(userId.equals(UserTranslator.STUDENT)){
                dashboard = "../main_home_student.jsp";
                sideNav = "../side_nav_student.jsp";
                loggedUser = "STUDENT";
            }
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
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
      out.write("</h1>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"card shadow mb-4\">\n");
      out.write("                            <div class=\"card-header py-3\">\n");
      out.write("                                <div class=\"row\">\n");
      out.write("                                    <div class=\"col-md-3\">\n");
      out.write("                                        <h6 class=\"m-3 font-weight-bold text-success\">Manage Questions</h6>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"offset-md-7\">\n");
      out.write("                                        <a href=\"jp.do?action=question-form\"><button type=\"button\" class=\"btn btn-success m-1\">Add Question</button></a>\n");
      out.write("                                    </div>\n");
      out.write("                                \n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                                \n");
      out.write("                            <div class=\"card-body\">\n");
      out.write("                                <div class=\"table-responsive\">\n");
      out.write("                                    <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n");
      out.write("                                        <thead>\n");
      out.write("                                            <tr>\n");
      out.write("                                                <th>S/N</th>\n");
      out.write("                                                <th>Question ID</th>\n");
      out.write("                                                <th>Course Name</th>\n");
      out.write("                                                <th>Question</th>\n");
      out.write("                                                <th>Opt 1</th>\n");
      out.write("                                                <th>Opt 2</th>\n");
      out.write("                                                <th>Opt 3</th>\n");
      out.write("                                                <th>Opt 4</th>\n");
      out.write("                                                <th>Correct</th>\n");
      out.write("                                                <th>Action</th>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </thead>\n");
      out.write("                                        <tbody>\n");
      out.write("                                            ");

                                                
                                                int counter = 1;
                                                int qid = 0;
                                                String courseName = "";
                                                String question = "";
                                                String opt1 = "";
                                                String opt2 = "";
                                                String opt3 = "";
                                                String opt4 = "";
                                                String correct = "";
                                                
                                                String cName = "";
                                                if(request.getParameter("courseName")!=null){
                                                    cName = request.getParameter("courseName");
                                                }
//                                                else{
//                                                    cName = "MTH101";
//                                                }
                                                    
                                                QuestionDAO dao = new QuestionDAO();
                                                List<QuestionBean> beans = dao.getAllQuestions(cName);
                                                for(QuestionBean bean: beans){
                                                    qid = bean.getQuestionId();
                                                    courseName = bean.getCourseName();
                                                    question = bean.getQuestion();
                                                    opt1 = bean.getOpt1();
                                                    opt2 = bean.getOpt2();
                                                    opt3 = bean.getOpt3();
                                                    opt4 = bean.getOpt4();
                                                    correct = bean.getCorrect();
                                            
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td>");
      out.print(counter);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(qid);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(courseName);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(question);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(opt1);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(opt2);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(opt3);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(opt4);
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print(correct);
      out.write("</td>\n");
      out.write("                                                <td nowrap>\n");
      out.write("                                                    <a href=\"jp.do?action=question-form&op=edit&id=");
      out.print(qid);
      out.write("\"><button type=\"button\" class=\"btn btn-info btn-circle\"><i class=\"fas fa-info-circle\"></i></button></a>\n");
      out.write("                                                    <a href=\"jp.do?action=question-form&op=delete&id=");
      out.print(qid);
      out.write("\"><button type=\"button\" class=\"btn btn-danger btn-circle\"><i class=\"fas fa-trash\"></i></button></a>\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                            ");

                                                counter++;
                                                }
                                            
      out.write("\n");
      out.write("                                        </tbody>\n");
      out.write("                                        <tfoot>\n");
      out.write("                                            <tr>\n");
      out.write("                                               <th>S/N</th>\n");
      out.write("                                                <th>Question ID</th>\n");
      out.write("                                                <th>Course Name</th>\n");
      out.write("                                                <th>Question</th>\n");
      out.write("                                                <th>Opt 1</th>\n");
      out.write("                                                <th>Opt 2</th>\n");
      out.write("                                                <th>Opt 3</th>\n");
      out.write("                                                <th>Opt 4</th>\n");
      out.write("                                                <th>Correct</th>\n");
      out.write("                                                <th>Action</th>\n");
      out.write("                                            </tr>\n");
      out.write("                                        </tfoot>\n");
      out.write("                                    </table>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <!-- Footer -->\n");
      out.write("                            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_footer.jsp", out, true);
      out.write("\n");
      out.write("                            <!-- End of Footer -->\n");
      out.write("                        </div>\n");
      out.write("                        <!-- End of Content Wrapper -->\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <!-- End of Page Wrapper -->\n");
      out.write("\n");
      out.write("                    <!-- Scroll to Top Button-->\n");
      out.write("                    <a class=\"scroll-to-top rounded\" href=\"#page-top\">\n");
      out.write("                        <i class=\"fas fa-angle-up\"></i>\n");
      out.write("                    </a>\n");
      out.write("\n");
      out.write("                    <!-- Logout Modal-->\n");
      out.write("                    <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("                        <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                            <div class=\"modal-content\">\n");
      out.write("                                <div class=\"modal-header\">\n");
      out.write("                                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>\n");
      out.write("                                    <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                                        <span aria-hidden=\"true\">×</span>\n");
      out.write("                                    </button>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n");
      out.write("                                    <a class=\"btn btn-primary\" href=\"login.html\">Logout</a>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../main_scripts.jsp", out, true);
      out.write("\n");
      out.write("\n");
      out.write("                    </body>\n");
      out.write("\n");
      out.write("                    </html>\n");
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
