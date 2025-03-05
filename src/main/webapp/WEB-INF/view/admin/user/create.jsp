<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                <form:form action="/admin/user/create" method="POST" modelAttribute="newUser" class="row"
                  enctype="multipart/form-data">
                  <div class="mb-3 col-12 col-md-6">
                    <label class="form-label">Email:</label>
                    <spring:bind path="newUser.email">
                      <!-- Cách 1: thêm thẻ tag bên trên và dùng spring:bind để đổi màu input đúng sai -->
                      <form:input type="email" class="form-control ${status.error ? 'is-invalid' : ''} " path="email" />
                    </spring:bind>
                    <form:errors path="email" cssClass="invalid-feedback" /> <!-- hiển thị lỗi nếu có dưới input -->
                  </div>
                  <div class="mb-3 col-12 col-md-6">
                    <label class="form-label">Password:</label>
                    <!-- Cách 2: dùng c:set để check xem có lỗi không, nếu có thì hiển thị lỗi -->
                    <c:set var="errorPassword">
                      <form:errors path="password" cssClass="invalid-feedback" />
                    </c:set>
                    <form:input type="password" class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                      path="password" />
                    ${errorPassword}


                  </div>
                  <div class=" mb-3 col-12 col-md-6">
                    <label class="form-label">Phone Number:</label>
                    <!-- path phải trùng với key của model trong domain -->
                    <form:input type="text" class="form-control" path="phone" />
                  </div>
                  <div class="mb-3 col-12 col-md-6">
                    <label class="form-label">Full Name:</label>
                    <spring:bind path="newUser.fullName">
                      <form:input type="text" class="form-control ${status.error ? 'is-invalid' : ''}"
                        path="fullName" />
                    </spring:bind>

                    <form:errors path="fullName" cssClass="invalid-feedback" />
                  </div>
                  <div class="mb-3 col-12 col-md-6">
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