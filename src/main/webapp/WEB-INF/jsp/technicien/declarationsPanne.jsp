<%@ page import="java.util.List" %>
<%@ page import="gestionRessource.backend.model.*" %>
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
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Ressources</div>
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

        <li class="nav-item">
            <a class="nav-link" href="resoudrePannes">
                <i class="fas fa-bug"></i>
                <span>Pannes</span></a>
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
            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading and Add Department Button -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Ordinateurs</h1>
                </div>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>type de ressource</th>
                                    <th>detail de la ressource</th>
                                    <th>date de la declaration</th>
                                    <th>detail de la panne</th>
                                    <th>etat de la panne</th>
                                    <th>technicien en charge</th>
                                    <th>modifier panne</th>
                                    <th>constat</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>type de ressource</th>
                                    <th>detail de la ressource</th>
                                    <th>date de la declaration</th>
                                    <th>detail de la panne</th>
                                    <th>etat de la panne</th>
                                    <th>technicien en charge</th>
                                    <th>modifier panne</th>
                                    <th>constat</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <% List<Ressource> ressources = (List<Ressource>) request.getAttribute("ressources");
                                for (Ressource ressource : ressources) {
                                    if(!ressource.getPannes().isEmpty()){
                                        for (Panne panne : ressource.getPannes()) { %>
                                        <tr>

                                            <td>
                                                <%= ressource.getTypeRessource()%>
                                            </td>
                                            <td><% if(ressource.getTypeRessource().equals("Ordinateur")){
                                                Ordinateur ordinateur = (Ordinateur) ressource;
                                            %>
                                                <%=ordinateur.getCpu()%> , <%=ordinateur.getDisqueDur()%> , <%=ordinateur.getEcran()%> , <%=ordinateur.getRam()%> , <%=ordinateur.getDateCreation()%>
                                                <%}else{
                                                    Imprimante imprimante = (Imprimante) ressource;
                                                %>
                                                <%=imprimante.getResolution()%> , <%=imprimante.getVitesseImpression()%> , <%=imprimante.getDateCreation()%>

                                                <%}%>

                                            </td>
                                            <td>
                                                <%= panne.getDateSignal()%>
                                            </td>
                                            <td>
                                                <%= panne.getDetail()%>
                                            </td>
                                            <td style="background-color:
                                                <% if(panne.getEtatPanne().equals(EtatPanne.Repare)) { %>
                                                    green
                                                <% } else if(panne.getEtatPanne().equals(EtatPanne.EnCours)) { %>
                                                    orange
                                                <% } else if(panne.getEtatPanne().equals(EtatPanne.NonRepare)) { %>
                                                    dodgerblue
                                                <% } else if(panne.getEtatPanne().equals(EtatPanne.Severe)) { %>
                                                    red
                                                <% } else { %>
                                                    gray
                                                <% } %>;
                                                    color: white">

                                                <% if(panne.getEtatPanne().equals(EtatPanne.NonRepare)) { %>
                                                Non Traité
                                                <% } else if(panne.getEtatPanne().equals(EtatPanne.EnCours)){ %>
                                                En Cours de Traitement
                                                <% } else if(panne.getEtatPanne().equals(EtatPanne.Severe)){ %>
                                                Panne Severe
                                                <% }else{%>
                                                    <%= panne.getEtatPanne() %>
                                                <%}%>
                                            </td>
                                            <td>
                                                <% if(panne.getEtatPanne().equals(EtatPanne.NonRepare)){%>
                                                    <button type="button" class="btn btn-success" onclick="affecterPanne('<%= panne.getId() %>')">
                                                        <span class="text">Traiter</span>
                                                    </button>
                                                <%}else{
                                                        if(currentUser.getId() == panne.getUser().getId()){%>
                                                            vous
                                                        <%}else {%>
                                                            <%= panne.getUser().getFirst_name() %> <%= panne.getUser().getLast_name() %>
                                                        <%}%>
                                                <%}%>
                                            </td>
                                            <td>
                                            <% if(panne.getUser() != null && currentUser.getId() == panne.getUser().getId()){%>
                                                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#panneModal<%= panne.getId() %>">
                                                    <i class="fas fa-edit"></i> Modifier
                                                </button>
                                                <%}%>
                                            </td>
                                            <td>
                                                <% if(panne.getUser() != null && currentUser.getId() == panne.getUser().getId() && panne.getEtatPanne()==EtatPanne.Severe){%>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#constatModal<%= panne.getId() %>">
                                                        <i class="fas fa-pencil-alt"></i> <!-- Icône de crayon de Font Awesome -->
                                                    </button>
                                                <%} else if (panne.getUser() != null && currentUser.getId() == panne.getUser().getId() && panne.getEtatPanne() != EtatPanne.Severe) {%>
                                                    pour rediger constat vous devez d'abord changer l etat de panne a severe
                                                <%}%>
                                            </td>
                                        </tr>
                                        <!-- Panne modal -->
                                        <div class="modal fade" id="panneModal<%= panne.getId() %>" tabindex="-1" role="dialog" aria-labelledby="panneModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="panneModalLabel">Détails de la Panne</h5>
                                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Type de ressource: <%= panne.getRessource().getTypeRessource() %></p>
                                                        <p>Date de déclaration: <%= panne.getDateSignal() %></p>
                                                        <p>Détails de la panne: <%= panne.getDetail() %></p>
                                                        <p>État de la panne:
                                                            <select id="etatPanne<%= panne.getId() %>" class="form-control">
                                                                <option value="EnCours">En Cours de Traitement</option>
                                                                <option value="Severe">Panne Sévère</option>
                                                                <option value="Repare">Réparée</option>
                                                            </select>
                                                        </p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Fermer</button>
                                                        <button class="btn btn-primary" onclick="changerEtatPanne('<%= panne.getId() %>')">Enregistrer</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                <!-- Constat modal -->
                                <div class="modal fade" id="constatModal<%= panne.getId() %>" tabindex="-1" role="dialog" aria-labelledby="constatModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="constatModalLabel">Détails du constat</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form id="constatForm<%= panne.getId() %>">
                                                    <div class="form-group">
                                                        <label for="explication<%= panne.getId() %>">Explication:</label>
                                                        <textarea class="form-control" id="explication<%= panne.getId() %>" placeholder="Explication" required></textarea>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="frequenceConstat<%= panne.getId() %>">Fréquence du constat:</label>
                                                        <select class="form-control" id="frequenceConstat<%= panne.getId() %>" required>
                                                            <option value="rare">rare</option>
                                                            <option value="frequente">frequente</option>
                                                            <option value="permanente">permanente</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="ordre<%= panne.getId() %>">Ordre de la panne:</label>
                                                        <% if (ressource.getTypeRessource().equals("Ordinateur")) { %>
                                                        <select class="form-control" id="ordre<%= panne.getId() %>" required>
                                                            <option value="défaut_système">Défaut système</option>
                                                            <option value="logiciel_utilitaire">Logiciel utilitaire</option>
                                                            <option value="matériel">Matériel</option>
                                                        </select>
                                                        <% } else if (ressource.getTypeRessource().equals("Imprimante")) { %>
                                                        <input type="text" class="form-control" id="explication<%= panne.getId() %>" placeholder="Explication" value="matériel" disabled>
                                                        <% } %>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="dateApparition<%= panne.getId() %>">Date d'apparition:</label>
                                                        <input type="date" class="form-control" id="dateApparition<%= panne.getId() %>" required>
                                                    </div>
                                                    <!-- You can add more fields here if needed -->
                                                    <input type="hidden" id="panneId<%= panne.getId() %>" value="<%= panne.getId() %>">
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Fermer</button>
                                                <button class="btn btn-primary" onclick="enregistrerConstat(<%= panne.getId() %>)">Enregistrer</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <% }%>

                                <% }
                                } %>
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

<script type="text/javascript">
    function affecterPanne(panneId) {
        $.ajax({
            type: 'POST',
            url: 'affecterPanne',
            data: { panneId: panneId },
            success: function(response) {
                // Refresh the page or update the table after successful deletion
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error('Error affecting panne:', error);
                // You can show an error message to the user if deletion fails
            }
        });
    }
</script>
<script type="text/javascript">
    function changerEtatPanne(panneId) {
        var nouvelEtat = $("#etatPanne" + panneId).val();
        $.ajax({
            type: 'POST',
            url: 'changerEtatPanne',
            data: { panneId: panneId, nouvelEtat: nouvelEtat },
            success: function(response) {
                // Rafraîchir la page ou mettre à jour la table après modification réussie
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error('Erreur lors du changement d\'état de la panne:', error);
                // Vous pouvez afficher un message d'erreur à l'utilisateur si la modification échoue
            }
        });
    }


</script>


<script type="text/javascript">
    function redigerConstat(panneId) {
        $.ajax({
            type: 'POST',
            url: 'constat',
            data: { panneId: panneId },
            success: function(response) {
                r
            },
            error: function(xhr, status, error) {
                console.error('Error affecting panne:', error);
                // You can show an error message to the user if deletion fails
            }
        });
    }
</script>


</body>
</html>
