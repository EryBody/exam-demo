<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>
<%@page import="ems.DAO.ExamDAO"%>
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
        <style>
            .bodr{
                border: 1px solid blue;
                padding-left: 15px;
                padding-right: 15px;
                padding-top: 10px;
                padding-bottom: 10px;
                border-radius: 10px;
            }

            .border:hover{
                background-color: #90ee90
            }
        </style>
        <%
            String userId = "";
            if (session.getAttribute("userRoleId") != null) {
                userId = (String) session.getAttribute("userRoleId");
            }
        %>
        <%
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
        %>
        <%
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
        %>
        <%
            String spageid = request.getParameter("page");
            int pageid = Integer.parseInt(spageid);
            int total = 1;
            if (pageid == 1) {
            } else {
                pageid = pageid - 1;
                pageid = pageid * total + 1;
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
                            <h1 class="h3 mb-0 text-gray-800"><%=loggedUser%> </h1>
                        </div>

                        <div>
                            <p name="courseTime" id="courseTimer" style="display: none;"><%=courseTime%></p>
                            <span id="remainingTime" style="position: absolute;top:85px;right: 50px;z-index:3000;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;">02:40</span>
                        </div>

                        <!--Start Main Body-->
                        <div class="row justify-content">
                            <div class="col-md-8">
                                <div class="card o-hidden border-0 shadow-lg my-5">
                                    <div class="card-body p-0">
                                        <!-- Nested Row within Card Body -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="p-5">
                                                    <div class="text-uppercase">
                                                        <h1 class="h4 text-gray-900 mb-4">Take Examination</h1>
                                                    </div>
                                                    <form id="myform" class="user" action="jp.do?action=student-exam-action" method="post">
                                                        <%
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

                                                        %>
                                                        <div class="container">
                                                            <div class="form-group">
                                                                <h5 class="text-uppercase">Question <%=spageid%></h5>
                                                            </div>
                                                            <input type="hidden" value="<%=qid%>" name="qid" id="qid">
                                                            <input type="hidden" value="<%=courseTime%>" name="courseTime" id="courseTime">

                                                            <div class="form-group">
                                                                <!--<textArea class="form-control" name="question" id="question">-->
                                                                <div class="bodr">
                                                                    <%=question%>
                                                                </div>
                                                                <!--</textArea>-->
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6" style="">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" value="<%=opt1%>" name="opt" id="opt1">
                                                                        <label for="opt1" ><%=opt1%></label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" value="<%=opt2%>" name="opt" id="opt2">
                                                                        <label for="opt2" ><%=opt2%></label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" value="<%=opt3%>" name="opt" id="opt3">
                                                                        <label for="opt3" ><%=opt3%></label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <input type="checkbox" value="<%=opt4%>" name="opt" id="opt4">
                                                                        <label for="opt4" ><%=opt4%></label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <hr class="bg-info">
<!--                                                        <a class="bodr border float-left" href="jp.do?action=student-exam&page=<%=counter%>&cn=<%=cName%>">Previous</a> 
                                                        <a class="bodr border float-right" href="jp.do?action=student-exam&page=<%=counter%>&cn=<%=cName%>">Next</a>-->
                                                        <!--<br>-->
                                                        <%
                                                                counter++;
                                                            }
                                                        %>
                                                        <input class="form-control" type="hidden" value="<%=qid%>" name="validatingId" id="validatingId">
                                                        <input class="form-control" type="hidden" value="<%=courseName%>" name="courseName" id="courseName">
                                                        <a class="bodr border float-left" href="jp.do?action=student-exam&page=<%=counter%>&cn=<%=cName%>">Previous</a> 
                                                        <a class="bodr border float-right" href="jp.do?action=student-exam&page=<%=counter%>&cn=<%=cName%>">Next</a>
                                                        <br>
                                                        <br>
                                                        <hr>
                                                        <center>
                                                            <a href="jp.do?action=student-exam-action"><button type="button" value="cancel" name="cancelName" class="btn btn-danger">Cancel</button></a>
                                                            <button type="submit" name="buttonName" value="submit" class="btn btn-success">Submit</button>
                                                        </center>
                                                    </form>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card o-hidden border-0 shadow-lg my-5">
                                    <div class="card-body p-0">
                                        <!-- Nested Row within Card Body -->
                                        <h4 class="m-4">QUICK SCROLL</h4>
                                        <div class="m-3 p-3 col-md-12">
                                            <%
                                                
                                                int pageCounter = 1;

                                                List<QuestionBean> qabean = dao.getAllQuestions(cName);
                                                for (QuestionBean bean : qabean) {
                                            %>
                                                <a class="bodr border" href="jp.do?action=student-exam&page=<%=pageCounter%>&cn=<%=cName%>"><%=pageCounter%></a>  
                                            <%
                                                    pageCounter++;
                                                }
                                            %>
                                        </div>
                                        <br>
                                        <br>
                                        <br>
                                    </div>
                                </div>     
                            </div>
                        </div>


                    </div>
                </div>
                <!--End Main Body-->

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

            <script>
                var time = document.getElementById('courseTimer').innerHTML;
                time--;
                var sec = 60;
                document.getElementById("remainingTime").innerHTML = time + " : " + sec;
                //it calls fuction after specific time again and again                  
                var x = window.setInterval(timerFunction, 1000);

                function timerFunction() {
                    sec--;
                    // Display the result in the element with id="demo"
                    if (time < 0) {
                        clearInterval(x);
                        document.getElementById("remainingTime").innerHTML = "00 : 00";
                        document.getElementById("myform").submit();
                    }
                    document.getElementById("remainingTime").innerHTML = time + " : " + sec;
                    if (sec == 0) {
                        sec = 60;
                        time--;

                    }
                }
            </script>

            <jsp:include page="../main_scripts.jsp" flush="true"/>

    </body>

</html>

