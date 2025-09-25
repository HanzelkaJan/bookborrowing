package com.hanzelkajan.bookborrowing.repositories;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import com.hanzelkajan.bookborrowing.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
}
