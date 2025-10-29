package com.example.customers.controller.query;

import com.example.customers.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.customers.service.PersonService;

import java.util.Optional;

@Controller
@RequestMapping({"", "/", "/persons"})
public class PersonViewController {

    private final PersonService service;

    public PersonViewController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String showSearchForm(
            @ModelAttribute("error") String error,
            @ModelAttribute("message") String message,
            Model model
    ) {
        model.addAttribute("error", error);
        model.addAttribute("message", message);
        return "person/form/main-page-search"; // Main page
    }

    @GetMapping("/newOrEdit")
    public String showUpdateAndCreateForm(
            @RequestParam(required = false) String name, Model model
    ) {
        Person person = service.findByExactName(name).orElse(new Person());
        model.addAttribute("person", person);
        return "person/form/create-edit-form";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam String name, Model model) {
        Optional<Person> personOpt = service.findByExactName(name);
        if (personOpt.isPresent()) {
            model.addAttribute("person", personOpt.get());
            return "person/form/delete-confirm-form";
        } else {
            model.addAttribute("message", "Клиент с именем '" + name + "' не найден");
            return "person/form/main-page-search"; // или отдельная страница ошибки
        }
    }

}
