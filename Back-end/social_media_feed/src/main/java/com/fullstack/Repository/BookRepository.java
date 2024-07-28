package com.fullstack.Repository;

import com.fullstack.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBook(String name);
}
