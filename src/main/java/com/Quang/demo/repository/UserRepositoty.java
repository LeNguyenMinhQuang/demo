package com.Quang.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.User;

@Repository
public interface UserRepositoty extends CrudRepository<User, Long> {
  User save(User newUser);
}