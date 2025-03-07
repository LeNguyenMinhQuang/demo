package com.Quang.demo.service.validator;

import com.Quang.demo.domain.dto.RegisterDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

  @Override
  public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
    boolean valid = true;

    // Check if password and confirmPassword are the same
    if (!user.getPassword().equals(user.getConfirmPassword())) {
      valid = false;
      context.buildConstraintViolationWithTemplate("Password and Confirm Password are not the same")
          .addPropertyNode("confirmPassword").addConstraintViolation().disableDefaultConstraintViolation();
    }

    return valid;
  }

}
