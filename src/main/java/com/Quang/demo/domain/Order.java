package com.Quang.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// annotation để biến model thành table(entity, model) trong database
@Entity
@Table(name = "orders") // phải đặt tên vì ko thể tạo bảng order do trùng với phương thức orderBy của
                        // sql
public class Order {

  // buộc phải có để khai báo id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // tự sinh id (Auto, Table, Sequence, indentity)
  private long id;

  private double totalPrice;

  // user id, nhiều order thuộc về 1 user
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Order() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", totalPrice=" + totalPrice + "]";
  }

}
