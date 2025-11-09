package com.example.customers.service;

import com.example.customers.entity.Person;
import com.example.customers.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }


    public Optional<Person> findByExactName(String name) {
        return repository.findByName(name);
    }


    public List<Person> searchByNameOrAll(String name) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll(); // все клиенты
        }
        return repository.findByNameStartingWithIgnoreCase(name);
    }


    public Person saveOrUpdateByName(Person updated) {
        return repository.findByName(updated.getName())
                .map(existing -> {
                    existing.setBirthDate(updated.getBirthDate());
                    existing.setBirthTime(updated.getBirthTime());
                    existing.setBirthPlace(updated.getBirthPlace());
                    existing.setCategory(updated.getCategory());
                    existing.setExtraInfo(updated.getExtraInfo());
                    return repository.save(existing);
                })
                .orElseGet(() -> repository.save(updated)); // если не найдено — создаём нового
    }


    public void delete(Person person) {
        repository.delete(person);
    }


    public List<Person> findByBirthMonthOrderedByDay(int month) {
        return repository.findByBirthMonthOrderByDayAsc(month);
    }


    public List<Person> findBirthdaysTodayAndTomorrow() {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        log.info("Поиск дней рождений на даты: {} и {}", today, tomorrow);

        List<Person> birthdays = repository.findBirthdaysTodayAndTomorrow(
                today.getMonthValue(), today.getDayOfMonth(),
                tomorrow.getMonthValue(), tomorrow.getDayOfMonth()
        );

        log.info("Найдено {} дней рождений", birthdays.size());
        return birthdays;
    }

}
