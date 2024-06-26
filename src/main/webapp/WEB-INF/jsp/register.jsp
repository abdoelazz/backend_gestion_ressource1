<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register</title>


    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">

            <div class="row">
                <div class="col-lg-5 d-none d-lg-block ">
                    <img src="/images/pages/favicon.png" alt="branding logo" style="margin-left: 7%;padding: 12%;width: 100%; height: auto; display: block;">
                </div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>



                        <form class="user" action="<c:url value='/register'/>" method="post">
                            <div class="form-group">
                                <select id="roleSelect" class="form-select form-control-user" required style="width: 300px">
                                    <option value="">Select Role</option>
                                    <option value="ENSEIGNANT">Enseignant</option>
                                    <option value="CHEF_DEPARTEMENT">Chef Departement</option>
                                    <option value="RESPONSABLE">Responsable</option>
                                    <option value="FOURNISSEUR">Fournisseur</option>
                                    <option value="TECHNICIEN">Technicien</option>
                                </select>
                            </div>
                            <div id="dynamicFields">
                            </div>
                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </button>
                            <hr>
                        </form>
                        <div class="text-center">
                            <a class="small" href="login">Already have an account? Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<script src="js/sb-admin-2.min.js"></script>

</body>


<script>
    document.getElementById('roleSelect').addEventListener('change', function() {
        var role = this.value;
        var dynamicFieldsDiv = document.getElementById('dynamicFields');
        dynamicFieldsDiv.innerHTML = ''; // Clear existing dynamic fields

        if (role === 'ENSEIGNANT') {
            dynamicFieldsDiv.innerHTML += `


                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" name="username" class="form-control form-control-user" id="exampleFirstName"
                                           placeholder="Username" required>
                                </div>
                                <div class="col-sm-6">
                                    <input type="email" name="email" class="form-control form-control-user" id="exampleInputEmail"
                                           placeholder="Email Address" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="text" name="departementId" class="form-control form-control-user" id="exampleDepartement"
                                       placeholder="Departement ID" required>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" name="password" class="form-control form-control-user"
                                           id="exampleInputPassword" placeholder="Password" required>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user"
                                           id="exampleRepeatPassword" placeholder="Repeat Password" required>
                                </div>
                            </div>

            `;
        } else if (role === 'CHEF_DEPARTEMENT') {
            dynamicFieldsDiv.innerHTML += `
                <div class="form-group">
                    <input type="text" name="departement" class="form-control form-control-user" id="exampleInputDepartement"
                           placeholder="Departement" required>
                </div>
                <div class="form-group">
                    <input type="text" name="additionalField" class="form-control form-control-user" id="exampleInputAdditionalField"
                           placeholder="Additional Field" required>
                </div>
            `;
        }
        // Add additional fields for other roles as needed
    });
</script>

</html>
