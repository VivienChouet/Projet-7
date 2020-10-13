package com.bibliotheque.API.Service;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Repository.BookRepository;
import com.bibliotheque.API.Utility.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll() {
        logger.info("return list book");
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        logger.info("return book id =" + id);
        return  bookRepository.findById(id).get();
    }

    public void save(Book book) {
        logger.info("save new book = " + book.title);
        bookRepository.save(book);
    }

    public void update(Book book) {
        logger.info("update book id = " + book.id);
        bookRepository.save(book);
    }

    public void delete(Book book) {
        logger.info("delete book id = " + book.id);
        bookRepository.delete(book);
    }
}
