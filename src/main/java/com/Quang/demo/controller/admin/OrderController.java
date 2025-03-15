package com.Quang.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.OrderDetail;
import com.Quang.demo.repository.OrderRepository;
import com.Quang.demo.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

  private final ProductService productService;
  private final OrderRepository orderRepository;

  public OrderController(ProductService productService, OrderRepository orderRepository) {
    this.productService = productService;
    this.orderRepository = orderRepository;
  }

  @GetMapping("/admin/order")
  public String GetOrderPage(Model model) {
    List<Order> orders = this.productService.handlegetAllOrders();
    if (orders != null) {
      model.addAttribute("orders", orders);
    }
    return "admin/order/show";
  }

  @GetMapping("/admin/order/{id}")
  public String GetItemPage(@PathVariable long id, Model model) {
    Order order = this.productService.handleGetOrderById(id);
    List<OrderDetail> list = this.productService.handleGetOrderDetailsbyOrder(order);
    if (list != null) {
      model.addAttribute("list", list);
    }
    return "admin/order/item";
  }

  @GetMapping("/admin/order/update/{id}")
  public String getUpdateOrderPage(Model model, @PathVariable long id) {
    Order order = this.productService.handleGetOrderById(id);
    model.addAttribute("order", order);
    return "admin/order/update";
  }

  @PostMapping("/admin/order/update")
  public String postUpdateOrderPage(Model model, @ModelAttribute("order") Order order) {
    Order currentOrder = this.productService.handleGetOrderById(order.getId());
    if (currentOrder != null) {
      currentOrder.setStatus(order.getStatus());

      this.orderRepository.save(currentOrder);
    }

    return "redirect:/admin/order";
  }

  @GetMapping("/admin/order/delete/{id}")
  public String getDeleteOrderPage(Model model, @PathVariable long id) {
    Order order = this.productService.handleGetOrderById(id);
    model.addAttribute("order", order);
    return "admin/order/delete";
  }

  @PostMapping("/admin/order/delete")
  public String postDeleteOrderPage(Model model, @ModelAttribute("order") Order order) {
    this.productService.handleDeleteOrder(order.getId());

    return "redirect:/admin/order";
  }

}
