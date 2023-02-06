package com.biboheart.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "valid_items_import")
public class ValidConsumables {
    @Id
    private Long id;
    private String countryNumber;
    private String countryName;
}
