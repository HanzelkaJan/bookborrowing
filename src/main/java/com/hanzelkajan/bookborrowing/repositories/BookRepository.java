package com.hanzelkajan.bookborrowing.repositories;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long> {
}
