package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search/{title}")
    public List<Book> searchBook(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> author (@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @GetMapping("/isbn/{isbn}")
    public List<Book> isbn(@PathVariable String isbn) {
        return bookService.getBooksByISBN(isbn);
    }

}
