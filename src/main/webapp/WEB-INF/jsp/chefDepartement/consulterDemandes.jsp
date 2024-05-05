<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="ISO-8859-1">
    <title>Demande</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</head>

<body class="page-top">
<div id="wrapper">
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="home">
            <div class="sidebar-brand-icon rotate-n-15">
            </div>
            <div class="sidebar-brand-text mx-3">Chef de Departement</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Dashboard -->
        <li class="nav-item ">
            <a class="nav-link" href="home">
                <i class="fas fa-fw fa-home"></i>
                <span>Accueil</span>
            </a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider">
        <!-- Heading -->
        <div class="sidebar-heading">
            Interface
        </div>
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item active">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-folder-open"></i>
                <span>Ressources</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="ajouterRessource">Créer Demande</a>
                    <a class="collapse-item" href="consulterDemandes">Consulter Demandes</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider d-none d-md-block">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                <i class="fas fa-fw fa-folder-open"></i>
                <span>Pannes</span>
            </a>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="declarerPanne">Declarer Panne</a>
                    <a class="collapse-item" href="declarationPannes">Consulter</a>
                    <a class="collapse-item" href="ordinateurEnseignant">Mes Ordinateurs</a>
                    <a class="collapse-item" href="imprimanteEnseignant">Mes Imprimantes</a>
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
            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading and Add Department Button -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Demandes Ressources</h1>
                </div>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Type de Ressource</th>
                                    <th>Date de Declaration</th>
                                    <th>Créée Par</th>
                                    <th>Details</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%List<Ressource> resources = (List<Ressource>) request.getAttribute("resources");%>
                                <% for(Ressource ressource : resources) {
                                    User user = ressource.getUser();
                                    String detailParams = "";
                                    if (ressource instanceof Ordinateur) {
                                        Ordinateur ordinateur = (Ordinateur) ressource;
                                        detailParams = "'" + ordinateur.getCpu() + "', '" + ordinateur.getRam() + "', '', '',";
                                    } else if (ressource instanceof Imprimante) {
                                        Imprimante imprimante = (Imprimante) ressource;
                                        detailParams = "'', '', '" + imprimante.getResolution() + "', '" + imprimante.getVitesseImpression() + "',";
                                    }
                                %>
                                <tr>
                                    <td><%= ressource.getTypeRessource() %></td>
                                    <td><%= ressource.getDateCreation() %></td>
                                    <td><%= user.getLast_name() %> <%= user.getFirst_name() %></td>
                                    <td>
                                        <button class="btn btn-primary" onclick="showDetails('<%= ressource.getId() %>', '<%= ressource.getTypeRessource() %>', <%= detailParams %> '<%= ressource.getDateCreation() %>', '<%= user.getLast_name() %> <%= user.getFirst_name() %>')">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger" onclick="deleteRessource('<%= ressource.getId() %>')">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                                <% } %>

                                </tbody>
                            </table>
                        </div>
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
                            <input type="text" class="form-control" id="firstName" name="firstName" value="<%=currentUser.getFirst_name()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Prenom</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" value="<%=currentUser.getLast_name()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="departement">Departement</label>
                            <input type="text" class="form-control" id="departement" name="departement" value="<%=currentUser.getDepartement().getNomDepartement()%>" disabled>
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
<!-- Ressource Details Modal -->
<div class="modal fade" id="ressourceDetailsModal" tabindex="-1" role="dialog" aria-labelledby="ressourceDetailsModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ressourceDetailsModalLabel">Edit Ressource Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editRessourceForm" >
                    <input type="hidden" id="ressourceId" name="ressourceId">
                    <div class="form-group">
                        <label for="type">Type</label>
                        <select class="form-control" id="type" name="type" onchange="toggleOptions(this.value)" disabled>
                            <option value="">Select...</option>
                            <option value="Ordinateur">Ordinateur</option>
                            <option value="Imprimante">Imprimante</option>
                        </select>
                    </div>
                    <div id="forTypeOrdinateur" style="display:none;">
                        <div class="form-group">
                            <label for="cpu">CPU</label>
                            <select class="form-control" id="cpu" name="cpu">
                                <option value="">Select...</option>
                                <option value="Intel i5">Intel i5</option>
                                <option value="Intel i7">Intel i7</option>
                                <option value="AMD Ryzen 5">AMD Ryzen 5</option>
                                <option value="AMD Ryzen 7">AMD Ryzen 7</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="ram">RAM</label>
                            <select class="form-control" id="ram" name="ram">
                                <option value="">Select...</option>
                                <option value="8GB">8GB</option>
                                <option value="16GB">16GB</option>
                                <option value="32GB">32GB</option>
                            </select>
                        </div>
                        <!-- Additional fields for "Ordinateur" -->
                    </div>
                    <div id="forTypeImprimante" style="display:none;">
                        <div class="form-group">
                            <label for="resolution">Resolution (dpi)</label>
                            <select class="form-control" id="resolution" name="resolution">
                                <option value="">Select...</option>
                                <option value="600">600 dpi</option>
                                <option value="1200">1200 dpi</option>
                                <option value="2400">2400 dpi</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="vitesseimpression">Vitesse Impression (ppm)</label>
                            <select class="form-control" id="vitesseimpression" name="vitesseimpression">
                                <option value="">Select...</option>
                                <option value="20">20 ppm</option>
                                <option value="35">35 ppm</option>
                                <option value="50">50 ppm</option>
                            </select>
                        </div>
                        <!-- Additional fields for "Imprimante" -->
                    </div>
                    <div class="form-group">
                        <label for="dateDeclaration">Date of Declaration</label>
                        <input type="date" class="form-control" id="dateDeclaration" name="dateDeclaration">
                    </div>
                    <div class="form-group">
                        <label for="createdBy">Created By</label>
                        <input type="text" class="form-control" id="createdBy" name="createdBy" disabled>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="saveChanges()"><i class="fas fa-save"></i> Save</button>
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
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
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
<script>
    function showDetails(id, type, cpu, ram, resolution, vitesseImpression, dateCreation, createdBy) {
        $('#ressourceId').val(id);
        $('#type').val(type).change();  // Trigger change to setup the form
        $('#dateDeclaration').val(dateCreation);
        $('#createdBy').val(createdBy);

        // Depending on the type, set the values accordingly
        if (type === "Ordinateur") {
            $('#cpu').val(cpu);
            $('#ram').val(ram);
        } else if (type === "Imprimante") {
            $('#resolution').val(resolution);
            $('#vitesseimpression').val(vitesseImpression);
        }

        $('#ressourceDetailsModal').modal('show');
    }


    function toggleOptions(selectedType) {
        // Hide all conditional fields first
        $('#forTypeOrdinateur').hide();
        $('#forTypeImprimante').hide();
        // Show relevant fields based on selection
        if (selectedType === 'Ordinateur') {
            $('#forTypeOrdinateur').show();
        } else if (selectedType === 'Imprimante') {
            $('#forTypeImprimante').show();
        }
    }

    $(document).ready(function() {
        // This ensures fields are shown/hidden based on the pre-selected value when the modal loads
        $('#type').change(function() {
            toggleOptions($(this).val());
        });
    });
</script>



<script type="text/javascript">$(document).ready(function() {
    $('#saveProfileBtn').on('click', function() {
        const newPassword = $('#password').val();
        $.ajax({
            type: 'POST',
            url: 'updatePassword',
            data: { password: newPassword},
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
<script>
    function saveChanges() {

    var formData = $('#editRessourceForm').serializeArray();
    var data = {
        typeRessource: $('#type').val(), // Always send the type
        cpu: '', // Default empty string
        ram: '',
        disqueDur: '',
        ecran: '',
        resolution: '',
        vitesseImpression: ''
    };

    // Fill the data object with actual form data
    formData.forEach(function(item) {
        if (item.value) { // Only overwrite if there's an actual value
            data[item.name] = item.value;
        }
    });

    // Check the type and ensure all relevant data fields are filled
    if (data.type === "Ordinateur") {
        data.cpu = $('#cpu').val();
        data.ram = $('#ram').val();
        data.disqueDur = $('#disqueDur').val(); // Assuming these fields exist
        data.ecran = $('#ecran').val();
    } else if (data.type === "Imprimante") {
        data.resolution = $('#resolution').val();
        data.vitesseImpression = $('#vitesseimpression').val();
    }

    console.log("DTO being sent:", JSON.stringify(data));

    $.ajax({
        type: 'POST',
        url: '/updateDemande/' + $('#ressourceId').val(),
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            alert('Changes saved successfully');
            $('#ressourceDetailsModal').modal('hide');
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error('Failed to update resource:', xhr.responseText);
        }
    });
    }
    function deleteRessource(id) {
        if (confirm('Are you sure you want to delete this resource?')) {
            $.ajax({
                type: 'DELETE',
                url: 'deleteRessource',
                data: { id: id },
                success: function(response) {
                    alert('Resource deleted successfully');
                    $('#ressourceDetailsModal').modal('hide');
                    location.reload();
                },
                error: function(xhr, status, error) {
                    alert('Error deleting resource: ' + error);
                }
            });
        }
    }
</script>

</body>
</html>
