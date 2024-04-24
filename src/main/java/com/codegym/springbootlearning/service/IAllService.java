package com.codegym.springbootlearning.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAllService<E> {

    Page<E> findAll(String name,Pageable pageable);

    E findById(UUID id);

    void save(E e);

    void deleteById(UUID id);

    void update(E e);

}
