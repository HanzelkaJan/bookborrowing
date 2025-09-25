package com.hanzelkajan.bookborrowing.services.impl;

import com.hanzelkajan.bookborrowing.domain.entities.BookEntity;
import com.hanzelkajan.bookborrowing.repositories.BookRepository;
import com.hanzelkajan.bookborrowing.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }
    @Override
    public Page<BookEntity> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public BookEntity partialUpdate(Long id, BookEntity bookEntity) {
        bookEntity.setId(id);

        return bookRepository.findById(id).map(existingBook -> {
            Optional.ofNullable(bookEntity.getIsbn()).ifPresent(existingBook::setIsbn);
            Optional.ofNullable(bookEntity.getName()).ifPresent(existingBook::setName);
            Optional.ofNullable(bookEntity.getAuthor()).ifPresent(existingBook::setAuthor);
            Optional.ofNullable(bookEntity.getReserved()).ifPresent(existingBook::setReserved);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
