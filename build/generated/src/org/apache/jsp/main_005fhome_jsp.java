package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Utility.UserTranslator;

public final class main_005fhome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main_css_style.jsp", out, true);
      out.write("\n");
      out.write("        ");

            String userId = "";
            if (session.getAttribute("userRoleId") != null) {
                userId = (String) session.getAttribute("userRoleId");
            }
        
      out.write("\n");
      out.write("        ");

            String dashboard = "";
            String sideNav = "";
            if (userId.equals(UserTranslator.ADMIN)) {
                dashboard = "main_home_admin.jsp";
                sideNav = "side_nav_admin.jsp";
            } else if (userId.equals(UserTranslator.LECTURER)) {
                dashboard = "main_home_lecturer.jsp";
                sideNav = "side_nav_lecturer.jsp";
            } else if (userId.equals(UserTranslator.STUDENT)) {
                dashboard = "main_home_student.jsp";
                sideNav = "side_nav_student.jsp";
            }
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <!--<body id=\"page-top\" onload=\"noBack();\" onpageshow=\"if(event.persisted) noBack();\" onunload=\"\">-->\n");
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main_header.jsp", out, true);
      out.write("\n");
      out.write("                    <!-- End of Topbar -->\n");
      out.write("\n");
      out.write("                    <!-- Begin main content-->\n");
      out.write("\n");
      out.write("                    <div class=\"container-fluid\">\n");
      out.write("\n");
      out.write("                        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, dashboard, out, true);
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <!-- End of Content Wrapper -->\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <!-- End of Page Wrapper -->\n");
      out.write("\n");
      out.write("                <!-- Scroll to Top Button-->\n");
      out.write("                <a class=\"scroll-to-top rounded\" href=\"#page-top\">\n");
      out.write("                    <i class=\"fas fa-angle-up\"></i>\n");
      out.write("                </a>\n");
      out.write("\n");
      out.write("                <!-- Logout Modal-->\n");
      out.write("                <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("                    <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                        <div class=\"modal-content\">\n");
      out.write("                            <div class=\"modal-header\">\n");
      out.write("                                <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>\n");
      out.write("                                <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                                    <span aria-hidden=\"true\">×</span>\n");
      out.write("                                </button>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>\n");
      out.write("                            <div class=\"modal-footer\">\n");
      out.write("                                <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n");
      out.write("                                <a class=\"btn btn-success\" href=\"login.html\">Logout</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("<!--                <script>\n");
      out.write("                    window.history.forward();\n");
      out.write("                    function noBack() {\n");
      out.write("                        window.history.forward();\n");
      out.write("                    }\n");
      out.write("                </script>-->\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "main_scripts.jsp", out, true);
      out.write("\n");
      out.write("\n");
      out.write("                    </body>\n");
      out.write("\n");
      out.write("                </html>\n");
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
