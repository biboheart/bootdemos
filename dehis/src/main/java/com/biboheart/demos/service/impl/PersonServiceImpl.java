package com.biboheart.demos.service.impl;

import com.biboheart.demos.entity.Person;
import com.biboheart.demos.repository.PersonRepository;
import com.biboheart.demos.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person load(String name) {
        return personRepository.findByLocalName(name);
    }
}
