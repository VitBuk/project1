package com.vitbuk.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Name of the book should not be empty")
    private String name;

    @NotEmpty
    private String author;

    @NotEmpty(message = "Year could not be empty")
    @Pattern(regexp = "\\d{4}", message = "year should have 4 digits")
    private int year_of_creation;

    public Book() {
    }

    public Book(int id, String name, String author, int year_of_creation) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year_of_creation = year_of_creation;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_creation() {
        return year_of_creation;
    }

    public void setYear_of_creation(int year_of_creation) {
        this.year_of_creation = year_of_creation;
    }
}
