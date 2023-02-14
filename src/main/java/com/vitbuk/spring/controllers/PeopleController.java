package com.vitbuk.spring.controllers;

import com.vitbuk.spring.dao.PersonDAO;
import com.vitbuk.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person perosn) {
       return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person, BindingResult bindingResult) {

        // в bindingResult складываются и простейшие ошибки с @Valid и сложные с personValidatora
        //personValidator.validate(person, bindingResult);

       // if(bindingResult.hasErrors())
       //     return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
}
