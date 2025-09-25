package com.hanzelkajan.bookborrowing.controllers;

import com.hanzelkajan.bookborrowing.domain.dto.ReservationDto;
import com.hanzelkajan.bookborrowing.domain.dto.ReservationDto;
import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import com.hanzelkajan.bookborrowing.mappers.Mapper;
import com.hanzelkajan.bookborrowing.services.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReservationController {
    private ReservationService reservationService;
    private Mapper<ReservationEntity, ReservationDto> reservationMapper;
    public ReservationController(ReservationService reservationService, Mapper<ReservationEntity, ReservationDto> reservationMapper){
        this.reservationMapper = reservationMapper;
        this.reservationService = reservationService;
    }
    @PostMapping(path = "/reservations")
    public ReservationDto save(@RequestBody ReservationDto reservation){
        ReservationEntity reservationEntity = reservationMapper.mapFrom(reservation);
        ReservationEntity savedReservationEntity =  reservationService.save(reservationEntity);
        return reservationMapper.mapTo(savedReservationEntity);
    }

    @GetMapping(path = "/reservations")
    public Page<ReservationDto> listReservations(Pageable pageable){
        Page<ReservationEntity> reservations = reservationService.findAll(pageable);
        return reservations.map(reservationMapper::mapTo);
    }

    @GetMapping(path = "/reservations/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable("id") Long id){
        Optional<ReservationEntity> foundReservation = reservationService.findOne(id);
        return foundReservation.map(reservationEntity -> {
            ReservationDto reservationDto = reservationMapper.mapTo(reservationEntity);
            return new ResponseEntity<>(reservationDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/reservations/{id}")
    public ResponseEntity<ReservationDto> fullUpdateReservation(
            @PathVariable("id") Long id,
            @RequestBody ReservationDto reservationDto){
        if(!reservationService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationDto.setId(id);
        ReservationEntity reservationEntity = reservationMapper.mapFrom(reservationDto);
        ReservationEntity savedReservationEntity = reservationService.save(reservationEntity);
        return new ResponseEntity<>(
                reservationMapper.mapTo(savedReservationEntity),
                HttpStatus.OK
        );
    }
    @PatchMapping(path = "/reservations/{id}")
    public ResponseEntity<ReservationDto> partialUpdateReservation(
            @PathVariable("id") Long id,
            @RequestBody ReservationDto reservationDto
    ){
        if(!reservationService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ReservationEntity reservationEntity = reservationMapper.mapFrom(reservationDto);
        ReservationEntity updatedReservationEntity = reservationService.partialUpdate(id, reservationEntity);
        return new ResponseEntity<>(reservationMapper.mapTo(updatedReservationEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable("id") Long id){
        reservationService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
