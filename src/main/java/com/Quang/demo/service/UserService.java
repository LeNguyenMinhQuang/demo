package com.Quang.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.User;
import com.Quang.demo.repository.UserRepositoty;

@Service
public class UserService {

  private final UserRepositoty userRepositoty;

  public UserService(UserRepositoty userRepositoty) {
    this.userRepositoty = userRepositoty;
  }

  public User handleGetUserById(long id) {
    return this.userRepositoty.findById(id);
  }

  public List<User> handleGetAllUser() {
    return this.userRepositoty.findAll();
  }

  public List<User> handleGetUsersByEmail(String email) {
    return this.userRepositoty.findAllByEmail(email);
  }

  public User handleGetUserByEmail(String email) {
    return this.userRepositoty.findFirstByEmail(email);
  }

  public User handleSaveUser(User newUser) {
    User user = this.userRepositoty.save(newUser);
    return user;
  }

}
