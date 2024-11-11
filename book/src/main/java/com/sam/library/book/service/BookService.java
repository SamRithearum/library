package com.sam.library.book.service;

import com.sam.library.book.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBookByStudentId(Long studentId);
    Book getBookById(Long id);
    String reserveBook(Long bookId, Long studentId);
    String changeBookStatusToBorrowed(Long bookId);
    String returnBook(Long bookId);
}
