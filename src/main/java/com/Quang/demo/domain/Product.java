package com.Quang.demo.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.OneToMany;

// annotation để biến model thành table(entity, model) trong database
@Entity
@Table(name = "products") // đặt tên khác cho table trong database
public class Product {

  // buộc phải có để khai báo id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // tự sinh id (Auto, Table, Sequence, indentity)
  private long id;

  @NotEmpty(message = "Name is not empty")
  private String name;

  @Min(value = 0, message = "Price must be greater than 0")
  private double price;

  private String image;

  @Column(columnDefinition = "MEDIUMTEXT")
  private String detailDesc;
  private String shortDesc;
  private String quantity;
  private String sold;
  private String factory;
  private String target;

  // detail: 1 detail chỉ mô tả 1 product, nhg 1 product thì có thể được miêu tả
  // bởi nhiều detail -> one to many

  @OneToMany(mappedBy = "product")
  private List<OrderDetail> orderDetails;

  public Product() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDetailDesc() {
    return detailDesc;
  }

  public void setDetailDesc(String detailDesc) {
    this.detailDesc = detailDesc;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getSold() {
    return sold;
  }

  public void setSold(String sold) {
    this.sold = sold;
  }

  public String getFactory() {
    return factory;
  }

  public void setFactory(String factory) {
    this.factory = factory;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", detailDesc="
        + detailDesc + ", shortDesc=" + shortDesc + ", quantity=" + quantity + ", sold=" + sold + ", factory=" + factory
        + ", target=" + target + "]";
  }

}
