package com.biboheart.demos.repository;

import com.biboheart.demos.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByLocalName(String name);
}
