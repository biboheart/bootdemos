package com.biboheart.dmultipledatabase.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bh_test")
public class Test {
    @Id
    private Integer id;
    private String name;
}
