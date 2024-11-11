package com.sam.library.book.service.impl;

import com.sam.library.book.constant.BookStatus;
import com.sam.library.book.entity.Book;
import com.sam.library.book.repository.BookRepository;
import com.sam.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBookByStudentId(Long studentId) {
        return bookRepository.getStudentByStudentId(studentId);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
    }

    @Override
    public String reserveBook(Long bookId, Long studentId) {
        int result = bookRepository.reserveBook(bookId, studentId, BookStatus.RESERVED);
        if (result == 0) throw new RuntimeException("Book not found.");
        return "Book reserved successfully!";
    }

    @Override
    public String changeBookStatusToBorrowed(Long bookId) {
        int result = bookRepository.changeBookStatusToBorrowed(bookId, BookStatus.BORROWED);
        if (result == 0) throw new RuntimeException("Book not found.");
        return "Book borrowed successfully!";
    }

    @Override
    public String returnBook(Long bookId) {
        int result = bookRepository.returnBook(bookId, BookStatus.AVAILABLE);
        if (result == 0) throw new RuntimeException("Book not found.");
        return "Book return successfully!";
    }
}
