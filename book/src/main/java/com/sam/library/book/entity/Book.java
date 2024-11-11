package com.sam.library.book.entity;

import com.sam.library.book.constant.BookStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(length = 1000)
    private String description;

    private Long studentId;

    private BookStatus status;

    private LocalDate reservedAt;

    private LocalDate lastBorrowedAt;

    private LocalDate lastReturnedAt;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}

