package com.Quang.demo.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.User;
import com.Quang.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

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
  public String getCart(Model model, HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    User user = new User();
    user.setId(userId);

    Cart cart = this.productService.handleGetCartByUser(user);
    if (cart != null) {
      List<CartDetail> cartDetails = cart.getCartDetails();

      double totalPrice = 0;
      for (CartDetail cd : cartDetails) {
        totalPrice += (cd.getPrice() * cd.getQuantity());
      }
      model.addAttribute("cartEmpty", false);
      model.addAttribute("lists", cartDetails);
      model.addAttribute("total", totalPrice);
    } else {
      model.addAttribute("cartEmpty", true);
    }

    return "client/cart/show";
  }

  @PostMapping("/delete-cart-product/{id}")
  public String postMethodName(Model model, @PathVariable long id, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    CartDetail cd = this.productService.handleGetCartDetailById(id);
    Cart cart = cd.getCart();
    this.productService.handleDeleteCartDetailById(id);
    int sum = cart.getSum();

    if (sum <= 1) {
      int newSum = 0;
      cart.setSum(newSum);
      this.productService.handleSaveCart(cart);
      session.setAttribute("sumCart", newSum);
    } else {
      int newSum = sum - 1;
      cart.setSum(newSum);
      this.productService.handleSaveCart(cart);
      session.setAttribute("sumCart", newSum);

    }

    return "redirect:/cart";
  }

}
