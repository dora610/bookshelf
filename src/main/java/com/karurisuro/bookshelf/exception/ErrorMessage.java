package com.karurisuro.bookshelf.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage(HttpStatus status, LocalDateTime timestamp, String message, String description) {
}
