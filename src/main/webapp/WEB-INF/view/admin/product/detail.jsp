<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product ${product.name}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <link rel="stylesheet" href="/css/styles.css">
      </head>

      <body>
        <div class="container mt-5">
          <div class="row">
            <div class="mx-auto">
              <div class="d-flex justify-content-between">
                <h3>Product detail: ${product.id}</h3>
                <a href="/admin/product" class="btn btn-primary">Back</a>
              </div>
              <hr />
              <div class="card" style="width: 60%">
                <div class="card-header">Product information</div>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">Id: ${product.id}</li>
                  <li class="list-group-item">Name: ${product.name}</li>
                  <li class="list-group-item">Price: ${product.price}</li>
                  <li class="list-group-item">Quantity: ${product.quantity}</li>
                  <li class="list-group-item">Factory: ${product.factory}</li>
                  <li class="list-group-item">Detail description: ${product.detailDesc}</li>
                  <li class="list-group-item">Short description: ${product.shortDesc}</li>
                  <li class="list-group-item">Target: ${product.target}</li>
                  <li class="list-group-item">Sold: ${product.sold}</li>
                  <li class="list-group-item">Image:
                    <img style="width: 100px;" src="/images/products/${product.image}" alt="">
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </body>

      </html>