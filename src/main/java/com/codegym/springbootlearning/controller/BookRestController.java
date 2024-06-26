package com.codegym.springbootlearning.controller;

import com.codegym.springbootlearning.converter.DtoToEntity;
import com.codegym.springbootlearning.entity.Book;
import com.codegym.springbootlearning.payload.ResponseDto;
import com.codegym.springbootlearning.payload.request.EditBookRequest;
import com.codegym.springbootlearning.service.IAuthorService;
import com.codegym.springbootlearning.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/*
REST là gì ?
RESTful là gì ?
SOAP là gì ?
Sử dụng Postman để test REST API
Sử dụng Postman để test SOAP API
 */

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookRestController {
    private final DtoToEntity dtoToEntity;
    private final IBookService bookService;
    private final IAuthorService authorService;

    @GetMapping("/list")
    public ResponseEntity<Page<Book>> findAll(@RequestParam(defaultValue = "") String name,
                                              @PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(name, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable UUID id,
                                              @RequestBody EditBookRequest bookRequest) {
        Book book = bookService.findById(id);
        Book bookUpdate = dtoToEntity.convert(book, bookRequest);
        bookService.update(bookUpdate);
        ResponseDto responseDto = ResponseDto.builder()
                .message("Update success")
                .data(bookUpdate)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

}
