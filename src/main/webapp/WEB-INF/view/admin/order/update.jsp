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
              <h3>Update an order with id: ${order.id}</h3>
              <hr />
              <!-- JSPL chú ý form: và path và modelAttribute -->
              <form:form action="/admin/order/update" method="post" modelAttribute="order"
                enctype="multipart/form-data">
                <div class="mb-3" style="display: none;">
                  <label class="form-label">Id:</label>
                  <form:input type="text" class="form-control" path="id" readonly="true" />
                </div>

                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Status:</label>
                  <form:select class="form-select" path="status">
                    <form:option value="Pending">Pending</form:option>
                    <form:option value="Shipping">Shipping</form:option>
                    <form:option value="Completed">Completed</form:option>
                    <form:option value="Canceled">Canceled</form:option>
                  </form:select>
                </div>


                <div class="col-12 mb-5">
                  <button type="submit" class="btn btn-primary ">Update</button>
                </div>

              </form:form>

            </div>
          </div>
        </div>
      </body>

      </html>