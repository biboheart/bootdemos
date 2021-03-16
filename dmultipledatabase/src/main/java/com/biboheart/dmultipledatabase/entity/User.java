package com.biboheart.dmultipledatabase.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bh_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
}
