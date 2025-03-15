package com.Quang.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Order;
import com.Quang.demo.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findAll();

  Order findById(long id);

  List<Order> findByUser(User user);

  void deleteById(long id);

}
