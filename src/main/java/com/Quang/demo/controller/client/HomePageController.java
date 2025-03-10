package com.Quang.demo.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.Quang.demo.domain.Product;
import com.Quang.demo.service.ProductService;

@Controller
public class HomePageController {
  // DI: nhúng ProductService vào HomePageController để lấy dữ liệu từ database
  private final ProductService productService;

  public HomePageController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("")
  public String getHomePage(Model model) {
    List<Product> products = this.productService.handleGetProducts();
    model.addAttribute("products", products);
    return "client/homepage/show";
  }

  @GetMapping("/product/{id}")
  public String getDetail(Model model, @PathVariable long id) {
    Product product = this.productService.handleGetByID(id);
    model.addAttribute("product", product);
    List<Product> products = this.productService.handleGetProducts();
    model.addAttribute("products", products);

    return "client/homepage/detail";
  }

  @GetMapping("/access-deny")
  public String getDenyPage(Model model) {
    return "client/deny";
  }

}
