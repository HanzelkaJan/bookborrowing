package com.hanzelkajan.bookborrowing.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="books_id_seq")
    private Long id;
    private String isbn;
    private String name;
    private String author;
    private Boolean reserved;
}
