package com.biboheart.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bh_test_person")
public class Person {
    @Id
    private Integer id;
    private String localName;
    private String centreSn;
}
