package com.cjufi.bookmanagement.controller;

import com.cjufi.bookmanagement.model.Book;
import com.cjufi.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book),HttpStatus.CREATED);
    }
}