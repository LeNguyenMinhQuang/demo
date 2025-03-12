package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Cart findByUser(User user);

  Cart save(Cart cart);

}
