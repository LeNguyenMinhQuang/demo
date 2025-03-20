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
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                            rel="stylesheet">

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



                        <!-- Fruits Shop Start-->
                        <div class="container-fluid fruite py-5">
                            <div class="container py-5">
                                <h1 class="mb-4">Fresh fruits shop</h1>
                                <div class="row g-4">
                                    <div class="col-lg-12">
                                        <div class="row g-4">
                                            <div class="col-xl-3">
                                                <div class="input-group w-100 mx-auto d-flex">
                                                    <input type="search" class="form-control p-3" placeholder="keywords"
                                                        aria-describedby="search-icon-1">
                                                    <span id="search-icon-1" class="input-group-text p-3"><i
                                                            class="fa fa-search"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-6"></div>
                                            <div class="col-xl-3">
                                                <div
                                                    class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                                                    <label for="fruits">Default Sorting:</label>
                                                    <select id="fruits" name="fruitlist"
                                                        class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                                        <option value="volvo">Nothing</option>
                                                        <option value="saab">Popularity</option>
                                                        <option value="opel">Organic</option>
                                                        <option value="audi">Fantastic</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row g-4">
                                            <div class="col-lg-3">
                                                <div class="row g-4">
                                                    <div class="col-12">
                                                        <div class="mb-2">
                                                            <h4>Factory</h4>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Apple" id="flexCheckDefault">
                                                            <label class="form-check-label" for="flexCheckDefault">
                                                                Apple
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Asus"
                                                                id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Asus
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Dell"
                                                                id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Dell
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Lenovo" id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Lenovo
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Acer"
                                                                id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Acer
                                                            </label>
                                                        </div>

                                                    </div>
                                                    <div class="col-12">
                                                        <div class="mb-2">
                                                            <h4>Target</h4>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Media" id="flexCheckDefault">
                                                            <label class="form-check-label" for="flexCheckDefault">
                                                                Media
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Coding" id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Coding
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Gaming" id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Gaming
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="mb-2">
                                                            <h4>Price</h4>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Media" id="flexCheckDefault">
                                                            <label class="form-check-label" for="flexCheckDefault">
                                                                Smaller than 10
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Coding" id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Bigger than 10 and Smaller than 100
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox"
                                                                value="Gaming" id="flexCheckChecked" checked>
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Bigger than 100
                                                            </label>
                                                        </div>
                                                    </div>

                                                    <div class="col-12">
                                                        <div class="mb-2">
                                                            <h4>Sort</h4>
                                                        </div>

                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio"
                                                                name="flexRadioDefault" id="flexRadioDefault1">
                                                            <label class="form-check-label" for="flexRadioDefault1">
                                                                ASC
                                                            </label>
                                                        </div>

                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio"
                                                                name="flexRadioDefault" id="flexRadioDefault1">
                                                            <label class="form-check-label" for="flexRadioDefault1">
                                                                DESC
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio"
                                                                name="flexRadioDefault" id="flexRadioDefault2" checked>
                                                            <label class="form-check-label" for="flexRadioDefault2">
                                                                None
                                                            </label>
                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                            <div class="col-lg-9">
                                                <div class="row g-4 justify-content-center">
                                                    <c:forEach var="product" items="${products}">
                                                        <div class="col-md-6 col-lg-6 col-xl-4">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="images/products/${product.image}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${product.factory}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4>${product.factory} ${product.name}</h4>
                                                                    <p>${product.shortDesc}</p>
                                                                    <div
                                                                        class="d-flex justify-content-between flex-lg-wrap">
                                                                        <p class="text-dark fs-5 fw-bold mb-0">
                                                                            ${product.price}
                                                                        </p>
                                                                        <a href="#"
                                                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                            Add to cart</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    <div class="col-12">
                                                        <div class="pagination d-flex justify-content-center mt-5">



                                                            <c:forEach begin="1" end="${pages}" varStatus="status">
                                                                <a class="${(status.index) eq page ? 'active page-link' : 'page-link'}"
                                                                    href="/shop?page=${status.index}">${status.index}</a>

                                                            </c:forEach>





                                                        </div>
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
                        <script
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                        <script src="/client/lib/easing/easing.min.js"></script>
                        <script src="/client/lib/waypoints/waypoints.min.js"></script>
                        <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                        <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                        <!-- Template Javascript -->
                        <script src="/client/js/main.js"></script>
                    </body>

                    </html>