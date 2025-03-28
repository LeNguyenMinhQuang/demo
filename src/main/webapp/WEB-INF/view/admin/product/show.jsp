<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
      </head>

      <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />
        <div id="layoutSidenav">
          <jsp:include page="../layout/sidebar.jsp" />

          <div id="layoutSidenav_content">
            <main>
              <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
                <ol class="breadcrumb mb-4">
                  <li class="breadcrumb-item active">Dashboard</li>
                  <li class="breadcrumb-item active">Product</li>
                </ol>
                <div class="container mt-5">
                  <div class="row">
                    <div class="col-12 mx-auto">
                      <div class="d-flex justify-content-between">
                        <h3>Table products</h3>
                        <!-- mặc định thẻ a là method get -->
                        <a href="/admin/product/create" class="btn btn-primary">Create new product</a>
                      </div>
                      <hr />
                      <table class="table table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Factory</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="product" items="${productsList}">
                            <!-- forEach var items -->
                            <tr>
                              <th>${product.id}</th>
                              <th>${product.name}</th>
                              <th>${product.price}</th>
                              <th>${product.factory}</th>
                              <th>
                                <a href="/admin/product/${product.id}" class="btn btn-success">View</a>
                                <a href="/admin/product/update/${product.id}" class="btn btn-warning">Update</a>
                                <a href="/admin/product/delete/${product.id}" class="btn btn-danger">Delete</a>
                              </th>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                      <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                          <li class="page-item">
                            <a class="${(currentPage eq 1) ? 'disabled page-link' : 'page-link'}"
                              href="/admin/product?page=${currentPage - 1}" aria-label="Previous">
                              <span aria-hidden="true">&laquo;</span>
                            </a>
                          </li>
                          <!-- phần sau dấu ? là queryString, nó sẽ ko ảnh hưởng tới đường link url gọi bên server -->
                          <c:forEach begin="1" end="${pages}" varStatus="status">
                            <li class="page-item"><a
                                class="${(status.index) eq currentPage ? 'active page-link' : 'page-link'}"
                                href="/admin/product?page=${status.index}">${status.index}</a></li>
                          </c:forEach>

                          <li class="page-item">
                            <a class="${(currentPage eq pages) ? 'disabled page-link' : 'page-link'}"
                              href="/admin/product?page=${currentPage + 1}" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span>
                            </a>
                          </li>
                        </ul>
                      </nav>
                    </div>
                  </div>
                </div>
              </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
          </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
          crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
      </body>

      </html>