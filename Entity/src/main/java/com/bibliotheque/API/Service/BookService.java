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

    /**
     * findAll
     * @return List<Book>
     */
    public List<Book> findAll() {
        logger.info("return list book");
        return bookRepository.findAll();
    }

    /**
     * findById
     * @param id
     * @return Book
     */
    public Book findById(int id) {
        logger.info("return book id =" + id);
        return  bookRepository.findById(id).get();
    }

    /**
     * Save
     * @param book
     */
    public void save(Book book) {
        logger.info("save new book = " + book.title);
        bookRepository.save(book);
    }

    /**
     * Update
     * @param book
     */
    public void update(Book book) {
        bookRepository.save(book);
    }

    /**
     * findByTitle
     * @param name
     * @return Book
     */
    public Book findByTitle(String name) {
        logger.info("research book name = " + name);
       Book book = bookRepository.findByTitle(name);
        return book;
    }
}
