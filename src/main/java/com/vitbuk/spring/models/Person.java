package com.vitbuk.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "year of birth should not be empty")
    @Pattern(regexp = "\\d{4}", message = "year should have 4 digits")
    private int year_of_birth;

    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, int year_of_birth, List<Book> books) {
        this.id = id;
        this.name = name;
        this.year_of_birth = year_of_birth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
