<%@ page import="gestionRessource.backend.model.*" %>
<%@ page import="gestionRessource.backend.model.Departement" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="ISO-8859-1">
    <title>Acceuil</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body class="page-top">
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
                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <div class="topbar-divider d-none d-sm-block"></div>
                    <%@ include file="../notification.jspf" %>
                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Personnels</h1>
                </div>
                <!-- Content Row -->
                <!-- Demande Card Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Créer un nouveau utilisateur</h6>
                    </div>
                    <div class="card-body">
                        <form id="createUserForm">
                            <!-- User data collection fields -->
                            <div class="form-group">
                                <label for="firstname">First Name:</label>
                                <input type="text" id="firstname" class="form-control" placeholder="Enter first name">
                            </div>

                            <div class="form-group">
                                <label for="lastname">Last Name:</label>
                                <input type="text" id="lastname" class="form-control" placeholder="Enter last name">
                            </div>

                            <div class="form-group">
                                <label for="login">Login:</label>
                                <input type="text" id="login" class="form-control" placeholder="Enter login">
                            </div>

                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" id="password" class="form-control" placeholder="Enter password">
                            </div>

                            <div class="form-group">
                                <label for="departementId">Department:</label>
                                <select id="departementId" class="form-control">
                                    <option value="" disabled selected>Select a department</option>
                                    <%-- Assuming a list of departments is available --%>
                                    <%
                                        List<Departement> departements = (List<Departement>) session.getAttribute("departements");
                                        if (departements != null) {
                                            for (Departement dep : departements) {
                                    %>
                                    <option value="<%= dep.getId() %>"><%= dep.getNomDepartement() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="role">Role:</label>
                                <select id="role" class="form-control">
                                    <option value="" disabled selected>Select a role</option>
                                    <%-- Displaying roles from the Role enum --%>
                                    <%
                                        for (Role role : Role.values()) {
                                    %>
                                    <option value="<%= role.toString() %>"><%= role.toString() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <!-- JavaScript button to handle form submission -->
                            <button type="button" onclick="submitUserForm()" class="btn btn-primary mt-3">Create User</button>
                        </form>

                    </div>
                </div>
            </div>
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Master SDSI 2023/24</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
        </div>
        <!-- End of Content -->
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
                    <a class="btn btn-primary" href="logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Profile Modal -->
    <div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profileModalLabel"  aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="profileModalLabel">Your Profile Information</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="profileForm">
                        <div class="form-group">
                            <label for="firstName1">Nom</label>
                            <input type="text" class="form-control" id="firstName1" name="firstName1" placeholder="Enter First Name" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="lastName1">Prenom</label>
                            <input type="text" class="form-control" id="lastName1" name="lastName1" placeholder="Enter Last Name" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="email1">Email</label>
                            <input type="email" class="form-control" id="email1" name="email1" placeholder="Enter Email" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="password1">Mot de passe</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="password1" placeholder="Enter Password" >
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" id="saveProfileBtn">Save Changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Success Modal -->
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Success</h5>
                <button type="button" id="closeSuccessModal" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                La demande est envoyée avec succée.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>
<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#togglePassword').on('click', function() {
            const passwordField = $('#password1');
            const fieldType = passwordField.attr('type');
            if (fieldType === 'password') {
                passwordField.attr('type', 'text');
                $('#togglePassword i').removeClass('fa-eye').addClass('fa-eye-slash');
            } else {
                passwordField.attr('type', 'password');
                $('#togglePassword i').removeClass('fa-eye-slash').addClass('fa-eye');
            }
        });
    });
</script>
<script type="text/javascript">$(document).ready(function() {
    $('#saveProfileBtn').on('click', function() {
        const newPassword = $('#password1').val();
        const email = $('#email1').val();
        $.ajax({
            type: 'POST',
            url: 'UpdatePassword',
            data: { newPassword: newPassword, email: email },
            success: function(response) {
                alert('Password updated successfully');
                $('#profileModal').modal('hide');
            },
            error: function(xhr, status, error) {
                console.error('Error updating password:', error);
            }
        });
    });
});
</script>
<<script>
    function submitUserForm() {
        const form = document.getElementById("createUserForm");
        const user = {
            first_name: form.querySelector("#firstname").value,
            last_name: form.querySelector("#lastname").value,
            login: form.querySelector("#login").value,
            password: form.querySelector("#password").value,
            departementId: form.querySelector("#departementId").value,
            role: form.querySelector("#role").value
        };
        fetch("/api/user/addUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json" // Ensure proper content type
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/Personnels'
                } else {
                    alert("Failed to create user");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred while creating the user");
            });
    }
</script>
</body>
</html>
