package com.Quang.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.Quang.demo.domain.User;
import com.Quang.demo.service.UserService;

@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/admin/user")
  public String viewUser(Model model) {
    List<User> arrUsers = this.userService.handleGetAllUser();
    model.addAttribute("userList", arrUsers);
    return "/admin/user/show";
  }

  @GetMapping("/admin/user/{id}")
  // PathVariable để lấy {params}
  public String getAllUsers(Model model, @PathVariable long id) {
    User user = this.userService.handleGetUserById(id);
    model.addAttribute("user", user);
    return "/admin/user/detail";
  }

  @GetMapping("/test")
  public String getTest(Model model) {
    List<User> arrUsers = this.userService.handleGetUsersByEmail("quang@quang.com");
    User user = this.userService.handleGetUserByEmail("quang@quang.com");
    System.out.println("list: " + arrUsers);
    System.out.println("one: " + user);
    return "/client/hello";
  }

  @GetMapping("/admin/user/create")
  public String getUserCreatePage(Model model) {
    model.addAttribute("newUser", new User());
    // newUser trên sẽ liên kết vs modelAttribute trong trang view
    return "/admin/user/create";
  }

  @PostMapping("/admin/user/create")
  public String createUser(Model model, @ModelAttribute("newUser") User newUser) {
    this.userService.handleSaveUser(newUser);
    // redirect: chuyển hướng đến link khác
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/update/{id}")
  public String getUserUpdatePage(Model model, @PathVariable long id) {
    User user = this.userService.handleGetUserById(id);
    model.addAttribute("user", user);
    return "/admin/user/update";
  }

  @PostMapping("/admin/user/update/")
  public String updateUser(Model model, @ModelAttribute("user") User user) {
    User currentUser = this.userService.handleGetUserById(user.getId());
    if (currentUser != null) {
      // sửa và lưu user do ko có hàm update :((
      currentUser.setFullName(user.getFullName());
      currentUser.setPhone(user.getPhone());
      currentUser.setAddress(user.getAddress());
      this.userService.handleSaveUser(currentUser);
    }
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/delete/{id}")
  public String getUserDeletePage(Model model, @PathVariable long id) {
    model.addAttribute("id", id);
    User user = new User();
    user.setId(id);
    model.addAttribute("user", user);
    return "/admin/user/delete";
  }

  @PostMapping("/admin/user/delete")
  public String deleteUser(Model model, @ModelAttribute("user") User user) {
    this.userService.handleDeleteUser(user.getId());
    System.out.println("show:" + user.getId());
    // redirect: chuyển hướng đến link khác
    return "redirect:/admin/user";
  }

}
