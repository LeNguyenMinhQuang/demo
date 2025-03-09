package com.Quang.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// custom details service để viết code xác minh user từ database

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public CustomUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    com.Quang.demo.domain.User userInDatabase = this.userService.handleFindUserByEmail(username); // làm thế này khi
                                                                                                  // inport 2 class
                                                                                                  // trùng tên
    if (userInDatabase == null) {
      throw new UsernameNotFoundException(username + " not Found!");
    }

    return new User(userInDatabase.getEmail(), userInDatabase.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

  }

}
