package com.karurisuro.bookshelf.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(ContentNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleContentNotFoundException(ContentNotFoundException ex, WebRequest request) {
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    log.error(errorMessage.toString());
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidUserInputException.class)
  public ResponseEntity<ErrorMessage> handleInvalidUserInputException(InvalidUserInputException ex, WebRequest request) {
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    log.error(errorMessage.toString());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
}
