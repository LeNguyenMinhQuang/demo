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
              <h3>Create an Product</h3>
              <hr />
              <!-- JSPL chú ý form: và path và modelAttribute -->
              <form:form action="/admin/product/create" method="POST" modelAttribute="newProduct" class="row"
                enctype="multipart/form-data">
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Name:</label>
                  <form:input type="text" class="form-control" path="name" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Price:</label>
                  <form:input type="number" class="form-control" path="price" />
                </div>
                <div class=" mb-3 col-12 col-md-6">
                  <label class="form-label">Detail description:</label>
                  <!-- path phải trùng với key của model trong domain -->
                  <form:input type="text" class="form-control" path="detailDesc" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Short description:</label>
                  <form:input type="text" class="form-control" path="shortDesc" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Quantity:</label>
                  <form:input type="number" class="form-control" path="quantity" />
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Factory:</label>
                  <form:select class="form-select" path="factory">
                    <form:option value="ADMIN">Apple</form:option>
                    <form:option value="USER">Asus</form:option>
                    <form:option value="USER">Acer</form:option>
                    <form:option value="USER">Lenovo</form:option>
                    <form:option value="USER">Dell</form:option>
                  </form:select>
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label class="form-label">Target:</label>
                  <form:select class="form-select" path="target">
                    <form:option value="ADMIN">Media</form:option>
                    <form:option value="USER">Gaming</form:option>
                    <form:option value="USER">Coding</form:option>
                  </form:select>
                </div>
                <div class="mb-3 col-12 col-md-6">
                  <label for="imageFile" class="form-label">Image:</label>
                  <!-- thuộc tính name để bên controller có thể getParams -->
                  <input name="imageFileUpload" type="file" class="form-control" id="imageFile"
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
            const avatarFile = $("#imageFile");
            avatarFile.change(function (e) {
              const imgURL = URL.createObjectURL(e.target.files[0]);
              $("#avatarPreview").attr("src", imgURL);
              $("#avatarPreview").css({ "display": "block" });
            });
          });
        </script>
      </body>

      </html>