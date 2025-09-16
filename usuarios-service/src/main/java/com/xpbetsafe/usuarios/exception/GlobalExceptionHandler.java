package com.xpbetsafe.usuarios.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  record ApiError(Instant timestamp, int status, String error, String message, String path) {}

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    String msg = ex.getBindingResult().getFieldErrors().stream()
      .map(f -> f.getField()+": "+f.getDefaultMessage())
      .collect(Collectors.joining("; "));
    return ResponseEntity.badRequest().body(new ApiError(Instant.now(), 400, "ValidationError", msg, req.getRequestURI()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiError> handleIllegal(IllegalArgumentException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(new ApiError(Instant.now(), 400, "BadRequest", ex.getMessage(), req.getRequestURI()));
  }
}
