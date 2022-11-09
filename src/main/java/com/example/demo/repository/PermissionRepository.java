package com.example.demo.repository;

import com.example.demo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
//    @Override
//    Optional<Permission> findById(Integer integer);

//    Permission findById(int integer);
}
