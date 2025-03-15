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
                        <th scope="col">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:if test="${empty orders}">
                        <tr>
                          <td colspan="6">No history</td>
                        </tr>
                      </c:if>
                      <c:forEach var="order" items="${orders}" varStatus="status">
                        <tr>
                          <td colspan="2">
                            Order Id: ${order.id}
                          </td>
                          <td colspan="3">
                            ${order.totalPrice}
                          </td>
                          <td colspan="1">
                            ${order.status}
                          </td>
                        </tr>
                        <c:forEach var="od" items="${order.getOrderDetails()}">
                          <tr>
                            <td>
                              <img src="/images/products/${od.product.image}" alt="image" style="width: 80px;">
                            </td>
                            <td>${od.product.name}</td>
                            <td>${od.product.price}</td>
                            <td>${od.quantity}</td>
                            <td>${od.product.price * od.quantity}</td>
                            <td></td>
                          </tr>
                        </c:forEach>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>

              </div>
            </div>
            <!-- Cart Page End -->






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