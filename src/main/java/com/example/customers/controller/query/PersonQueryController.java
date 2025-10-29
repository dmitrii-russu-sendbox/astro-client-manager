package com.example.customers.controller.query;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import com.example.customers.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.customers.service.PersonService;

@Controller
@RequestMapping({"", "/", "/persons"})
public class PersonQueryController {

    private final PersonService service;

    public PersonQueryController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public String searchPersons(
            Model model,
            @RequestParam(value = "name", required = false) String name
    ) {

        Optional<Person> exact = service.findByExactName(name);
        if (exact.isPresent()) {
            model.addAttribute("person", exact.get());
            return "person/result/person-details";
        }

        List<Person> persons = service.searchByNameOrAll(name);
        model.addAttribute("persons", persons);
        model.addAttribute("searchTerm", (
                name == null || name.trim().isEmpty()) ? "(все клиенты)" : name
        );

        return "person/result/person-search-results";
    }

}
