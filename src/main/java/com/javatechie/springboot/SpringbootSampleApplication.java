package com.javatechie.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/books")
public class SpringbootSampleApplication {


    public List<Book> books = new ArrayList<>();

    @PostConstruct
    public void initBooks() {
        books.add(Book.builder().id(101).name("Java").author("James").build());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSampleApplication.class, args);
    }

}
