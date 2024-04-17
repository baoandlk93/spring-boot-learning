package com.codegym.springbootlearning.service;

import com.codegym.springbootlearning.entity.Book;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    List<Book> findAll();

    Book findById(UUID id);

    void save(Book book);

    void deleteById(UUID id);

    void update(Book book);

}
