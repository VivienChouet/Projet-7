package com.bibliotheque.Entity.Controller;

import com.bibliotheque.Entity.Entity.Book;
import com.bibliotheque.Entity.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

@Autowired
BookService bookService;

    @RequestMapping("/")
    public List<Book> listOfBooks(){
        List<Book> books = this.bookService.findAll();
        return books;
    }

    @RequestMapping("/{id}")
    public Book bookId (@PathVariable int id){
        Book book  = this.bookService.findById(id);
        return book;
    }

    @PostMapping("/new")
    public void newBook (@RequestBody Book book){
        bookService.save(book);
    }


}
