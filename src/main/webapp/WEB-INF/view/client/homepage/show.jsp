<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

      <!-- Carousel Start -->
      <jsp:include page="../layout/carousel.jsp" />
      <!-- Carousel End -->


      <!-- Fruits Shop Start-->
      <div class="container-fluid fruite py-5">
        <div class="container py-5">
          <div class="tab-class text-center">
            <div class="row g-4">
              <div class="col-lg-4 text-start">
                <h1>Our Organic Products</h1>
              </div>

            </div>
            <div class="tab-content">
              <div id="tab-1" class="tab-pane fade show p-0 active">
                <div class="row g-4">
                  <div class="col-lg-12">
                    <div class="row g-4">
                      <!--Vòng lặp-->
                      <c:forEach var="product" items="${products}">
                        <div class="col-md-6 col-lg-4 col-xl-3">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img src="/images/products/${product.image}" class="img-fluid w-100 rounded-top" alt="">
                            </div>
                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style="top: 10px; left: 10px;">${product.target}</div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4 style="font-size: 22px">
                                <a href="/product/${product.id}">${product.factory} ${product.name}</a>
                              </h4>
                              <p style="font-size: 15px">${product.shortDesc}
                              </p>
                              <div class="d-flex justify-content-center flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">${product.price}$</p>
                                <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                                  <form:form action="/add-product-to-cart/${product.id}" method="post">
                                    <input type="hidden" name="${_crsf.parameterName}" value="${_crsf.token}">
                                    <button type="submit"
                                      class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                        class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</button>
                                  </form:form>

                              </div>
                            </div>
                          </div>
                        </div>
                      </c:forEach>


                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
      <!-- Fruits Shop End-->


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