package com.Quang.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.OrderDetail;
import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.User;
import com.Quang.demo.repository.CartDetailRepository;
import com.Quang.demo.repository.CartRepository;
import com.Quang.demo.repository.OrderDetailRepository;
import com.Quang.demo.repository.OrderRepository;
import com.Quang.demo.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final CartDetailRepository cartDetailRepository;
  private final UserService userService;
  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  public ProductService(ProductRepository productRepository, CartRepository cartRepository,
      CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
      OrderDetailRepository orderDetailRepository) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.cartDetailRepository = cartDetailRepository;
    this.userService = userService;
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
  }

  public Product handleSaveProduct(Product newProduct) {
    Product product = this.productRepository.save(newProduct);
    return product;
  }

  // public List<Product> handleGetProducts() {
  // List<Product> products = this.productRepository.findAll();
  // return products;

  // }

  public Page<Product> handleGetProducts(Pageable pageable) {
    Page<Product> products = this.productRepository.findAll(pageable);
    return products;
  }

  public long handleCountAllProducts() {
    return this.productRepository.count();
  }

  public List<Order> handleGetOrders() {
    List<Order> orders = this.orderRepository.findAll();
    return orders;
  }

  public Product handleGetByID(long id) {
    Product product = this.productRepository.findById(id);
    return product;
  }

  public void handleDeleteProduct(long id) {
    this.productRepository.deleteById(id);
  }

  public void handleAddProductToCart(String email, long productId, HttpSession session) {
    User user = this.userService.handleGetUserByEmail(email);
    if (user != null) {
      // tìm cart của user
      Cart cart = this.cartRepository.findByUser(user);
      // nếu user đó không có cart thì tạo mới
      if (cart == null) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setSum(0);

        cart = this.cartRepository.save(newCart);
      }

      // tạo ra 1 cart_detail từ productId
      // Product product = this.productRepository.findById(productId);
      Optional<Product> optionalProduct = Optional.ofNullable(this.productRepository.findById(productId));

      if (optionalProduct.isPresent()) {
        Product product = optionalProduct.get();

        // check xem giỏ hàng đã có product đó chưa
        boolean isExistProductInCart = this.cartDetailRepository.existsByCartAndProduct(cart, product);
        int quantity;
        if (isExistProductInCart) {
          // nếu có rồi thì quantity +1
          CartDetail cd = this.cartDetailRepository.findByCartAndProduct(cart, product);
          quantity = (int) cd.getQuantity() + 1;
          cd.setQuantity(quantity);
          // cd.setPrice((int) product.getPrice() * quantity);
          this.cartDetailRepository.save(cd);

        } else {
          // chưa có thì tạo mới cart detail
          quantity = 1;

          CartDetail cd = new CartDetail();
          cd.setCart(cart);
          cd.setProduct(product);
          cd.setPrice(product.getPrice());
          cd.setQuantity(quantity);

          this.cartDetailRepository.save(cd);

          // khi thêm 1 sp mới vào cart thì sum tăng lên 1 và update session để hiện thị
          // trên navbar
          int s = cart.getSum() + 1;
          cart.setSum(s);
          this.cartRepository.save(cart);
          session.setAttribute("sumCart", s);
        }

      }

    }

  }

  public Cart handleGetCartByUser(User user) {
    Cart c = this.cartRepository.findByUser(user);
    return c;
  }

  public CartDetail handleGetCartDetailById(long id) {
    return this.cartDetailRepository.findById(id);
  }

  public void handleDeleteCartDetailById(long id) {
    this.cartDetailRepository.deleteById(id);
  }

  public void handleDeleteCart(long cartId) {
    this.cartRepository.deleteById(cartId);
  }

  public void handleSaveCart(Cart cart) {
    this.cartRepository.save(cart);
  }

  public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
    for (CartDetail cartDetail : cartDetails) {
      Optional<CartDetail> cdOptional = Optional.ofNullable(this.cartDetailRepository.findById(cartDetail.getId()));
      if (cdOptional.isPresent()) {
        CartDetail updateCartDetail = cdOptional.get(); // hàm get để lấy value bên trong biến optinal
        updateCartDetail.setQuantity(cartDetail.getQuantity());
        this.cartDetailRepository.save(updateCartDetail);
      }
    }
  }

  public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverPhone,
      String receiverAddress) {
    // create order
    Order order = new Order();
    order.setUser(user);
    order.setReceiverName(receiverName);
    order.setReceiverPhone(receiverPhone);
    order.setReceiverAddress(receiverAddress);
    order.setStatus("Pending");

    // create orderDetail
    // b1: get cart by user
    Cart cart = this.handleGetCartByUser(user);
    double total = 0;
    if (cart != null) {
      List<CartDetail> cds = cart.getCartDetails();

      for (CartDetail cd : cds) {
        total += cd.getPrice();// tính total price rồi tạo order
      }

      order.setTotalPrice(total);
      order = this.orderRepository.save(order);

      if (cds != null) {

        for (CartDetail cd : cds) {
          OrderDetail od = new OrderDetail();
          od.setPrice(cd.getPrice());
          od.setQuantity(cd.getQuantity());
          od.setOrder(order);
          od.setProduct(cd.getProduct());
          this.orderDetailRepository.save(od);

        }

      }

      // b2: delete cart detail and cart

      for (CartDetail cd : cds) {
        this.cartDetailRepository.deleteById(cd.getId());
      }

      cart.setSum(0);
      cart.getCartDetails().clear();
      this.cartRepository.save(cart);

      // bằng 1 thế lực wibu nào đó mà đ thể nào xóa được cart nên đổi sum thành 0 (có
      // thể là do liên kết vs user, idk nhg mà kệ sau pro tính tiếp)
      // this.cartRepository.deleteById(cart.getId());

      // b3: update session
      session.setAttribute("sumCart", 0);

    }

  }

  public List<Order> handlegetAllOrders() {
    return this.orderRepository.findAll();
  }

  public Order handleGetOrderById(long id) {
    return this.orderRepository.findById(id);
  }

  public List<OrderDetail> handleGetOrderDetailsbyOrder(Order order) {
    return this.orderDetailRepository.findByOrder(order);
  }

  public void handleDeleteOrder(long id) {
    Order order = this.orderRepository.findById(id);
    List<OrderDetail> list = this.orderDetailRepository.findByOrder(order);
    if (list != null) {
      for (OrderDetail od : list) {
        this.orderDetailRepository.deleteById(od.getId());
      }
    }
    this.orderRepository.deleteById(id);
  }

  public List<Order> handleGetOrderByUser(User user) {
    return this.orderRepository.findByUser(user);
  }

}
