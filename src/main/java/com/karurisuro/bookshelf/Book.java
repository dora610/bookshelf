package com.karurisuro.bookshelf;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Title is mandatory")
  private String title;
  @NotBlank(message = "Author name is mandatory")
  private String author;
  @Positive(message = "Price cannot be 0 or negative")
  private Float price;
  @PastOrPresent(message = "Published date cannot be a future date")
  private LocalDate publishedOn;
}
