<%@ page import="gestionRessource.backend.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Acceuil</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="page-top">
<div id="wrapper">
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Directeur">
            <div class="sidebar-brand-icon rotate-n-15">
            </div>
            <div class="sidebar-brand-text mx-3">Chef de Departement</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="Directeur">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Tableau De Bord</span>
            </a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider">
        <!-- Heading -->
        <div class="sidebar-heading">
            Interface
        </div>
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-folder-open"></i>
                <span>Demandes</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="CreateProject">Créer</a>
                    <a class="collapse-item" href="ConsulterProjets">Consulter</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider d-none d-md-block">
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
                    <h1 class="h3 mb-0 text-gray-800">Demandes</h1>
                </div>
                <!-- Content Row -->
                <!-- Project Card Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Créer Nouvelle Demande</h6>
                    </div>
                    <div class="card-body">
                        <form id="createProjectForm">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="typeDeRess">Type de ressource</label>
                                        <select class="form-control" id="typeDeRess" name="typeDeRess" required>
                                            <option value="" selected>Choose...</option>
                                            <option value="Ordinateur">Ordinateur</option>
                                            <option value="Imprimante">Imprimante</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="forTypeOrdinateur" style="display: none;">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="cpu">CPU</label>
                                        <select class="form-control" id="cpu" name="cpu" required>
                                            <option value="">Choose...</option>
                                            <!-- CPU options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="ram">RAM</label>
                                        <select class="form-control" id="ram" name="ram" required>
                                            <option value="">Choose...</option>
                                            <!-- RAM options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="ecran">Ecran</label>
                                        <select class="form-control" id="ecran" name="ecran" required>
                                            <option value="">Choose...</option>
                                            <!-- Ecran options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="disqueDur">Disque Dur</label>
                                        <select class="form-control" id="disqueDur" name="disqueDur" required>
                                            <option value="">Choose...</option>
                                            <!-- Disque Dur options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="forTypeImprimante" style="display: none;">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="resolution">Resolution</label>
                                        <select class="form-control" id="resolution" name="resolution" required>
                                            <option value="">Choose...</option>
                                            <!-- Resolution options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="vitesseimpression">Vitesse Impression</label>
                                        <select class="form-control" id="vitesseimpression" name="vitesseimpression" required>
                                            <option value="">Choose...</option>
                                            <!-- Vitesse Impression options will be dynamically added here -->
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <button type="submit" class="btn btn-primary btn-block">Ajouter</button>
                                </div>
                            </div>
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
    <div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profileModalLabel" aria-hidden="true">
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
                            <label for="firstName">Nom</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter First Name" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Prenom</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter Last Name" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" value="Unknown" disabled>
                        </div>
                        <div class="form-group">
                            <label for="password">Mot de passe</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="password" placeholder="Enter Password" >
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
            const passwordField = $('#password');
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
        const newPassword = $('#password').val();
        const email = $('#email').val();
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
<script type="text/javascript">
    $(document).ready(function() {
        $('#createProjectForm').submit(function(event) {
            event.preventDefault();
            var formData = $(this).serialize();
            $.ajax({
                type: 'POST',
                url: 'CreateProject',
                data: formData,
                success: function(response) {
                    if (response.trim() === "This project already exists") {
                        $('#createProjectErrorModal').modal('show');
                    } else {
                        $('#assignProjectManagerModal').modal('show');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error creating project:', error);
                    $('#createProjectErrorModal').modal('show');
                }
            });
        });
    });
</script>
<script>
    $(document).ready(function() {
        $('#startDate').on('change', function() {
            var startDate = new Date($(this).val());
            var endDate = new Date($('#endDate').val());
            if (startDate > endDate) {
                $(this).val('');
            }
        });
        $('#endDate').on('change', function() {
            var startDate = new Date($('#startDate').val());
            var endDate = new Date($(this).val());
            if (endDate < startDate) {
                $(this).val('');
            }
        });
    });
</script>
<script>
    $(document).ready(function() {
        $('#startDate, #endDate').on('change', function() {
            var startDate = new Date($('#startDate').val());
            var endDate = new Date($('#endDate').val());
            if (startDate && endDate && startDate <= endDate) {
                var timeDifference = Math.abs(endDate.getTime() - startDate.getTime()+1);
                var durationDays = Math.ceil(timeDifference / (1000 * 3600 * 24));
                $('#duration').val(durationDays);
            } else {
                $('#duration').val('');
            }
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#assignProjectManagerBtn').click(function() {
            var projectName =$('#projectName').val();
            var selectedManagerId = $('#projectManager').val();
            $.ajax({
                type: 'POST',
                url: 'AssignProject',
                data: { managerId: selectedManagerId,projectName:  projectName},
                success: function(response) {
                    console.log('Project manager assigned successfully');
                    $('#createProjectSuccessModal').modal('show');
                    $('#assignProjectManagerModal').modal('hide');
                },
                error: function(xhr, status, error) {
                    console.error('Error assigning project manager:', error);
                }
            });
        });
    });
</script>
<script>
    // Here you might add JavaScript to handle showing/hiding of specific form sections based on the selected resource type.
    document.getElementById('typeDeRess').addEventListener('change', function() {
        var type = this.value;
        var ordinateurOptions = document.getElementById('forTypeOrdinateur');
        var imprimanteOptions = document.getElementById('forTypeImprimante');
        ordinateurOptions.style.display = (type === 'Ordinateur') ? 'block' : 'none';
        imprimanteOptions.style.display = (type === 'Imprimante') ? 'block' : 'none';
    });
</script>
</body>
</html>
