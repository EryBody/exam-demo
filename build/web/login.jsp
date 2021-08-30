<%-- 
    Document   : login
    Created on : Sep 3, 2019, 4:04:27 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <jsp:include page="main_css_style.jsp" flush="true"/>
  <% 
//            String sessionUser = (String)session.getAttribute("userId");
//        if (sessionUser!= null) {
//             session.invalidate();
//         }
        
  %>
</head>


<body class="indexbg" style="background: url(img/schoolexams1.png); width: 100%; height: auto">
  <div class="container">
<!--    <video autoplay muted loop id="myVideo">
      <source src="Video-Jingle.mp3" type="video/mp4">
    </video>-->
    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-md-6 d-none d-lg-block bg-login-image" style="background: url(img/paper.jpg)">
                    <center>
                        <h3 class="cetered text-light text-uppercase">SIGN IN</h3>
                    </center>
                </div>
              <div class="col-lg-6" style="background: url(img/paper.jpg)">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-light mb-4">Welcome Back!</h1>
                    <% 
                        String loginMsg = "";
                        if(request.getAttribute("loginMsg")!=null){
                            loginMsg = request.getAttribute("loginMsg").toString();
                            out.print("<div class='alert alert-info'>"+loginMsg+"</div>");
                        }
                        else{
                            out.print("");
                        }
                    %>
                  </div>
                  <form class="user" id="loginform" method="post" action="jp.do?action=login-action">
                    <div class="form-group">
                      <input type="text" class="form-control" id="textUsername" name="textUsername"  placeholder="Enter Email Address...">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control" id="textPassword" value="password" name="textPassword" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label text-dark" for="rememberme">Remember Me</label>
                      </div>
                    </div>
                      <button class="btn btn-primary btn-user btn-block" name="buttonSubmit" value="login" type="submit">Login</button>
                    <hr class="bg-light">
                    <a href="jp.do?action=login" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="jp.do?action=login" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a>
                  </form>
                  <hr class="bg-light">
                  
                  <div class="text-center">
                    <a class="small  text-light" href="jp.do?action=register">Create an Account!</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

<!--    <script>
        window.history.forward();
        function noBack(){window.history.forward();}
    </script>-->
<!--onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload=""-->

  <jsp:include page="main_scripts.jsp" flush="true"/>

</body>

</html>

