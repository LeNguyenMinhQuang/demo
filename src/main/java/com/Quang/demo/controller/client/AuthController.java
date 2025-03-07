package com.Quang.demo.controller.client;

import java.util.List;

import javax.naming.Binding;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Quang.demo.domain.User;
import com.Quang.demo.domain.dto.RegisterDTO;
import com.Quang.demo.service.RoleService;
import com.Quang.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;

  public AuthController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.roleService = roleService;
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    // DTO: Data Transfer Object - chuyển dữ liệu từ form register sang dạng phù hợp
    // với database ( firstName + lastName -> fullName)

    model.addAttribute("registerUser", new RegisterDTO());
    return "client/auth/register";
  }

  @PostMapping("/register")
  public String registerUser(Model model, @ModelAttribute("registerUser") @Valid RegisterDTO registerUser,
      BindingResult bindingResult) {
    List<FieldError> errors = bindingResult.getFieldErrors();
    for (FieldError error : errors) {
      System.out.println(">>>>" + error.getDefaultMessage());
      // model.addAttribute(error.getField(), error.getDefaultMessage());
    }
    if (bindingResult.hasErrors()) {
      return "client/auth/register";
    }
    User user = this.userService.handleRegisterDTOtoUser(registerUser);
    user.setPassword(this.passwordEncoder.encode(registerUser.getPassword()));
    user.setRole(this.roleService.handleGetRole("USER"));
    this.userService.handleSaveUser(user);

    return "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    return "client/auth/login";
  }

}
