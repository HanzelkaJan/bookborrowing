package com.hanzelkajan.bookborrowing.services.impl;

import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import com.hanzelkajan.bookborrowing.repositories.ReservationRepository;
import com.hanzelkajan.bookborrowing.services.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationEntity save(ReservationEntity reservationEntity){
        return reservationRepository.save(reservationEntity);
    }

    @Override
    public Optional<ReservationEntity> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<ReservationEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public boolean isExists(Long id) {
        return false;
    }

    @Override
    public ReservationEntity partialUpdate(Long id, ReservationEntity reservationEntity) {
        reservationEntity.setId(id);

        return reservationRepository.findById(id).map(existingReservation ->{
            Optional.ofNullable(reservationEntity.getBookEntity()).ifPresent(existingReservation::setBookEntity);
            Optional.ofNullable(reservationEntity.getUserEntity()).ifPresent(existingReservation::setUserEntity);
            Optional.ofNullable(reservationEntity.getBorrowedFrom()).ifPresent(existingReservation::setBorrowedFrom);
            Optional.ofNullable(reservationEntity.getBorrowedTo()).ifPresent(existingReservation::setBorrowedTo);
            return reservationRepository.save(existingReservation);
        }).orElseThrow(() -> new RuntimeException("Reservation does not exist"));
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}