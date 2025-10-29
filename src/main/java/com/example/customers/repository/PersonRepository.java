package com.example.customers.repository;

import com.example.customers.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // Частичный поиск (начинается с)
    List<Person> findByNameStartingWithIgnoreCase(String name);

    // Полное совпадение по имени
    Optional<Person> findByName(String name);

}
