package com.example.demo.repository;

import com.example.demo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Set<UserRole> findByUserId(int userid);
}
