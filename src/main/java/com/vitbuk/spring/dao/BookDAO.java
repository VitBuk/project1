package com.vitbuk.spring.dao;

import com.vitbuk.spring.models.Book;
import com.vitbuk.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_creation ) VALUES(?,?,?)",
                book.getName(), book.getAuthor(), book.getYear_of_creation());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_creation=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear_of_creation(), updatedBook.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assignBook(Person person,  int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person.getId(), id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON " +
                        "Book.person_id = Person.id WHERE Book.id = ? ",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }
    public void returnBook(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }


}
