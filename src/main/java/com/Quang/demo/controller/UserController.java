package com.Quang.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Quang.demo.domain.User;
import com.Quang.demo.repository.UserRepositoty;
import com.Quang.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
  private final UserService userService;
  private final UserRepositoty userRepositoty;

  public UserController(UserService userService, UserRepositoty userRepositoty) {
    this.userService = userService;
    this.userRepositoty = userRepositoty;
  }

  @RequestMapping(value = "/")
  public String getHomePage(Model model) {
    List<User> arrUsers = this.userService.handleGetAllUser();
    System.out.println(arrUsers);
    return "/client/hello";
  }

  @RequestMapping(value = "/test")
  public String getTest(Model model) {
    List<User> arrUsers = this.userService.handleGetUsersByEmail("quang@quang.com");
    User user = this.userService.handleGetUserByEmail("quang@quang.com");
    System.out.println("list: " + arrUsers);
    System.out.println("one: " + user);
    return "/client/hello";
  }

  @RequestMapping(value = "/admin/user")
  public String getUserCreatePage(Model model) {
    model.addAttribute("newUser", new User());
    return "/admin/user/create";
  }

  @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
  public String createUser(Model model, @ModelAttribute("newUser") User newUser) {
    System.out.println("/admin/user/create run");
    System.out.println(newUser);
    this.userService.handleSaveUser(newUser);
    return "/client/hello";
  }

}
