package com.example.bookobook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookobook.model.Book;
import com.example.bookobook.service.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @PostMapping("/add")
    public String addBook(Book book, Model model) {
        if (book.getTitle() != null && !book.getTitle().isBlank() && !bookService.getAll().contains(book)) {
            bookService.addBook(book);
        }
        model.addAttribute("books", bookService.getAll());
        return "fragments/book-list :: bookList";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id, Model model) {
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.getAll());
        return "fragments/book-list :: bookList";
    }

    @PostMapping("/toggle")
    public String toggle(@RequestParam Long id, Model model) {
        bookService.toggle(id);
        model.addAttribute("books", bookService.getAll());
        return "fragments/book-list :: bookList";
    }

    @GetMapping("/books/fragment")
    public String getBooksFragment(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "fragments/book-list :: bookList";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        bookService.findBookById(id).ifPresent(book -> model.addAttribute("book", book));
        return "book-detail";
    }

    @PostMapping("/books/{id}/note")
    public String updateNote(@PathVariable Long id, @RequestParam String note) {
        bookService.updateNote(id, note);
        return "redirect:/books/" + id;
    }

    @GetMapping("/books/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("books", bookService.search(keyword));
        return "fragments/book-list :: bookList";
    }
    

}
