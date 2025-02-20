package com.Quang.demo.service;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.User;
import com.Quang.demo.repository.UserRepositoty;

@Service
public class UserService {

  private final UserRepositoty userRepositoty;

  public UserService(UserRepositoty userRepositoty) {
    this.userRepositoty = userRepositoty;
  }

  public User handleSaveUser(User newUser) {
    User user = this.userRepositoty.save(newUser);
    return user;
  }

}
