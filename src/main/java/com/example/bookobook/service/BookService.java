package com.example.bookobook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.bookobook.model.Book;
import com.example.bookobook.model.User;
import com.example.bookobook.repository.BookRepository;
import com.example.bookobook.repository.UserRepository;

@Service
public class BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findByUser(getCurrentUser());
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        book.setUser(getCurrentUser());
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
         findBookById(id).ifPresent(book -> bookRepository.delete(book));
    }

    public void toggle(Long id) {
        findBookById(id).ifPresent(book -> {book.setRead(!book.isRead()); bookRepository.save(book);});
    }

    public void updateNote(Long id, String note) {
        findBookById(id).ifPresent(book -> {book.setNote(note); bookRepository.save(book);});
    }

    public List<Book> search(String keyword) {
        return bookRepository.search(keyword);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }
}
