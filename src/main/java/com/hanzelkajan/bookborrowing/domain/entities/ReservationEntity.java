package com.hanzelkajan.bookborrowing.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reservations_id_seq")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="book_id")
    private BookEntity bookEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;
    private Date borrowedFrom;
    private Date borrowedTo;
}
