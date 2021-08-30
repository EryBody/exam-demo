<ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="jp.do?action=dashboard">
        <div class="sidebar-brand-icon rotate-n-15">
            <!--<i class="fas fa-laugh-wink"></i>-->
            <img src="img/c-logo-tps.png" alt="Caleb University" width="39.1" height="39.14">
        </div>
        <!--<div class="sidebar-brand-text mx-3">Caleb <sup>University</sup></div>-->
        <div class="sidebar-brand-text mx-3">EMS <sup></sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="jp.do?action=dashboard">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading text-light">
        Administrator Menu
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
            <i class=" fas fa-users"></i>
            <span>Users</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Manage Users</h6>
                <a class="collapse-item" href="jp.do?action=my-profile&mp=-1">My Profile</a>
                <a class="collapse-item" href="jp.do?action=user-role-list">User Roles</a>
                <a class="collapse-item" href="jp.do?action=user-list">Users</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Courses Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-wrench"></i>
            <span>Courses</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Manage Courses:</h6>
                <a class="collapse-item" href="jp.do?action=course-form">Add Course</a>
                <a class="collapse-item" href="jp.do?action=course-list">Manage Courses</a>
            </div>
        </div>
    </li>
    
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
          <span>Question</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header" >Manage Questions</h6>
            <a class="collapse-item" href="jp.do?action=question-form">Add Questions</a>
            <a class="collapse-item" href="jp.do?action=cascade-by-course">Manage Questions</a>
          </div>
        </div>
      </li>
      
      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="jp.do?action=admin-result-list">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Student Exams</span></a>
      </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>