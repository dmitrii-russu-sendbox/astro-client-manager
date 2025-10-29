package com.example.customers.repository;

import com.example.customers.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // Частичный поиск (начинается с)
    List<Person> findByNameStartingWithIgnoreCase(String name);

    // Полное совпадение по имени
    Optional<Person> findByName(String name);

    @Query("SELECT p FROM Person p " +
            "WHERE MONTH(p.birthDate) = :month " +
            "ORDER BY FUNCTION('DAY', p.birthDate) ASC")
    List<Person> findByBirthMonthOrderByDayAsc(@Param("month") int month);

}
