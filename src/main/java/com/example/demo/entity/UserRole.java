package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int userId;

    @Column
    private int roleId;
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
//    private List<Role_permission> Role_permission;
}
