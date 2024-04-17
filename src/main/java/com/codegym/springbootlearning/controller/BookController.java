package com.codegym.springbootlearning.controller;

import com.codegym.springbootlearning.entity.Book;
import com.codegym.springbootlearning.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("ListBook");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("CreateBook", "book", new Book());
    }

    @PostMapping("/create")
    public String createBook(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public ModelAndView showBook(@PathVariable UUID id) {
        ModelAndView modelAndView = new ModelAndView("InfoBook");
        modelAndView.addObject("book", bookService.findById(id));
        return modelAndView;
    }


}
