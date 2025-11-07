package com.example.customers.controller.query;

import com.example.customers.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.customers.service.PersonService;

import java.util.Optional;

@Controller
@RequestMapping({"", "/", "/customers"})
public class PersonViewController {

    private final PersonService service;

    public PersonViewController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String maiPage() {
        return "customers/layout/main";
    }


    @GetMapping("/searchForm")
    public String showSearchForm(
            @ModelAttribute("error") String error,
            @ModelAttribute("message") String message,
            Model model
    ) {
        model.addAttribute("error", error);
        model.addAttribute("message", message);
        return "customers/pages/form/main-page-search";
    }

    @GetMapping("/newOrEdit")
    public String showUpdateAndCreateForm(
            @RequestParam(required = false) String name, Model model
    ) {
        Person person = service.findByExactName(name).orElse(new Person());
        model.addAttribute("person", person);
        return "customers/pages/form/create-edit-form";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam String name, Model model) {
        Optional<Person> personOpt = service.findByExactName(name);
        if (personOpt.isPresent()) {
            model.addAttribute("person", personOpt.get());
            return "customers/pages/form/delete-confirm-form";
        } else {
            model.addAttribute("message", "Клиент с именем '" + name + "' не найден");
            return "customers/pages/form/main-page-search";
        }
    }

}
