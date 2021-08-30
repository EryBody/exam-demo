<%-- 
    Document   : index
    Created on : Aug 21, 2021, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="Utility.UserTranslator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="main_css_style.jsp" flush="true"/>
        <%
            String userId = "";
            if (session.getAttribute("userRoleId") != null) {
                userId = (String) session.getAttribute("userRoleId");
            }
        %>
        <%
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
        %>
    </head>

    <!--<body id="page-top" onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">-->
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
                    <jsp:include page="main_header.jsp" flush="true"/>
                    <!-- End of Topbar -->

                    <!-- Begin main content-->

                    <div class="container-fluid">

                        <jsp:include page="<%=dashboard%>" flush="true"/>

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
                                <a class="btn btn-success" href="login.html">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>
<!--                <script>
                    window.history.forward();
                    function noBack() {
                        window.history.forward();
                    }
                </script>-->
                <jsp:include page="main_scripts.jsp" flush="true"/>

                    </body>

                </html>

