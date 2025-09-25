package com.hanzelkajan.bookborrowing.services;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity bookEntity);
    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(Long id);

    boolean isExists(Long id);

    BookEntity partialUpdate(Long id, BookEntity bookEntity);

    void delete(Long id);
}
