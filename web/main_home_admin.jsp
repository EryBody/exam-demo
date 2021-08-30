<%@page import="ems.DAO.CoursesDAO"%>
<%@page import="ems.DAO.ExamDAO"%>
<%@page import="ems.DAO.UserDAO"%>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">ADMINISTRATOR</h1>
</div>

<div class="row">

    <!-- Earnings (Monthly) Card Example -->
   
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">SYSTEM</div>
                         <a href="jp.do?action=user-list"><div class="h5 mb-0 font-weight-bold text-gray-800">USERS</div></a>
                    </div>
                    <div class="col-auto mt-2">
                        <%
                         int systemUsers = new UserDAO().getCountUsers();
                        %>
                        <a href="jp.do?action=user-list"><h2><strong><%=systemUsers%></strong></h2></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">ACTIVE</div>
                        <a href="jp.do?action=admin-result-list"><div class="h5 mb-0 font-weight-bold text-gray-800">EXAMS</div></a>
                    </div>
                    <div class="col-auto mt-2">
                        <%
                         int activeExams = new ExamDAO().getActiveExams();
                        %>
                        <a href="jp.do?action=admin-result-list"><h2><strong><%=activeExams%></strong></h2></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">ACTIVE</div>
                        <div class="row no-gutters align-items-center">
                            <div class="col-auto">
                                <a href="jp.do?action=course-list"><div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">COURSES</div></a>
                            </div>
                            <div class="col">
                                <!--                          <div class="progress progress-sm mr-2">
                                                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                          </div>-->
                            </div>
                        </div>
                    </div>
                    <div class="col-auto mt-2">
                        <%
                         int activeCourses = new CoursesDAO().getActiveCourses();
                        %>
                        <a href="jp.do?action=course-list"><h2><strong><%=activeCourses%></strong></h2></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Pending Requests Card Example -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">PENDING</div>
                        <a href="jp.do?action=user-list"><div class="h5 mb-0 font-weight-bold text-gray-800">LOGIN REQUEST</div></a>
                    </div>
                    <div class="col-auto mt-2">
                        <%
                         int loginRequest = new UserDAO().getPendingLoginRequest();
                        %>
                        <a href="jp.do?action=user-list"><h2><strong><%=loginRequest%></strong></h2></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
                    <a href="jp.do?action=user-list">
                        <img style="border-radius: 20px;" src="img/USERS.jpg" alt="login">
                    </a>
                </div>
            </div>

            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
                    <a href="jp.do?action=admin-result-list">
                        <img style="border-radius: 20px;" src="img/EXAMINATION.jpg" alt="login">
                    </a>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
                    <a href="jp.do?action=course-list">
                        <img style="border-radius: 20px;" src="img/CATEGORIES.jpg" alt="login">
                    </a>
                </div>
            </div>
        </div>
    
        <div class="row my-4">
            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
                    <a href="jp.do?action=student-list">
                        <img style="border-radius: 20px;" src="img/STUDENTS.jpg" alt="login">
                    </a>
                </div>
            </div>

            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
                    <a href="jp.do?action=cascade-by-course">
                        <img style="border-radius: 20px;" src="img/QUESTIONS.jpg" alt="login">
                    </a>
                </div>
            </div>

            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-4">
                <div class="my-2">
<!--                    <a href="jp.do?action=get-in-touch">-->
                        <img style="border-radius: 20px;" src="img/getintouch.jpg" alt="login">
                    <!--</a>-->
                </div>
            </div>
        </div>
        </div>



    <!--                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                             Indicators 
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>
    
                             Wrapper for slides 
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="img/adult.jpg" alt="Adult Reading Image" style="width:1200px;height:300px;">
                                    <div class="carousel-caption">
                                        <h3>Los Angeles</h3>
                                        <p>LA is always so much fun!</p>
                                    </div>
    
                                    <div class="item">
                                        <img src="img/book.jpg" alt="Book Image" style="width:1102px;height:1600px;">
                                        <div class="carousel-caption">
                                            <h3>Los Angeles</h3>
                                            <p>LA is always so much fun!</p>
                                        </div>
    
                                        <div class="item">
                                            <img src="img/conference.jpg" alt="Conference Image" style="width:1102px;height:1600px;">
                                            <div class="carousel-caption">
                                                <h3>Los Angeles</h3>
                                                <p>LA is always so much fun!</p>
                                            </div>
                                        </div>
    
                                         Left and right controls 
                                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                            <span class="glyphicon glyphicon-chevron-left"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                                            <span class="glyphicon glyphicon-chevron-right"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </div>-->


    <!-- End main content-->

    <!-- Footer -->
    <jsp:include page="main_footer.jsp" flush="true"/>
    <!-- End of Footer -->

</div>