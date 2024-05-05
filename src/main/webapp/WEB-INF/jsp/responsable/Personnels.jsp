<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <link href="css/ours.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>



<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="home">
            <div class="sidebar-brand-icon rotate-n-15">

            </div>
            <div class="sidebar-brand-text mx-3">Ressources</div>
        </a>
        <!-- Nav Item - Dashboard -->
        <li class="nav-item ">
            <a class="nav-link" href="home">
                <i class="fas fa-fw fa-home"></i>
                <span>Accueil</span>
            </a>
        </li>
        <hr class="sidebar-divider">
        <!-- Heading -->
        <div class="sidebar-heading">
            Interface
        </div>

        <!-- Nav Item - Charts -->
        <li class="nav-item active">
            <a class="nav-link" href="personnels" >
                <i class="fas fa-users"></i>
                <span>Personnels</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="AppelDoffres">
                <i class="fas fa-bullhorn"></i>
                <span>Proposition</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="AppelDoffres">
                <i class="fas fa-bullhorn"></i>
                <span>Appels d'offre</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="Ressources">
                <i class="fas fa-desktop"></i>
                <span>Ressources</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="Pannes">
                <i class="fas fa-bug"></i>
                <span>Pannes</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Fournisseurs" >
                <i class="fas fa-users"></i>
                <span>Fournisseurs</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <%@ include file="../notification.jspf" %>


                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%User currentUser = (User) session.getAttribute("user");%><%=currentUser.getFirst_name()%> <%=currentUser.getLast_name()%></span>
                            <img class="img-profile rounded-circle"
                                 src="/images/portrait/small/avatar-s-7.jpg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#profileModal">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3>liste des personnels</h3>

                    <div class="d-flex align-items-center">
                        <form action="/newPersonnels" class="mr-2">
                            <button class="btn btn-primary" type="submit">Add User</button>
                        </form>
                        <i class="fas fa-filter"></i>
                    </div>
                </div>


                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>nom</th>
                                    <th>departement</th>
                                    <th>Role</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>nom</th>
                                    <th>departement</th>
                                    <th>Role</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                                <tbody><%
                                    List<User> users= (List<User>) session.getAttribute("Users");
                                    if (users != null && !users.isEmpty()) {
                                        for (User use : users) {
                                            if(!use.getRole().equals(Role.Fournisseur)){
                                                if(!use.getRole().equals(Role.Responsable))
                                                {
                                %>

                                <tr onclick="detailProfil('<%= use.getLogin() %>')">

                                    <td>
                                        <p><%=use.getFirst_name()%>  <%=use.getLast_name()%></p></a>
                                    </td>
                                    <td><%if(use.getDepartement()!=null ){%><%=use.getDepartement().getNomDepartement()%><%}%></td>
                                    <td>
                                        <span class="status <% if(!use.getRole().equals(Role.Enseignant)){%> pending <%}else if(!use.getRole().equals(Role.ChefDepartement)){%>completed<%}else if(!use.getRole().equals(Role.Technicien)){%>pending<%}%>"><%=use.getRole()%></span>
                                    </td>
                                    <td>
                                        <form action="/deletePerso/<%= use.getLogin() %>"  >
                                            <div class="form-input">
                                                <button class="btn btn-primary " type="submit" style="background:#e63c3c" >delete</button>

                                            </div>
                                        </form>
                                    </td>
                                </tr>
                                <%}}}}%>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->
            <!-- Modal -->
            <div id="userModal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeModal()">&times;</span>
                    <div id="userDetails"></div>
                </div>
            </div>


        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
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
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
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
                <a class="btn btn-primary" href="logout">Logout</a>
            </div>
        </div>
    </div>
</div>
<script>
    function detailProfil(login) {
        window.location.href = '/Personnels/'+login;
    }

</script>


<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>

</body>

</html>
