package com.hanzelkajan.bookborrowing.domain.dto;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import com.hanzelkajan.bookborrowing.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private BookEntity bookEntity;
    private UserEntity userEntity;
    private Date borrowedFrom;
    private Date borrowedTo;
}
