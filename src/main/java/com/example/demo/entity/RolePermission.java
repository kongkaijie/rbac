package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RolePermission {
    @Id
    private int id;
    @Column
    private int roleId;
    @Column
    private int permissionId;
}
