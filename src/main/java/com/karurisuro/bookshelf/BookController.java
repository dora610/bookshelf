package com.karurisuro.bookshelf;

import com.karurisuro.bookshelf.exception.InvalidUserInputException;
import com.karurisuro.bookshelf.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public List<Book> getAllBoooks(){
    return bookService.findAllBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> findBookById(@PathVariable(value = "id") Long id) throws NotFoundException {
    return ResponseEntity.of(bookService.findById(id));
  }

  @GetMapping("/find")
  public List<Book> findBooks(@RequestParam(value = "title", required = false, defaultValue = "")String title,
                              @RequestParam(value = "author", required = false, defaultValue = "")String author) throws InvalidUserInputException, NotFoundException {
    return bookService.findBooks(title, author);
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Book addBook(@RequestBody @Valid Book book){
    return bookService.addBook(book);
  }

  @PutMapping("/{id}")
  public Book updateBook(@PathVariable(value = "id")Long id, @RequestBody @Valid Book book) throws NotFoundException {
    return bookService.updateBook(id, book);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable(value = "id")Long id){
    bookService.deleteBook(id);
  }

}
