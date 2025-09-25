package com.hanzelkajan.bookborrowing.services;

import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReservationService {
    ReservationEntity save(ReservationEntity reservationEntity);

    Optional<ReservationEntity> findOne(Long id);

    Page<ReservationEntity> findAll(Pageable pageable);

    boolean isExists(Long id);

    ReservationEntity partialUpdate(Long id, ReservationEntity reservationEntity);

    void delete(Long id);
}
