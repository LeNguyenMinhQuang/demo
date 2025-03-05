package com.Quang.demo.domain;

import java.util.List;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// annotation để biến model thành table(entity, model) trong database
@Entity
@Table(name = "users") // đặt tên khác cho table trong database
public class User {

  // buộc phải có để khai báo id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // tự sinh id (Auto, Table, Sequence, indentity)
  private long id;

  @NotNull
  @Email
  private String email;

  @NotNull
  @Min(6)
  private String password;

  @NotNull
  private String fullName;

  private String address;
  private String phone;
  private String avatar;

  // roleId: 1 user có 1 role, nhiều user có cùng 1 role -> many user - 1 role
  // dùng joincolumn ở ownerSide(user có khóa ngoại) và mappedBy ở
  // inverseSide(role) để ko bị tạo bảng thừa và ràng buộc 2 bảng gốc với nhau
  @ManyToOne // (cascade = CascadeType.ALL) //đã fix bằng cách tạo 1 đối tượng role ở role
             // repository
  @JoinColumn(name = "role_id") // join 2 bảng, định nghĩa 1 trường trong database (ở đây là thêm trường
                                // role_id)
  private Role role;

  // 1 order của 1 user, 1 user có nhiều order = > user 1 - many order
  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  public User() {
  }

  // public User(long id, String email, String password, String fullName, String
  // address, String phone) {
  // this.id = id;
  // this.email = email;
  // this.password = password;
  // this.fullName = fullName;
  // this.address = address;
  // this.phone = phone;
  // }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getFullName() {
    return fullName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAvatar() {
    return avatar;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName + ", address="
        + address + ", phone=" + phone + ", avatar=" + avatar + "]";
  }

}
