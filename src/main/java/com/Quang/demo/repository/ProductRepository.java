package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Product;
import java.util.List;

@Repository
// public interface UserRepositoty extends CrudRepository<User, Long> {
public interface ProductRepository extends JpaRepository<Product, Long> {
  // định nghĩa các function để gọi bên service
  Product save(Product newProduct); // save có thể cả create và update

  List<Product> findAll();

  Product findById(long id);

  void deleteById(long id);

}