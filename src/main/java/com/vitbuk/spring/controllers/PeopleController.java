package com.vitbuk.spring.controllers;

import com.vitbuk.spring.dao.BookDAO;
import com.vitbuk.spring.dao.PersonDAO;
import com.vitbuk.spring.models.Person;
import com.vitbuk.spring.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
       return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person, BindingResult bindingResult) {

        // в bindingResult складываются и простейшие ошибки с @Valid и сложные с personValidatora
        //personValidator.validate(person, bindingResult);

       // if(bindingResult.hasErrors())
       //     return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, BindingResult bindingResult, @PathVariable("id") int id) {

       // personValidator.validate(person, bindingResult);

       // if(bindingResult.hasErrors())
      //      return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }





}
