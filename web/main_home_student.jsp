<%@page import="ems.DAO.CoursesDAO"%>
<%@page import="ems.DAO.ExamDAO"%>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">STUDENT</h1>
</div>

<div class="row">
    <div class="col-xl-12 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">PENDING</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">MESSAGES</div>
                    </div>
                    <div class="col-auto">
                        <h2><strong>10</strong></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Earnings (Monthly) Card Example -->
    <div class="col-xl-12 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">ACTIVE</div>
                        <div class="row no-gutters align-items-center">
                            <div class="col-auto">
                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">COURSES</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-auto">
                        <%
                         int activeCourses = new CoursesDAO().getActiveCourses();
                        %>
                        <h2><strong><%=activeCourses%></strong></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
                    
    <div class="col-xl-12 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">EXAMS</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">DONE</div>
                    </div>
                    <div class="col-auto">
                        <%
                        int studentId = 0;
                        if (session.getAttribute("userId") != null) {
                            studentId = (int) session.getAttribute("userId");
                        }
                        
                        int activeExams = new ExamDAO().getActiveStudentExam(studentId);
                        %>
                        <h2><strong><%=activeExams%></strong></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <!-- Footer -->
    <jsp:include page="main_footer.jsp" flush="true"/>
    <!-- End of Footer -->

</div>