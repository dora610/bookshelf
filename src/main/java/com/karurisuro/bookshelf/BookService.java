package com.karurisuro.bookshelf;

import com.karurisuro.bookshelf.exception.InvalidUserInputException;
import com.karurisuro.bookshelf.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  private final Logger log = LoggerFactory.getLogger(BookService.class);

  public List<Book> findAllBooks(){
    return bookRepository.findAll();
  }

  public Optional<Book> findById(Long id) throws NotFoundException {
//    return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("No book found for the given id!"));
    return bookRepository.findById(id);
  }

  public List<Book> findBooks(String title, String author) throws InvalidUserInputException, NotFoundException {
    List<Book> books = new ArrayList<>();
    if(StringUtils.isBlank(title) && StringUtils.isBlank(author)){
      throw new InvalidUserInputException("Please provide valid input!");
    } else if (StringUtils.isBlank(title)) {
      books = bookRepository.findByAuthor(author);
    } else if (StringUtils.isBlank(author)) {
      books = bookRepository.findByTitleContaining(title);
    } else {
      books = bookRepository.findByTitleAndAuthor(title, author);
    }
    if(books.isEmpty()){
      throw new NotFoundException("No book found with the given attributes");
    }
    return books;
  }

  public Book addBook(Book book){
    // TODO: add validation for all fields of entity object
    return bookRepository.save(book);
  }

  public Book updateBook(Long id, Book book) throws NotFoundException {
    Book fetchedBook = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("No book found for the given id!"));
    fetchedBook.setTitle(book.getTitle());
    fetchedBook.setAuthor(book.getAuthor());
    fetchedBook.setPrice(book.getPrice());
    fetchedBook.setPublishedOn(book.getPublishedOn());
    return bookRepository.save(fetchedBook);
  }

  public void deleteBook(Long id){
    bookRepository.deleteById(id);
  }
}
