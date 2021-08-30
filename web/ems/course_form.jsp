<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="Utility.UserTranslator"%>
<%@page import="ems.beans.CoursesBean"%>
<%@page import="ems.DAO.CoursesDAO"%>
<%@page import="ems.beans.UserRoleBean"%>
<%@page import="ems.DAO.UserRoleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="../main_css_style.jsp" flush="true"/>
        
        <%
            String userId = "";
            if (session.getAttribute("userRoleId")!=null) {
                userId = (String)session.getAttribute("userRoleId");
            }
        %>
        <%
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
        %>
        <%
            String courseName = "";
            int totalScore = 0;
            String time = "";

            String buttonName = "Submit";
            String buttonClass = "btn btn-success";

            CoursesDAO urdao = new CoursesDAO();
            if (request.getParameter("op") != null && request.getParameter("id") != null) {

                String op = request.getParameter("op");
                String id = request.getParameter("id");

                session.setAttribute("id", id);

                CoursesBean ur = urdao.getCourseRecord(id);

                courseName = ur.getCourseName();
                totalScore = ur.getTotalScore();
                time = ur.getTime();

                if (op.equals("edit")) {
                    buttonName = "Update";
                    buttonClass = "btn btn-success";
                } else if (op.equals("delete")) {
                    buttonName = "Delete";
                    buttonClass = "btn btn-dark ";
                } else {
                    buttonName = "Submit";
                    buttonClass = "btn btn-success";
                }

            }

        %>
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!--top of the page-->

            <!-- Sidebar -->
            <jsp:include page="<%=sideNav%>" flush="true"/>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <jsp:include page="../main_header.jsp" flush="true"/>
                    <!-- End of Topbar -->

                    <!-- Begin main content-->

                    <div class="container">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800"><%=loggedUser%></h1>
                        </div>

                        <div class="row justify-content-center">

                            <div class="col-md-12">

                                <div class="card o-hidden border-0 shadow-lg my-5">
                                    <div class="card-body p-0">
                                        <!-- Nested Row within Card Body -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="p-5">
                                                    <div class="text-uppercase">
                                                        <h1 class="h4 text-gray-900 mb-4">Add Courses</h1>
                                                    </div>
                                                    <form class="user" action="jp.do?action=course-action" method="post">
                                                        <div class="form-group">
                                                            <input type="text" class="form-control form-control-user" value="<%=courseName%>" name="courseName" id="courseName" placeholder="Course Name" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control form-control-user" value="<%=totalScore%>" name="totalScore" id="totalScore" placeholder="Total Score" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="number" class="form-control form-control-user" value="<%=time%>" name="time" id="time" placeholder="Time(in Munites) Eg:Time = 4" required>
                                                        </div>

                                                        <a href="jp.do?action=course-list"><button type="button" class="btn btn-danger">Cancel</button></a>
                                                        <button type="submit" name="buttonName" value="<%=buttonName%>" class="<%=buttonClass%>"><%=buttonName%></button>
                                                    </form>
                                                    <hr>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div class="row">
                            <!-- Footer -->
                            <jsp:include page="../main_footer.jsp" flush="true"/>
                            <!-- End of Footer -->
                        </div>
                        <!-- End of Content Wrapper -->

                    </div>
                    <!-- End of Page Wrapper -->

                    <!-- Scroll to Top Button-->
                    <a class="scroll-to-top rounded" href="#page-top">
                        <i class="fas fa-angle-up"></i>
                    </a>

                    <!-- Logout Modal-->
                    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                                <div class="modal-footer">
                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                    <a class="btn btn-primary" href="login.html">Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <jsp:include page="../main_scripts.jsp" flush="true"/>

                    </body>

                    </html>

