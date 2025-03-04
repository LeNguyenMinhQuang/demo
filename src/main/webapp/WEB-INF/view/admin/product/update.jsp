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
              <h3>Update an user with id: ${user.id}</h3>
              <hr />
              <!-- JSPL chú ý form: và path và modelAttribute -->
              <form:form action="/admin/user/update/" method="post" modelAttribute="user" enctype="multipart/form-data">
                <div class="mb-3" style="display: none;">
                  <label class="form-label">Id:</label>
                  <form:input type="text" class="form-control" path="id" readonly="true" />
                </div>
                <div class="mb-3">
                  <label class="form-label">Email:</label>
                  <form:input type="email" class="form-control" path="email" readonly="true" />
                </div>
                <div class=" mb-3">
                  <label class="form-label">Phone Number:</label>
                  <!-- path phải trùng với key của model trong domain -->
                  <form:input type="text" class="form-control" path="phone" />
                </div>
                <div class="mb-3">
                  <label class="form-label">Full Name:</label>
                  <form:input type="text" class="form-control" path="fullName" />
                </div>
                <div class="mb-3">
                  <label class="form-label">Address:</label>
                  <form:input type="text" class="form-control" path="address" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Role:</label>
                  <!-- vì role trong user là một object sinh ra từ class Role, mà các option là String, nên ta phải để path đến role.name (là 1 String) -->
                  <form:select class="form-select" path="role.name">
                    <form:option value="ADMIN">ADMIN</form:option>
                    <form:option value="USER">USER</form:option>
                  </form:select>
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label for="avatarFile" class="form-label">Avatar:</label>
                  <!-- thuộc tính name để bên controller có thể getParams -->
                  <input name="avatarFileUpload" type="file" class="form-control" id="avatarFile"
                    accept=".png, .jpg, .jpeg">
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
              </form:form>

            </div>
          </div>
        </div>
      </body>

      </html>