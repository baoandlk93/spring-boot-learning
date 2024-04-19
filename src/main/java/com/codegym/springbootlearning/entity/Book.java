package com.codegym.springbootlearning.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/*
- Entity là 1 class trong Java, tương ứng với 1 table trong DB
- Entity bắt buộc phải là POJO (Plain Old Java Object)
- Entity phải có 1 primary key (khoá chi phân)
- Entity Phải có 1 constructor khóng tham số
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne(targetEntity = Author.class)
    @JsonManagedReference("book-author")
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private Double price;
    private Integer quantity;
    private String category;
    private String publisher;
    private String year;
    private String language;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = Boolean.FALSE;

}
