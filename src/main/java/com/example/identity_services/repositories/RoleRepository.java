package com.example.identity_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.identity_services.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
