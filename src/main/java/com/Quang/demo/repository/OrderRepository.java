package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
