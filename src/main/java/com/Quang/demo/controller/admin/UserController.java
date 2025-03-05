package com.Quang.demo.controller.admin;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Quang.demo.domain.Role;
import com.Quang.demo.domain.User;

import com.Quang.demo.service.RoleService;
import com.Quang.demo.service.UploadService;
import com.Quang.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

  // đây là Depencencies injection
  private final UserService userService;
  private final RoleService roleService;
  private final UploadService uploadService;
  private final PasswordEncoder passwordEncoder;

  public UserController(UserService userService, UploadService uploadService,
      PasswordEncoder passwordEncoder, RoleService roleService) {
    this.userService = userService;
    this.uploadService = uploadService;
    this.passwordEncoder = passwordEncoder;
    this.roleService = roleService;
  }
  //

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
  // modelAttribute: lấy newUser từ form trong view
  // requestParam: lấy input name: avatarFileUpload trong view
  // Valid, BindingResult: kiểm tra dữ liệu nhập vào, phải để BindingResult sau
  // Valid
  public String createUser(Model model,
      @ModelAttribute("newUser") @Valid User newUser,
      BindingResult newUserBindingResult,
      @RequestParam("avatarFileUpload") MultipartFile file) {

    // validate
    List<FieldError> errors = newUserBindingResult.getFieldErrors();
    for (FieldError error : errors) {
      System.out.println(">>>>>" + error.getField() + " - " + error.getDefaultMessage());
    }

    if (newUserBindingResult.hasErrors()) { // nếu có lỗi thì trả về trang create
      return "/admin/user/create";
    }

    // avatar
    String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
    newUser.setAvatar(avatar);

    // password
    String hashPassword = this.passwordEncoder.encode(newUser.getPassword());
    newUser.setPassword(hashPassword);

    // role
    Role role = newUser.getRole();
    String roleName = role.getName();
    Role newRole = this.roleService.handleGetRole(roleName);
    newUser.setRole(newRole);

    // save
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
  public String updateUser(Model model, @ModelAttribute("user") User user,
      @RequestParam("avatarFileUpload") MultipartFile file) {
    User currentUser = this.userService.handleGetUserById(user.getId());
    if (currentUser != null) {
      // sửa và lưu user do ko có hàm update :((
      currentUser.setFullName(user.getFullName());
      currentUser.setPhone(user.getPhone());
      currentUser.setAddress(user.getAddress());

      // avatar
      String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
      currentUser.setAvatar(avatar);
      // role
      currentUser.setRole(this.roleService.handleGetRole(user.getRole().getName()));
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
