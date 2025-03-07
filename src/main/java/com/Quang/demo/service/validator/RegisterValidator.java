package com.Quang.demo.service.validator;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.dto.RegisterDTO;
import com.Quang.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

  private final UserService userService;

  public RegisterValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
    boolean valid = true;

    // Check if password and confirmPassword are the same
    if (!user.getPassword().equals(user.getConfirmPassword())) {
      valid = false;
      context.buildConstraintViolationWithTemplate("Password and Confirm Password are not the same")
          .addPropertyNode("confirmPassword").addConstraintViolation().disableDefaultConstraintViolation();
    }

    // Check if email is already in use
    if (this.userService.handleCheckEmailExist(user.getEmail())) {
      valid = false;
      context.buildConstraintViolationWithTemplate("Email is already in use").addPropertyNode("email")
          .addConstraintViolation().disableDefaultConstraintViolation();
    }

    if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
      valid = false;
      context.buildConstraintViolationWithTemplate(
          "Password has to contain at least 8 characters, 1 uppercase, 1 lowercase, 1 number and 1 special character")
          .addPropertyNode("password").addConstraintViolation().disableDefaultConstraintViolation();
    }
    return valid;

  }

}
