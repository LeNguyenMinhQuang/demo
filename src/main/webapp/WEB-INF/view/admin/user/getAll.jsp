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
            <div class="col-12 mx-auto">
              <div class="d-flex justify-content-between">
                <h3>Table users</h3>
                <!-- mặc định thẻ a là method get -->
                <a href="/admin/user/create" class="btn btn-primary">Create new</a>
              </div>
              <hr />
              <table class="table table-bordered table-hover">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="user" items="${userList}">
                    <!-- forEach var items -->
                    <tr>
                      <th>${user.id}</th>
                      <th>${user.fullName}</th>
                      <th>${user.email}</th>
                      <th>
                        <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                        <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                        <a href="/admin/user/delete/${user.id}" class="btn btn-danger">Delete</a>
                      </th>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </body>

      </html>