package com.vitbuk.spring.dao;

import com.vitbuk.spring.models.Book;
import com.vitbuk.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_creation=? WHERE book_id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear_of_creation(), updatedBook.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void assignBook(Person person, Book book) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getId(), book.getId());
    }

    public void returnBook(Book book) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL, WHERE book_id=?", book.getId());
    }

}
