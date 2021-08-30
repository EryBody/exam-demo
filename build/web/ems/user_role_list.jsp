<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="Utility.UserTranslator"%>
<%@page import="ems.beans.UserRoleBean"%>
<%@page import="java.util.List"%>
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

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <div class="row">
                                    <div class="col-md-3">
                                        <h6 class="m-3 font-weight-bold text-success">Manage User Roles</h6>
                                    </div>
                                    <div class="offset-md-7">
                                        <a href="jp.do?action=user-role-form"><button type="button" class="btn btn-success m-1">User Role Form</button></a>
                                    </div>
                                
                                </div>
                            </div>
                                
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>S/N</th>
                                                <th>User Role ID</th>
                                                <th>User Role</th>
                                                <th>Description</th>
                                                <th>Entry Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                
                                                int counter = 1;
                                                String userRoleId = "";
                                                String userRole = "";
                                                String desc = "";
                                                String entryDate = "";
                                                
                                                UserRoleDAO dao = new UserRoleDAO();
                                                List<UserRoleBean> beans = dao.getUserRoles();
                                                for(UserRoleBean bean: beans){
                                                    userRoleId = bean.getUserRoleId();
                                                    userRole = bean.getUserRole();
                                                    desc = bean.getDescription();
                                                    entryDate = bean.getEntryDate();
                                            %>
                                            <tr>
                                                <td><%=counter%></td>
                                                <td><%=userRoleId%></td>
                                                <td><%=userRole%></td>
                                                <td><%=desc%></td>
                                                <td><%=entryDate%></td>
                                                <td>
                                                    <a href="jp.do?action=user-role-form&op=edit&id=<%=userRoleId%>"><button type="button" class="btn btn-info btn-circle"><i class="fas fa-info-circle"></i></button></a>
                                                    <a href="jp.do?action=user-role-form&op=delete&id=<%=userRoleId%>"><button type="button" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></button></a>
                                                </td>
                                            </tr>
                                            <%
                                                counter++;
                                                }
                                            %>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>S/N</th>
                                                <th>User Role ID</th>
                                                <th>User Role</th>
                                                <th>Description</th>
                                                <th>Entry Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </tfoot>
                                    </table>
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

