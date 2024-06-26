package com.codegym.springbootlearning.service.impl;

import com.codegym.springbootlearning.entity.Author;
import com.codegym.springbootlearning.entity.Book;
import com.codegym.springbootlearning.repository.IAuthorRepository;
import com.codegym.springbootlearning.service.IAuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorService implements IAuthorService {
    private final IAuthorRepository authorRepository;

    @Override
    public Page<Author> findAll(String name, Pageable pageable) {
        return authorRepository.findByNameContainsAndIsDeletedIsFalse(name, pageable);
    }

    @Override
    public Author findById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Author author) {

        authorRepository.save(author);
    }

    @Override
    public void deleteById(UUID id) {

        authorRepository.deleteById(id);
    }

    @Override
    public void update(Author author) {

        authorRepository.save(author);
    }
}
