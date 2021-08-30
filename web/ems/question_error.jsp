<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="Utility.UserTranslator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="../main_css_style.jsp" flush="true"/>

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!--top of the page-->

            <!-- Sidebar -->
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <!-- End of Topbar -->

                    <!-- Begin main content-->

                    <div class="container-fluid">


                        <!-- 404 Error Text -->
                        <div class="col-md-12">
                            <div class="text-center">
                                <!--<div class="error mx-auto" data-text="403">403</div>-->
                                <%
                                    String firstName = "";
                                    String lastName = "";
                                    if ((session.getAttribute("firstName") != null) && (session.getAttribute("lastName") != null)) {
                                        firstName = (String) session.getAttribute("firstName");
                                        lastName = (String) session.getAttribute("lastName");
                                    }
                                %>
                                <h2>
                                    <%=firstName%> <%=lastName%>
                                </h2>
                                <p class="lead text-gray-800 mb-5">You Didn't Answer Any Question</p>
                                <p class="text-gray-500 mb-0">Please Contact the Closest Administrator!!!</p>
                                <a href="jp.do?action=dashboard">&larr; Back to Dashboard</a>
                            </div>
                        </div>
                    </div>
                    <!-- End of Content Wrapper -->

                    <div style="margin-top:800px;">
                    </div>
                    <div class="row">
                        <!-- Footer -->
                        <jsp:include page="../main_footer.jsp" flush="true"/>
                        <!-- End of Footer -->
                    </div>
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
                                <a class="btn btn-primary" href="jp.do?action=login">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="../main_scripts.jsp" flush="true"/>

                </body>

                </html>
