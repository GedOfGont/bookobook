package com.example.bookobook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookobook.model.Book;
import com.example.bookobook.model.User;

public interface BookRepository extends JpaRepository<Book, Long>{
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> search(@Param("keyword") String keyword);

    List<Book> findByUser(User user);

}
