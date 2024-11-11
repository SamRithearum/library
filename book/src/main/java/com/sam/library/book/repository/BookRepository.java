package com.sam.library.book.repository;

import com.sam.library.book.constant.BookStatus;
import com.sam.library.book.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //
    @Query(value = "SELECT b.* FROM book b WHERE b.student_id = :studentId", nativeQuery = true)
    List<Book> getStudentByStudentId(@Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET " +
            "status = :status, " +
            "student_id = :studentId, " +
            "reserved_at = CURRENT_TIMESTAMP " +
            "WHERE id = :id", nativeQuery = true)
    int reserveBook(@Param("id") Long id, @Param("studentId") Long studentId, @Param("status") BookStatus status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET " +
            "status = :status, " +
            "last_borrowed_at = CURRENT_TIMESTAMP " +
            "WHERE id = :id", nativeQuery = true)
    int changeBookStatusToBorrowed(@Param("id") Long id, @Param("status") BookStatus status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET " +
            "status = :status, " +
            "last_returned_at = CURRENT_TIMESTAMP, " +
            "student_id = NULL " +
            "WHERE id = :id", nativeQuery = true)
    int returnBook(@Param("id") Long id, @Param("status") BookStatus status);
}
