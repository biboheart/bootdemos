package com.biboheart.dhelloworld.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
    private String name;
    private Integer age;
    private Integer sex;
    private String addres;
}
