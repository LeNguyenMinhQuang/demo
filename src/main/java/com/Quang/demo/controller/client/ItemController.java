package com.Quang.demo.controller.client;

import org.springframework.stereotype.Controller;

import com.Quang.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

  private final ProductService productService;

  public ItemController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("/add-product-to-cart/{id}")
  public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
    Long productId = id;

    HttpSession session = request.getSession(false);

    String email = (String) session.getAttribute("email"); // (String) : ép kiểu từ kiểu object mà getAttribute trả về

    this.productService.handleAddProductToCart(email, productId, session);

    return "redirect:/";
  }

  @GetMapping("/cart")
  public String getCart() {
    return "client/cart/show";
  }

}
