package com.Quang.demo.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "carts")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Min(value = 0)
  private int sum;

  // user id: 1 user thì chỉ có 1 giỏ hàng
  @OneToOne()
  @JoinColumn(name = "user_id")
  private User user;

  // card_detail_id: 1 giỏ hàng chứa nhiều chi tiết giỏ hàng (1 chi tiết giỏ hàng
  // = 1 sản phẩm trong giỏ hàng)
  @OneToMany(mappedBy = "cart")
  List<CartDetail> cartDetails;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartDetail> getCartDetails() {
    return cartDetails;
  }

  public void setCartDetails(List<CartDetail> cartDetails) {
    this.cartDetails = cartDetails;
  }

  public Cart() {
  }

  public Cart(int sum, User user, List<CartDetail> cartDetails) {
    this.sum = sum;
    this.user = user;
    this.cartDetails = cartDetails;
  }

  @Override
  public String toString() {
    return "Cart [id=" + id + ", sum=" + sum + ", user=" + user + ", cartDetails=" + cartDetails + "]";
  }

}
