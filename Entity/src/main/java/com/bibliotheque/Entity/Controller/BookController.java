package com.bibliotheque.Entity.Controller;

import com.bibliotheque.Entity.Entity.Book;
import com.bibliotheque.Entity.Entity.Dto.BookDTO;
import com.bibliotheque.Entity.Entity.Mapper.BookMapper;
import com.bibliotheque.Entity.Service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookMapper bookMapper;


    @RequestMapping("/")
    public List<BookDTO> listOfBooks() {
        List<Book> books = this.bookService.findAll();
        return bookMapper.toDto(books);
    }

    @GetMapping("/{id}")
    public Book bookId(@PathVariable int id) {
        Book book = this.bookService.findById(id);
        return book;
    }

    @PostMapping("/")
    public ResponseEntity<BookDTO> newBook(@RequestBody BookDTO bookDTO) {
        System.out.println("book => " + bookMapper.toEntity(bookDTO));
        bookService.save(bookMapper.toEntity(bookDTO));
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
