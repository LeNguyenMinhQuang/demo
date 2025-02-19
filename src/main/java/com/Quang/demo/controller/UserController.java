package com.Quang.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quang.demo.service.UserService;

@RestController
public class UserController {

  // @Autowired : đây cũng là DI nhg ko nên dùng
  private final UserService userService;


  public UserController(UserService userService) {
    this.userService = userService;
  }


  @RequestMapping("/homepage")
  public String getHomePage(){
    return this.userService.handleHello();
  }
  
}
