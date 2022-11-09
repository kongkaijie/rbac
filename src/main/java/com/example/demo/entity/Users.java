package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String gender;
}
