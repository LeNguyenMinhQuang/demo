package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Cart;
import com.Quang.demo.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Cart findByUser(User user); // ko cần hàm find by userID vì truyền user vào nó cũng dò theo Id, do 2 bảng
                              // cart và user đã join với nhau

  Cart save(Cart cart);

  void deleteById(long cartId);

}
