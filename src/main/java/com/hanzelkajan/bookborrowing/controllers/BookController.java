package com.hanzelkajan.bookborrowing.controllers;

import com.hanzelkajan.bookborrowing.domain.dto.BookDto;
import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import com.hanzelkajan.bookborrowing.mappers.Mapper;
import com.hanzelkajan.bookborrowing.services.BookService;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log
public class BookController {
    private BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;
    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper){
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }
    @PostMapping(path = "/books")
    public BookDto save(@RequestBody BookDto book){
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity =  bookService.save(bookEntity);
        return bookMapper.mapTo(savedBookEntity);
    }
    @GetMapping(path = "/books")
    public Page<BookDto> listBooks(Pageable pageable){
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @GetMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id){
        Optional<BookEntity> foundBook = bookService.findOne(id);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> fullUpdateBook(
            @PathVariable("id") Long id,
            @RequestBody BookDto bookDto){
        if(!bookService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookDto.setId(id);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.save(bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(savedBookEntity),
                HttpStatus.OK
        );
    }
    @PatchMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> partialUpdateBook(
            @PathVariable("id") Long id,
            @RequestBody BookDto bookDto
    ){
        if(!bookService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBookEntity = bookService.partialUpdate(id, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id){
        bookService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
