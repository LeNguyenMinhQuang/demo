<div class="container-fluid fixed-top">
  <div class="container topbar bg-primary d-none d-lg-block">
    <div class="d-flex justify-content-between">
      <div class="top-info ps-2">
        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123
            Street, New York</a></small>
        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#"
            class="text-white">Email@Example.com</a></small>
      </div>
      <div class="top-link pe-2">
        <a href="#" class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
        <a href="#" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
        <a href="#" class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
      </div>
    </div>
  </div>
  <div class="container px-0">
    <nav class="navbar navbar-light bg-white navbar-expand-xl">
      <a href="index.html" class="navbar-brand">
        <h1 class="text-primary display-6">Fruitables</h1>
      </a>
      <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="fa fa-bars text-primary"></span>
      </button>
      <div class="collapse navbar-collapse bg-white justify-content-between mx-5" id="navbarCollapse">
        <div class="navbar-nav">
          <a href="/" class="nav-item nav-link active">Home</a>
          <a href="/shop" class="nav-item nav-link">Shop</a>
        </div>
        <!-- nếu có ng dùng đã đăng nhập mới hiện 2 cái dưới -->
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          <c:if test="${not empty pageContext.request.userPrincipal}">
            <div class="d-flex m-3 me-0">
              <a href="/cart" class="position-relative me-4 my-auto">
                <i class="fa fa-shopping-bag fa-2x"></i>
                <span id="cartSum"
                  class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                  style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${sessionScope.sumCart}</span>
              </a>
              <div class="dropdown my-auto">
                <a href="#" class="dropdown" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
                  aria-expanded="false" data-bs-toggle="dropdown" aria-expanded="false">
                  <i class="fas fa-user fa-2x"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-end p-4" aria-labelledby="dropdownMenuLink">
                  <li class="d-flex align-items-center flex-column" style="min-width: 300px;">
                    <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
                      src="/images/avatar/${sessionScope.avatar}" />
                    <div class="text-center my-3">
                      <!-- lấy dữ liệu qua Spring security -->
                      <!-- <c:out value="${pageContext.request.userPrincipal.name}" /> -->
                      <!-- lấy dữ liệu qua Spring session -->
                      <c:out value="${sessionScope.fullName}" />
                    </div>
                  </li>
                  <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li><a class=" dropdown-item" href="/admin">Admin</a></li>
                  </c:if>
                  <li><a class=" dropdown-item" href="/order-history">History</a></li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li>
                    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                      <form:form method="post" action="/logout">
                        <input type="hidden" name="${_crsf.parameterName}" value="${_crsf.token}">
                        <button class="dropdown-item">Logout</button>
                      </form:form>
                  </li>
                </ul>
              </div>
          </c:if>
          <c:if test="${empty pageContext.request.userPrincipal}">
            <a href="/login" id="loginBtn" class="position-relative me-4 my-auto">Login</a>
          </c:if>
      </div>
  </div>
  </nav>
</div>
</div>