package com.vitbuk.spring.controllers;

import com.vitbuk.spring.dao.BookDAO;
import com.vitbuk.spring.dao.PersonDAO;
import com.vitbuk.spring.models.Book;
import com.vitbuk.spring.models.Person;
import com.vitbuk.spring.services.BookService;
import com.vitbuk.spring.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {


    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public BookController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", (bookService.findAll()));
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        model.addAttribute("book", bookService.findOne(id));

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookDAO.getBookOwner(id));
        else
            model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.assignBook(person, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.returnBook(id);
        return "redirect:/books";
    }
}
