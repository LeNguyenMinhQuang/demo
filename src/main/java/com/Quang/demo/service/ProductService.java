package com.Quang.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.OrderDetail;
import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.User;
import com.Quang.demo.domain.dto.ProductCriteriaDTO;
import com.Quang.demo.repository.CartDetailRepository;
import com.Quang.demo.repository.CartRepository;
import com.Quang.demo.repository.OrderDetailRepository;
import com.Quang.demo.repository.OrderRepository;
import com.Quang.demo.repository.ProductRepository;
import com.Quang.demo.service.specification.ProductSpec;

import jakarta.servlet.http.HttpSession;

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

  // tạo đối tượng Specification ~ Where trong mySQL
  // root: đại diện table muốn truy vấn, được dùng để truy cập entity và fields
  // CriteriaQuery: tạo ra cấu trúc tổng quan của query, dùng để modify the
  // select, join, group by, order by, etc. (ít dùng)
  // CriteriaBuilder: sử dụng predicates, để build ra điều kiện của câu query
  // Predicate trả về true hoặc false

  // tính đa hình của OOP (2 class cùng tên nhg khác biến truyền vào)

  public Page<Product> handleGetProducts(Pageable pageable) {
    Page<Product> products = this.productRepository.findAll(pageable);
    return products;
  }

  public Page<Product> handleGetProducts(Pageable pageable, String name) {
    Page<Product> products = this.productRepository.findAll(ProductSpec.nameLike(name), pageable);
    return products;
  }

  // name
  public Page<Product> handleGetProductsWithSpec(Pageable pageable, String name) {
    Page<Product> products = this.productRepository.findAll(ProductSpec.nameLike(name), pageable);
    return products;
  }

  public Page<Product> handleGetProductsWithSpec(Pageable pageable, ProductCriteriaDTO productCriteriaDTO) {
    Specification<Product> combinedSpec = Specification.where(null);

    if (productCriteriaDTO.getName() != null) {
      if (productCriteriaDTO.getName().isPresent()) {
        Specification<Product> currentSpecs = ProductSpec.nameLike(productCriteriaDTO.getName().get());
        combinedSpec = combinedSpec.and(currentSpecs);
      }
    }

    if (productCriteriaDTO.getTarget() != null) {
      if (productCriteriaDTO.getTarget().isPresent()) {
        Specification<Product> currentSpecs = ProductSpec.matchTargetList(productCriteriaDTO.getTarget().get());
        combinedSpec = combinedSpec.and(currentSpecs);
      }
    }
    if (productCriteriaDTO.getFactory() != null) {
      if (productCriteriaDTO.getFactory().isPresent()) {
        Specification<Product> currentSpecs = ProductSpec.matchFactoryList(productCriteriaDTO.getFactory().get());
        combinedSpec = combinedSpec.and(currentSpecs);
      }
    }

    if (productCriteriaDTO.getPrice() != null) {
      if (productCriteriaDTO.getPrice().isPresent()) {
        Specification<Product> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
        combinedSpec = combinedSpec.and(currentSpecs);
      }
    }

    return this.productRepository.findAll(combinedSpec, pageable);
  }

  // list price range
  public Specification<Product> buildPriceSpecification(
      List<String> price) {
    Specification<Product> combinedSpec = (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();

    for (String p : price) {
      double min = 0;
      double max = 0;

      switch (p) {
        case "10-":
          min = 1;
          max = 10;

          break;
        case "10-100":
          min = 10;
          max = 100;

          break;
        case "100-1000":
          min = 100;
          max = 1000;

          break;
        case "1000+":
          min = 1000;
          max = 100000;

          break;
      }

      if (min != 0 && max != 0) {
        Specification<Product> rangeSpec = ProductSpec.priceRangeList(min, max);
        combinedSpec = combinedSpec.or(rangeSpec);
      }
    }

    return combinedSpec;

  }

  // price range
  // public Page<Product> handleGetProductsWithSpec(Pageable pageable, String
  // priceRange) {
  // if (priceRange.equals("10-100")) {
  // double min = 10;
  // double max = 100;
  // Page<Product> products =
  // this.productRepository.findAll(ProductSpec.priceRange(min, max),
  // pageable);
  // return products;
  // } else if (priceRange.equals("100-1000")) {
  // double min = 100;
  // double max = 1000;
  // Page<Product> products =
  // this.productRepository.findAll(ProductSpec.priceRange(min, max),
  // pageable);
  // return products;
  // } else {
  // Page<Product> products = this.productRepository.findAll(pageable);
  // return products;
  // }
  // }

  // List factory
  // public Page<Product> handleGetProductsWithSpec(Pageable pageable,
  // List<String> factoryList) {
  // Page<Product> products =
  // this.productRepository.findAll(ProductSpec.matchFactoryList(factoryList),
  // pageable);
  // return products;
  // }

  // Min price
  // public Page<Product> handleGetProductsWithSpec(Pageable pageable, double
  // minPrice) {
  // Page<Product> products =
  // this.productRepository.findAll(ProductSpec.minPrice(minPrice),
  // pageable);
  // return products;
  // }

  // Max price
  // public Page<Product> handleGetProductsWithSpec(Pageable pageable, double
  // maxPrice) {
  // Page<Product> products =
  // this.productRepository.findAll(ProductSpec.maxPrice(maxPrice),
  // pageable);
  // return products;
  // }

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
