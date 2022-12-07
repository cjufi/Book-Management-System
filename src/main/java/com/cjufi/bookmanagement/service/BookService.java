package com.cjufi.bookmanagement.service;

import com.cjufi.bookmanagement.model.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(Long id);

    List<Book> findAllBooks();
}
