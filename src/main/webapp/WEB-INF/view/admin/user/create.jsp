<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <link rel="stylesheet" href="/css/styles.css">
      </head>

      <body>
        <div class="container mt-5">
          <div class="row">
            <div class="col-md6 col-12 mx-auto">
              <h3>Create an user</h3>
              <hr />
              <!-- JSPL chú ý form: và path và modelAttribute -->
              <form:form action="/admin/user/create" method="post" modelAttribute="newUser" class="row">
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Email:</label>
                  <form:input type="email" class="form-control" path="email" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Password:</label>
                  <form:input type="password" class="form-control" path="password" />
                </div>
                <div class=" mb-3 col-12 col-md-6">
                  <label class="form-label">Phone Number:</label>
                  <!-- path phải trùng với key của model trong domain -->
                  <form:input type="text" class="form-control" path="phone" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Full Name:</label>
                  <form:input type="text" class="form-control" path="fullName" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Address:</label>
                  <form:input type="text" class="form-control" path="address" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Role:</label>
                  <select class="form-select">
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                  </select>
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label for="avatarFile" class="form-label">Avatar:</label>
                  <input type="file" class="form-control" id="avatarFile" accept=".png, .jpg, .jpeg">
                </div>
                <div class="col-12 mb-3">
                  <img style="max-height: 250px; display:none" alt="avatarPreview" id="avatarPreview">
                </div>

                <div class="col-12 mb-5">
                  <button type="submit" class="btn btn-primary ">Create</button>
                </div>
              </form:form>
            </div>
          </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
          $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
              const imgURL = URL.createObjectURL(e.target.files[0]);
              $("#avatarPreview").attr("src", imgURL);
              $("#avatarPreview").css({ "display": "block" });
            });
          });
        </script>
      </body>

      </html>