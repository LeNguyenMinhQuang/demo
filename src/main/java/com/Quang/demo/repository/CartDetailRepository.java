package com.Quang.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.CartDetail;
import com.Quang.demo.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

  CartDetail save(CartDetail cartDetail);

  boolean existsByCartAndProduct(Cart cart, Product product);

  CartDetail findByCartAndProduct(Cart cart, Product product);

  CartDetail findById(long id);

  void deleteById(long id);

  // List<CartDetail> findByCartId(long cartId); --- ko cần cái này, chỉ cần
  // cart.getCartDetail()

}
