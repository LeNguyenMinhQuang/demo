package com.Quang.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Quang.demo.domain.Role;

@Repository
// public interface UserRepositoty extends CrudRepository<User, Long> {
public interface RoleRepository extends JpaRepository<Role, Long> {
  // định nghĩa các function để gọi bên service

  Role findByName(String name); // truyền vào name ở input trang create tìm ra data tương ứng
}