<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="Utility.UserTranslator"%>
<%@page import="java.util.List"%>
<%@page import="ems.beans.QuestionBean"%>
<%@page import="ems.DAO.QuestionDAO"%>
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
            int qid = 0;
            String courseName = "";
            String question = "";
            String opt1 = "";
            String opt2 = "";
            String opt3 = "";
            String opt4 = "";
            String correct = "";

            String buttonName = "Submit";
            String buttonClass = "btn btn-success";

            QuestionDAO urdao = new QuestionDAO();
            if (request.getParameter("op") != null && request.getParameter("id") != null) {

                String op = request.getParameter("op");
                int id = Integer.parseInt(request.getParameter("id"));

                session.setAttribute("id", id);

                QuestionBean ur = urdao.getQuestionRecord(id);

                qid = ur.getQuestionId();
                courseName = ur.getCourseName();
                question = ur.getQuestion();
                opt1 = ur.getOpt1();
                opt2 = ur.getOpt2();
                opt3 = ur.getOpt3();
                opt4 = ur.getOpt4();
                correct = ur.getCorrect();

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
                                                        <h1 class="h4 text-gray-900 mb-4">Add Questions</h1>
                                                    </div>
                                                    <form class="user" action="jp.do?action=question-action" method="post">
                                                        <div class="form-group" style="display: none;">
                                                            <input type="text" class="form-control" value="<%=qid%>" name="questionId" id="questionId" placeholder="Question ID" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <select class="form-control" name="courseName" id="" required>
                                                                <%
                                                                    CoursesDAO dao = new CoursesDAO();
                                                                    String cName = "";
                                                                    List<CoursesBean> beans = dao.getAllCourses();
                                                                    if (courseName.equals("")) {
                                                                        for (CoursesBean bean : beans) {
                                                                            cName = bean.getCourseName();
                                                                %>
                                                                <option value="<%=cName%>"><%=cName%></option>
                                                                <%
                                                                    }
                                                                } else {
                                                                %>
                                                                <option value="<%=courseName%>"><%=courseName%></option>
                                                                <%}%>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" value="<%=question%>" name="question" id="question" placeholder="Question" required>
                                                        </div>
                                                        <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <textarea type="text" class="form-control" value="" name="opt1" id="opt1" placeholder="Option 1" rows="4" required><%=opt1%></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <textarea type="text" class="form-control" value="" name="opt2" id="opt2" placeholder="Option 2" rows="4" required><%=opt2%></textarea>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <textarea type="text" class="form-control" value="" name="opt3" id="opt3" placeholder="Option 3" rows="4" required><%=opt3%></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <textarea type="text" class="form-control" value="" name="opt4" id="opt4" placeholder="Option 4" rows="4" required><%=opt4%></textarea>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" value="<%=correct%>" name="correct" id="correct" placeholder="Correct Answer" required>
                                                        </div>
                                                        <a href="jp.do?action=cascade-by-course"><button type="button" class="btn btn-danger">Cancel</button></a>
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

