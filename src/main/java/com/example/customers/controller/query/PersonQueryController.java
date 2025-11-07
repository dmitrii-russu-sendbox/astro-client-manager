package com.example.customers.controller.query;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.ui.Model;
import com.example.customers.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.customers.service.PersonService;

@Controller
@RequestMapping({"", "/", "/customers"})
public class PersonQueryController {

    private final PersonService service;

    public PersonQueryController(PersonService service) {
        this.service = service;
    }

    // 🔹 Поиск по имени или вывод всех клиентов
    @GetMapping("/search")
    public String searchPersons(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        // Если имя указано и есть точное совпадение — сразу показать детали
        if (name != null && !name.trim().isEmpty()) {
            Optional<Person> exact = service.findByExactName(name.trim());
            if (exact.isPresent()) {
                model.addAttribute("person", exact.get());
                return "customers/pages/result/person-details";
            }
        }

        // Иначе — показать список (всех или по части имени)
        List<Person> persons = service.searchByNameOrAll(name);
        boolean all = (name == null || name.trim().isEmpty());

        model.addAttribute("persons", persons);
        model.addAttribute("searchTerm", all ? "(все клиенты)" : name);
        model.addAttribute("searchType", all ? "all" : "name");

        return "customers/pages/result/person-search-results";
    }

    // 🔹 Поиск по месяцу рождения
    @GetMapping("/searchByMonth")
    public String searchByMonth(@RequestParam int month, Model model) {
        List<Person> persons = service.findByBirthMonthOrderedByDay(month);

        // Название месяца по-русски (например: "ноябрь")
        String monthName = Month.of(month)
                .getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));

        model.addAttribute("persons", persons);
        model.addAttribute("searchTerm", monthName);
        model.addAttribute("searchType", "month");

        return "customers/pages/result/person-search-results";
    }

}