package com.Quang.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.Quang.demo.service.UserService;

@Controller
// @RestController
public class UserController {

  // @Autowired : đây cũng là DI nhg ko nên dùng
  // đây là dependency injection

  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }
@RequestMapping("/")

  // demo userService và jsp
  // public String getHomePage(){
  //   // return this.userService.handleHello();
  //   return "hello";
  // }

  // demo Model
  public String getHomePage(Model model){
    String test = this.userService.handleHello();
      model.addAttribute("quang_var", test);
      model.addAttribute("quang_var2", "biến thứ 2");
      return "hello";
    }
  
}
