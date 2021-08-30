<%-- 
    Document   : login
    Created on : Sep 3, 2019, 4:04:27 PM
    Author     : Administrator
--%>

<%@page import="ems.beans.UserRoleBean"%>
<%@page import="java.util.List"%>
<%@page import="ems.DAO.UserRoleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="main_css_style.jsp" flush="true"/>
    </head>

    <body class="" style="background: url(img/schoolexams1.png)">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image" style="background: url(img/paper.jpg)">
                                    <center>
                                        <h3 class="cetered text-light text-uppercase">SIGN UP</h3>
                                    </center>
                                </div>
                                <div class="col-lg-6" style="background: url(img/paper.jpg)">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-light mb-4">Create an Account!</h1>
                                            <%
                                                String registerMsg = "";
                                                String errRegisterMsg = "";
                                                if (request.getAttribute("registerMsg") != null) {
                                                    registerMsg = request.getAttribute("registerMsg").toString();
                                                    out.print("<div class='alert alert-success'>" + registerMsg + "</div>");
                                                } else if (request.getAttribute("errRegisterMsg") != null) {
                                                    errRegisterMsg = request.getAttribute("errRegisterMsg").toString();
                                                    out.print("<div class='alert alert-danger'>" + errRegisterMsg + "</div>");
                                                } else {
                                                    out.print("");
                                                }
                                            %>
                                        </div>
                                        <form class="user" action="jp.do?action=register-action" method="post">
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input type="text" class="form-control" id="exampleFirstName" name="firstName" placeholder="First Name" required="true">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="exampleLastName" placeholder="Last Name" name="lastName" required="true">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input type="text" class="form-control" id="" name="userName" placeholder="User Name" required="true">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control" id="" placeholder="City" name="city" required="true">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="exampleInputEmail" placeholder="Email Address" name="email" required="true">
                                            </div>
                                            <div class="form-group">
                                                <select class="form-control form-control-range" name="userRole" id="" required="true">
                                                    <option value="">Select User Role</option>
                                                    <%
                                                        UserRoleDAO dao = new UserRoleDAO();
                                                        String userRoleName = "";
                                                        String userRoleId = "";
                                                        List<UserRoleBean> beans = dao.getUserRoles();

                                                        for (UserRoleBean bean : beans) {
                                                            userRoleId = bean.getUserRoleId();
                                                            userRoleName = bean.getUserRole();
                                                    %>
                                                    <option value="<%=userRoleId%>"><%=userRoleName%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="" placeholder="Mobile Number" name="mobileNumber" required="true">
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-sm-6 mb-3 mb-sm-0">
                                                    <input type="password" class="form-control" id="exampleInputPassword" name="password" placeholder="Password" required="true">
                                                </div>
                                                <div class="col-sm-6">
                                                    <input type="password" class="form-control" id="exampleRepeatPassword" name="confirmPassword" placeholder="Repeat Password" required="true">
                                                </div>
                                            </div>
                                            <button type="submit" value="register" name="buttonRegister" class="btn btn-primary btn-user btn-block">
                                                Register Account
                                            </button>
                                            <hr class="bg-light">
                                            <a href="jp.do?action=login" class="btn btn-google btn-user btn-block">
                                                <i class="fab fa-google fa-fw"></i> Register with Google
                                            </a>
                                            <a href="jp.do?action=login" class="btn btn-facebook btn-user btn-block">
                                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                                            </a>
                                        </form>
                                        <hr class="bg-light">
                                        <div class="text-center">
                                            <a class="small  text-light" href="jp.do?action=login">Already have an account? Login!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <jsp:include page="main_scripts.jsp" flush="true"/>

    </body>

</html>

