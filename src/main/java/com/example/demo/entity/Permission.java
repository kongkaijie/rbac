package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String auth;

    @Column
    private String description;
}
