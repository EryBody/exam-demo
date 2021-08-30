<%-- 
    Document   : index
    Created on : Aug 21, 2019, 10:50:17 PM
    Author     : Administrator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.List"%>
<%@page import="ems.beans.UserRoleBean"%>
<%@page import="ems.DAO.UserRoleDAO"%>
<%@page import="Utility.UserTranslator"%>
<%@page import="ems.beans.UserBean"%>
<%@page import="ems.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="../main_css_style.jsp" flush="true"/>

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
            int user = 0;
            String userRoleId = "";
            String firstName = "";
            String lastName = "";
            String userName = "";
            String email = "";
            String password = "";
            String contact = "";
            String city = "";
            String address = "";
            int loginStatus = 0;

            String userRName = "";

            String buttonName = "Submit";
            String buttonClass = "btn btn-success";

            UserDAO urdao = new UserDAO();
            if (request.getParameter("op") != null && request.getParameter("id") != null) {

                String op = request.getParameter("op");
                String id = request.getParameter("id");

                session.setAttribute("id", id);

                UserBean bean = urdao.getUserRecord(id);

                user = bean.getUserId();
                userRoleId = bean.getUserRoleId();
                firstName = bean.getFirstName();
                lastName = bean.getLastName();
                userName = bean.getUserName();
                email = bean.getEmail();
                contact = bean.getContact();
                city = bean.getCity();
                address = bean.getAddress();
                loginStatus = bean.getLoginStatus();
                password = bean.getPassword();

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

                userRName = new UserRoleDAO().getUserRoleName(userRoleId);

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
                                                        <h1 class="h4 text-gray-900 mb-4">Add Users</h1>
                                                    </div>
                                                    <form class="user" action="jp.do?action=user-action" method="post">
                                                        <div class="form-group">
                                                            <input type="hidden" class="form-control" value="<%=user%>" name="userId" id="" placeholder="User ID" required>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-md-4">
                                                                <%  
                                                                    UserRoleDAO dao = new UserRoleDAO();
                                                                    out.print("<select class='form-control' name='userRoleId' required>");
                                                                    LinkedHashMap CompMapData = null;
                                                                    CompMapData = dao.getUserRoleMap();
                                                                    if (CompMapData.isEmpty()) {
                                                                        out.print("<OPTION value=''>== No data found ==</OPTION>");
                                                                    } else {
                                                                        out.print("<OPTION value=''>== Select User Role ==</OPTION>");
                                                                        Set keys = CompMapData.keySet();
                                                                        Iterator itr = keys.iterator();
                                                                        for (int i = 0; i < keys.size(); i++) {
                                                                            String id = itr.next().toString();
                                                                            String name = CompMapData.get(id).toString();
                                                                            out.print("<OPTION value='" + id + "'");
                                                                            if (id.equals(userRoleId)) {
                                                                                out.println("selected='true'");
                                                                            }
                                                                            out.println(">" + name + "</OPTION>");
                                                                        }
                                                                    }
                                                                    out.print("</select>");
                                                                %>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <input type="password" class="form-control" value="<%=password%>" name="password" id="" placeholder="Password" required>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <select class="form-control" name="loginStatus" id="" required>
                                                                    <option value="">Select Login Status</option>
                                                                    <option value="0">No Privilege</option>
                                                                    <option value="1">Admin and Lecturer Privilege</option>
                                                                    <option value="2">Student Privilege</option>
                                                                </select>
                                                            </div>
                                                            <!--<input type="text" class="form-control" id="" placeholder="User Role">-->
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="firstName" id="" placeholder="First Name" value="<%=firstName%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="lastName" id="" placeholder="Last Name" value="<%=lastName%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="userName" id="" placeholder="User Name" value="<%=userName%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="email" id="" placeholder="Email" value="<%=email%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="contact" id="" placeholder="Phone Number" value="<%=contact%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="city" id="" placeholder="City" value="<%=city%>" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" name="address" id="" placeholder="Address" value="<%=address%>" required>
                                                        </div>

                                                        <a href="jp.do?action=user-list"><button type="button" class="btn btn-danger">Cancel</button></a>
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

