package com.Quang.demo.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.Product_;
import com.Quang.demo.domain.User;
import com.Quang.demo.domain.dto.ProductCriteriaDTO;
import com.Quang.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
  // DI: nhúng ProductService vào HomePageController để lấy dữ liệu từ database
  private final ProductService productService;

  public HomePageController(ProductService productService) {
    this.productService = productService;
  }

  // Vì bên admin có pagination nên bên homepage sẽ xuất hiện bug do dùng chung
  // hàm handleGetProducts nên phải sửa pageable thành số trang cần (10 item)
  @GetMapping("/")
  public String getHomePage(Model model, HttpServletRequest request) {
    Pageable pageable = PageRequest.of(0, 8);
    Page<Product> prds = this.productService.handleGetProducts(pageable);
    List<Product> products = prds.getContent();
    model.addAttribute("products", products);
    // HttpSession session = request.getSession(false); // lấy session từ request
    return "client/homepage/show";
  }

  @GetMapping("/product/{id}")
  public String getDetail(Model model, @PathVariable long id) {
    Product product = this.productService.handleGetByID(id);
    model.addAttribute("product", product);
    Pageable pageable = PageRequest.of(0, 8);
    Page<Product> prds = this.productService.handleGetProducts(pageable);
    List<Product> products = prds.getContent();
    model.addAttribute("products", products);

    return "client/homepage/detail";
  }

  @GetMapping("/access-deny")
  public String getDenyPage(Model model) {
    return "client/deny";
  }

  @GetMapping("/order-history")
  public String getOrderHistory(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    User user = new User();
    user.setId((long) session.getAttribute("id"));
    List<Order> orders = this.productService.handleGetOrderByUser(user);
    model.addAttribute("orders", orders);
    return "client/cart/orderHistory";
  }

  @GetMapping("/shop")
  public String getShopPage(Model model, ProductCriteriaDTO productCriteriaDTO,
      HttpServletRequest request
  // @RequestParam("page") Optional<String> pageOptional,
  ) {
    // int page = 1;
    // try {
    // if (pageOptional.isPresent()) {
    // page = Integer.parseInt(pageOptional.get());
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // }

    // Spec
    int page = Optional.ofNullable(productCriteriaDTO.getPage()).isPresent()
        ? Integer.parseInt(productCriteriaDTO.getPage().get())
        : 1;
    Pageable pageable = PageRequest.of(page - 1, 8);
    if (productCriteriaDTO.getSort() != null) {
      if (productCriteriaDTO.getSort().isPresent()) {
        String sort = productCriteriaDTO.getSort().get();
        if (sort.equals("ASC")) {
          pageable = PageRequest.of(page - 1, 8, Sort.by(Product_.PRICE).ascending());
        } else if (sort.equals("DESC")) {
          pageable = PageRequest.of(page - 1, 8, Sort.by(Product_.PRICE).descending());
        } else {
          pageable = PageRequest.of(page - 1, 8);
        }
      }
    }
    // Page<Product> prds = this.productService.handleGetProducts(pageable);
    Page<Product> prds = this.productService.handleGetProductsWithSpec(pageable,
        productCriteriaDTO);
    long pages = prds.getTotalPages();
    List<Product> products = prds.getContent();
    // Lấy queryString
    String qs = request.getQueryString();
    if (qs != null && !qs.isBlank()) {
      qs = qs.replace("page=" + page, "");
    }

    model.addAttribute("products", products);
    model.addAttribute("pages", pages);
    model.addAttribute("page", page);
    model.addAttribute("qs", qs);
    return "client/shop/show";
  }

}
