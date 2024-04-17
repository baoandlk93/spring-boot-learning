package com.codegym.springbootlearning.repository;

import com.codegym.springbootlearning.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBookRepository extends JpaRepository<Book, UUID> {
}
