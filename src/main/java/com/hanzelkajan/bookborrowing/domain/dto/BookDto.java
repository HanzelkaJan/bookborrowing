package com.hanzelkajan.bookborrowing.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String isbn;
    private String name;
    private String author;
    private Boolean reserved;
}
