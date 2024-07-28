package com.fullstack.controller;

import com.fullstack.Repository.BookRepository;
import com.fullstack.Repository.UserRepository;
import com.fullstack.model.Book;
import com.fullstack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminBookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/book/register_Book")
    public void register_Book(@RequestHeader("Authorization") String token,  @RequestBody Book book){
        bookRepository.save(book);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestHeader("Authorization") String token){
        return userRepository.findAll();
    }

    @GetMapping("/book/all_Books")
    public List<Book> all_Books(@RequestHeader("Authorization") String token){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public void getBookById(@RequestHeader("Authorization") String token, @RequestBody Long bookId){
        bookRepository.findById(bookId);
    }

    @PutMapping("/book/{id}")
    public void update_Book(@RequestHeader("Authorization") String token , @PathVariable Long bookId, Book book){
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete_Book(@PathVariable Long bookId){
        bookRepository.deleteById(bookId);
    }
}
