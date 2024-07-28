package com.fullstack.controller;

import com.fullstack.Repository.BookRepository;
import com.fullstack.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/book/available_Book")
    public List<Book> available_Book(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public void getBookById(@RequestBody Long bookId){
        bookRepository.findById(bookId);
    }
}
