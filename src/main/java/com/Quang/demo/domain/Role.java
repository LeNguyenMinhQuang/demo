package com.Quang.demo.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// annotation để biến model thành table(entity, model) trong database
@Entity
@Table(name = "roles") // đặt tên khác cho table trong database
public class Role {

  // buộc phải có để khai báo id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // tự sinh id (Auto, Table, Sequence, indentity)
  private long id;

  private String name;
  private String description;

  // 1 role - many user, 1 role có thể thuộc nhiều user
  @OneToMany(mappedBy = "role")
  private List<User> users;

  public Role() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
  }

}
