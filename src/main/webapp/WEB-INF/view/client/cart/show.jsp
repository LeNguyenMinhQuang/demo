<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
          <html lang="en">

          <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> -->

            <!-- Google Web Fonts -->
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link
              href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
              rel="stylesheet">

            <!-- Icon Font Stylesheet -->
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

            <!-- Libraries Stylesheet -->
            <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
            <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


            <!-- Customized Bootstrap Stylesheet -->
            <link href="/client/css/bootstrap.min.css" rel="stylesheet">

            <!-- Template Stylesheet -->
            <link href="/client/css/style.css" rel="stylesheet">
          </head>

          <body>

            <!-- Spinner Start -->
            <div id="spinner"
              class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
              <div class="spinner-grow text-primary" role="status"></div>
            </div>
            <!-- Spinner End -->
            <!-- Navbar start -->
            <jsp:include page="../layout/navbar.jsp" />
            <!-- Navbar End -->


            <c:choose>
              <c:when test="${cartEmpty==true}">
                <div class="container-fluid py-5" style="margin-top: 100px;">
                  <div class="container py-5">
                    <div class="table-responsive">
                      <p>Cart empty</p>
                    </div>
                  </div>
                </div>
              </c:when>
              <c:when test="${cartEmpty==false}">
                <!-- Cart Page Start -->
                <div class="container-fluid py-5" style="margin-top: 100px;">
                  <div class="container py-5">
                    <div class="table-responsive">
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Handle</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="cartDetail" items="${lists}" varStatus="status">
                            <tr>
                              <th scope="row">
                                <div class="d-flex align-items-center">
                                  <!-- join 2 table (cart detail và product) thì gọi được như dưới -->
                                  <!-- join 2 table bản chất là ghép 1 data bên bảng kia và bảng này -->
                                  <img src="images/products/${cartDetail.product.image}"
                                    class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                </div>
                              </th>
                              <td>
                                <p class="mb-0 mt-4">
                                  <a href="/product/${cartDetail.product.id}"
                                    target="_blank">${cartDetail.product.name}</a>
                                </p>
                              </td>
                              <td>
                                <p class="mb-0 mt-4">${cartDetail.product.price}</p>
                              </td>
                              <td>
                                <div class="input-group quantity mt-4" style="width: 100px;">
                                  <div class="input-group-btn">
                                    <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                      <i class="fa fa-minus"></i>
                                    </button>
                                  </div>
                                  <input type="text" class="form-control form-control-sm text-center border-0"
                                    value="${cartDetail.quantity}" data-cart-detail-id="${cartDetail.id}"
                                    data-cart-detail-price="${cartDetail.price}"
                                    data-cart-detail-index="${status.index}">
                                  <div class="input-group-btn">
                                    <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                      <i class="fa fa-plus"></i>
                                    </button>
                                  </div>
                                </div>
                              </td>
                              <td>
                                <p class="mb-0 mt-4" data-cart-detail-id="${cartDetail.id}">
                                  <fmt:formatNumber type="number" value="${cartDetail.quantity *cartDetail.price}" />

                                </p>
                              </td>
                              <td>
                                <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                                  <form:form method="post" action="/delete-cart-product/${cartDetail.id}">
                                    <input type="hidden" name="${_crsf.parameterName}" value="${_crsf.token}">
                                    <button class="btn btn-md rounded-circle bg-light border mt-4">
                                      <i class="fa fa-times text-danger"></i>
                                    </button>
                                  </form:form>

                              </td>

                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="row g-4 justify-content-end">
                      <div class="col-8"></div>
                      <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                        <div class="bg-light rounded">
                          <div class="p-4">
                            <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
                            <div class="d-flex justify-content-between mb-4">
                              <h5 class="mb-0 me-4">Subtotal:</h5>
                              <p class="mb-0" data-cart-total-price="${total}">
                                <fmt:formatNumber type="number" value="${total}" />
                              </p>
                            </div>
                            <div class="d-flex justify-content-between">
                              <h5 class="mb-0 me-4">Shipping</h5>
                              <div class="">
                                <p class="mb-0">Flat rate: $0.00</p>
                              </div>
                            </div>
                            <p class="mb-0 text-end">Shipping to VietNam.</p>
                          </div>
                          <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                            <h5 class="mb-0 ps-4 me-4">Total</h5>
                            <p class="mb-0 pe-4" data-cart-total-price="${total}">
                              <fmt:formatNumber type="number" value="${total}" />
                            </p>
                          </div>
                          <form:form action="/confirm-checkout" method="post" modelAttribute="cart">
                            <input type="hidden" name="${_crsf.parameterName}" value="${_crsf.token}">
                            <div style="display: none;">
                              <c:forEach var="cartDetail" items="${lists}" varStatus="status">
                                <div class="mb-3">
                                  <div class="form-group">
                                    <label>Id:</label>
                                    <form:input class="form-control" type="text" value="${cartDetail.id}"
                                      path="cartDetails[${status.index}].id" />
                                  </div>
                                  <div class="form-group">
                                    <label>Quantity:</label>
                                    <form:input class="form-control" type="text" value="${cartDetail.quantity}"
                                      path="cartDetails[${status.index}].quantity" />
                                  </div>
                                </div>
                              </c:forEach>
                            </div>
                            <button
                              class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
                              type="submit">Proceed Checkout</button>
                        </div>
                        </form:form>

                      </div>
                    </div>
                  </div>
                </div>
                <!-- Cart Page End -->
              </c:when>

            </c:choose>





            <!-- Footer Start -->
            <jsp:include page="../layout/footer.jsp" />
            <!-- Footer End -->

            <!-- Back to Top -->
            <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                class="fa fa-arrow-up"></i></a>


            <!-- JavaScript Libraries -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="/client/lib/easing/easing.min.js"></script>
            <script src="/client/lib/waypoints/waypoints.min.js"></script>
            <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
            <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Template Javascript -->
            <script src="/client/js/main.js"></script>
          </body>

          </html>