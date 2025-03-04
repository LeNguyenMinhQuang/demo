package com.Quang.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Quang.demo.domain.Product;

@Controller
public class ProductController {

  @GetMapping("/admin/product")
  public String getProducts() {
    return "/admin/product/show";
  }

  @GetMapping("/admin/product/create")
  public String createProductPage(Model model) {
    model.addAttribute("newProduct", new Product());
    return "/admin/product/create";
  }

}
