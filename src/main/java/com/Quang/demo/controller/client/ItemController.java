package com.Quang.demo.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.User;
import com.Quang.demo.repository.OrderDetailRepository;
import com.Quang.demo.repository.OrderRepository;
import com.Quang.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

  private final ProductService productService;
  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  public ItemController(ProductService productService, OrderRepository orderRepository,
      OrderDetailRepository orderDetailRepository) {
    this.productService = productService;
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
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
      model.addAttribute("cart", cart); // modelAttribule của form checkout
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

  @PostMapping("/confirm-checkout")
  public String postConfirmCheckout(@ModelAttribute("cart") Cart cart) {
    List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
    this.productService.handleUpdateCartBeforeCheckout(cartDetails);

    return "redirect:/checkout";
  }

  @GetMapping("/checkout")
  public String getCheckout(Model model, HttpServletRequest request) {
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
      model.addAttribute("cart", cart); // modelAttribule của form checkout
    } else {
      model.addAttribute("cartEmpty", true);
    }

    return "client/cart/checkout";
  }

  @PostMapping("/place-order")
  public String postPlaceOrder(HttpServletRequest request, @RequestParam("receiverName") String receiverName,
      @RequestParam("receiverPhone") String receiverPhone, @RequestParam("receiverAddress") String receiverAddress) {

    HttpSession session = request.getSession(false);
    Long userId = (Long) session.getAttribute("id");
    User user = new User();
    user.setId(userId);

    this.productService.handlePlaceOrder(user, session, receiverName, receiverPhone, receiverAddress);

    return "redirect:/";
  }

}
