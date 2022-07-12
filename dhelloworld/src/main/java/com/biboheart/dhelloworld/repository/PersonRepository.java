package com.biboheart.dhelloworld.repository;

import com.biboheart.dhelloworld.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNumberIn(Collection<String> numberList);
}
