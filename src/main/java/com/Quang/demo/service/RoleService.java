package com.Quang.demo.service;

import org.springframework.stereotype.Service;

import com.Quang.demo.domain.Role;
import com.Quang.demo.repository.RoleRepository;

@Service
public class RoleService {
  // DI
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  // Service

  public Role handleGetRole(String name) {
    return this.roleRepository.findByName(name);
  }

}