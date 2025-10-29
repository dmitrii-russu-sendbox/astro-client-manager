package com.example.customers.controller.command;

import com.example.customers.entity.Person;
import org.springframework.stereotype.Controller;
import com.example.customers.service.PersonService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class PersonCommandController {

    private PersonService service;

    public PersonCommandController(PersonService service) {
        this.service = service;
    }

    // 🔹 Один метод: и создать, и обновить
    @PostMapping("/save")
    public String saveOrUpdateByName(@ModelAttribute Person person, Model model) {
        Person saved = service.saveOrUpdateByName(person);
        model.addAttribute("person", saved);
        model.addAttribute("message", "Клиент сохранён или обновлён");
        String encodedName = URLEncoder.encode(saved.getName(), StandardCharsets.UTF_8);
        return "redirect:/persons/search?name=" + encodedName;
    }

    @PostMapping("/delete")
    public String deleteConfirmed(@RequestParam String name, RedirectAttributes redirectAttributes) {
        Optional<Person> personOpt = service.findByExactName(name);
        if (personOpt.isPresent()) {
            service.delete(personOpt.get());
            redirectAttributes.addFlashAttribute("message", "Клиент '" + name + "' успешно удален!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Клиент с именем '" + name + "' не найден. Удаление невозможно.");
        }
        return "redirect:/persons";
    }


}
