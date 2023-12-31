package com.karurisuro.bookshelf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BookRepository extends ListCrudRepository<Book, Long> {

  public List<Book> findByAuthor(String author);

  public List<Book> findByTitleContaining(String title);

  @Query("select b from Book b where b.title like %?1% and b.author = ?2")
  public List<Book> findByTitleAndAuthor(String title, String author);
}
