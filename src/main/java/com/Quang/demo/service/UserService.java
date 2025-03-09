package com.Quang.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.User;
import com.Quang.demo.domain.dto.RegisterDTO;
import com.Quang.demo.repository.UserRepositoty;

@Service
public class UserService {
  // DI
  private final UserRepositoty userRepositoty;

  public UserService(UserRepositoty userRepositoty) {
    this.userRepositoty = userRepositoty;
  }

  // Services

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

  public void handleDeleteUser(long id) {
    this.userRepositoty.deleteById(id);
  }

  // hàm để check email đã tồn tại chưa ( để register)
  public boolean handleCheckEmailExist(String email) {
    List<User> users = this.userRepositoty.findAllByEmail(email);
    return users.size() > 0;
  }

  public User handleFindUserByEmail(String email) {
    User user = this.userRepositoty.findFirstByEmail(email);
    return user;
  }

  // mapper để convert data từ DTO sang Entity
  public User handleRegisterDTOtoUser(RegisterDTO registerDTO) {
    User user = new User();
    user.setEmail(registerDTO.getEmail());
    user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
    user.setPassword(registerDTO.getPassword());
    return user;
  }

}
