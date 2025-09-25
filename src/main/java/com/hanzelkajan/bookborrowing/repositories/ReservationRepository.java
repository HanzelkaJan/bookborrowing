package com.hanzelkajan.bookborrowing.repositories;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long>, PagingAndSortingRepository<ReservationEntity, Long> {
}
