package com.Quang.demo.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

// Tự định nghĩa annotation để kiểm tra mật khẩu có đủ mạnh không
// Mật khẩu mạnh: chứa ít nhất 1 chữ cái viết hoa, 1 chữ cái viết thường, 1 số và 1 ký tự đặc biệt

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
  String message() default "Password have to contain 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character";

  Class<?>[] groups() default {};

  Class<?>[] payload() default {};
}
