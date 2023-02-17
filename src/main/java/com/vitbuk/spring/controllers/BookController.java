package com.vitbuk.spring.controllers;

import com.vitbuk.spring.dao.BookDAO;
import com.vitbuk.spring.models.Book;
import com.vitbuk.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult) {

        // в bindingResult складываются и простейшие ошибки с @Valid и сложные с personValidatora
        //personValidator.validate(person, bindingResult);

        // if(bindingResult.hasErrors())
        //     return "people/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
}
