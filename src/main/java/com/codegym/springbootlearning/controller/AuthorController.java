package com.codegym.springbootlearning.controller;

import com.codegym.springbootlearning.entity.Author;
import com.codegym.springbootlearning.service.impl.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ModelAndView findAll(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("author/ListAuthor");
        modelAndView.addObject("authors", authorService.findAll("", pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("author/CreateAuthor");
        modelAndView.addObject("author", new Author());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Author author) {
        authorService.save(author);
        return new ModelAndView("redirect:/authors");
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable UUID id) {
        return new ModelAndView("author/AuthorDetail", "author", authorService.findById(id));
    }
}
