package com.cjufi.bookmanagement.service.impl;

import com.cjufi.bookmanagement.model.Book;
import com.cjufi.bookmanagement.repository.BookRepo;
import com.cjufi.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    @Override
    public Book saveBook(Book book) {
        book.setCreateTime(LocalDateTime.now());
        return bookRepo.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }
}
