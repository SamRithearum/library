package com.sam.library.book.controller;

import com.sam.library.book.dto.BookDTO;
import com.sam.library.book.dto.ChangeBookStatusDTO;
import com.sam.library.book.dto.ReserveBookDTO;
import com.sam.library.book.entity.Book;
import com.sam.library.book.mapper.BookMapper;
import com.sam.library.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
@Tag(name = "Book", description = "Book services APIs")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> res = bookMapper.toBookList(books);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDTO res = bookMapper.toBook(book);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookDTO>> getBookByStudentId(@RequestParam(value="studentId") Long studentId) {
        List<Book> books = bookService.getBookByStudentId(studentId);
        List<BookDTO> res = bookMapper.toBookList(books);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveBook(@RequestBody ReserveBookDTO request){
        String res = bookService.reserveBook(request.getBookId(), request.getStudentId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/changeBookStatusToBorrowed")
    public ResponseEntity<?> changeBookStatusToBorrowed(@RequestBody ChangeBookStatusDTO request){
        String res = bookService.changeBookStatusToBorrowed(request.getBookId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody ChangeBookStatusDTO request){
        String res = bookService.returnBook(request.getBookId());
        return ResponseEntity.ok(res);
    }
}
