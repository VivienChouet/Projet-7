package com.bibliotheque.Entity.Service;

import com.bibliotheque.Entity.Entity.Book;
import com.bibliotheque.Entity.Repository.BookRepository;
import com.bibliotheque.Entity.Utility.LoggingController;
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
}
