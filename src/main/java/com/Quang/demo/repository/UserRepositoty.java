package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.User;
import java.util.List;

@Repository
// public interface UserRepositoty extends CrudRepository<User, Long> {
public interface UserRepositoty extends JpaRepository<User, Long> {
  // định nghĩa các function để gọi bên service
  User save(User newUser); // save có thể cả create và update

  List<User> findAll();

  User findById(long id);

  List<User> findAllByEmail(String email); // do là list nên trả về 1 list =>> nên đặt là findAll

  User findFirstByEmail(String email); // do là User nên trả về 1 giá trị đầu tiên tìm được =>> nên đặt là findFirst
  // findTop2 => trả về 2, top3 => 3

}