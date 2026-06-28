package com.example.bookobook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookobook.model.Book;
import com.example.bookobook.repository.BookRepository;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
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
}
