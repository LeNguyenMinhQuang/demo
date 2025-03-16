package com.Quang.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.User;
import com.Quang.demo.service.ProductService;
import com.Quang.demo.service.UserService;

@Controller
public class DashboardController {
  private final UserService userService;
  private final ProductService productService;

  public DashboardController(UserService userService, ProductService productService) {
    this.userService = userService;
    this.productService = productService;
  }

  @GetMapping("/admin")
  public String getDashboard(Model model) {
    List<User> users = this.userService.handleGetAllUser();
    long productsSize = this.productService.handleCountAllProducts();
    List<Order> orders = this.productService.handleGetOrders();

    model.addAttribute("totalUser", users.size());
    model.addAttribute("totalProduct", productsSize);
    model.addAttribute("totalOrder", orders.size());

    return "admin/dashboard/show";
  }

}
