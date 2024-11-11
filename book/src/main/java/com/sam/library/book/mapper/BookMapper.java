package com.sam.library.book.mapper;

import com.sam.library.book.dto.BookDTO;
import com.sam.library.book.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDTO toBook(Book b) {
        BookDTO book = new BookDTO();
        book.setId(b.getId());
        book.setTitle(b.getTitle());
        book.setCategory(b.getCategory());
        book.setDescription(b.getDescription());
        book.setStudentId(b.getStudentId());
        book.setReservedAt(b.getReservedAt());
        book.setLastBorrowedAt(b.getLastBorrowedAt());
        book.setLastReturnedAt(b.getLastReturnedAt());
        book.setCreatedAt(b.getCreatedAt());
        book.setUpdatedAt(b.getUpdatedAt());

        return book;
    }

    public List<BookDTO> toBookList(List<Book> b) {
        return b.stream()
                .map(this::toBook)
                .collect(Collectors.toList());
    }
}
