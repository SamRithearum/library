package com.sam.library.book.dto;

import com.sam.library.book.constant.BookStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String category;
    private String description;
    private Long studentId;
    private BookStatus status;
    private LocalDate reservedAt;
    private LocalDate lastBorrowedAt;
    private LocalDate lastReturnedAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
