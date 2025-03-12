package com.Quang.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.Product;
import com.Quang.demo.domain.User;
import com.Quang.demo.repository.CartDetailRepository;
import com.Quang.demo.repository.CartRepository;
import com.Quang.demo.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final CartDetailRepository cartDetailRepository;
  private final UserService userService;

  public ProductService(ProductRepository productRepository, CartRepository cartRepository,
      CartDetailRepository cartDetailRepository, UserService userService) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.cartDetailRepository = cartDetailRepository;
    this.userService = userService;
  }

  public Product handleSaveProduct(Product newProduct) {
    Product product = this.productRepository.save(newProduct);
    return product;
  }

  public List<Product> handleGetProducts() {
    List<Product> products = this.productRepository.findAll();
    return products;

  }

  public Product handleGetByID(long id) {
    Product product = this.productRepository.findById(id);
    return product;
  }

  public void handleDeleteProduct(long id) {
    this.productRepository.deleteById(id);
  }

  public void handleAddProductToCart(String email, long productId) {
    User user = this.userService.handleGetUserByEmail(email);
    if (user != null) {
      // tìm cart của user
      Cart cart = this.cartRepository.findByUser(user);
      // nếu user đó không có cart thì tạo mới
      if (cart == null) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setSum(1);

        cart = this.cartRepository.save(newCart);
      }

      // tạo ra 1 cart_detail từ productId
      // Product product = this.productRepository.findById(productId);
      Optional<Product> optionalProduct = Optional.ofNullable(this.productRepository.findById(productId));

      if (optionalProduct.isPresent()) {
        Product product = optionalProduct.get();

        CartDetail cd = new CartDetail();
        cd.setCart(cart);
        cd.setProduct(product);
        cd.setPrice(product.getPrice());
        cd.setQuantity(1);

        this.cartDetailRepository.save(cd);
      }

    }

  }
}
