package com.enterprise.integrations.annotation.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.enterprise.integrations.annotation.entities.ErrorStructure;
import java.util.*;
import java.util.function.Function;import java.util.stream.Collectors;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 *
 * <ul>
 *   <li/>This class is designed to support custom annotations that include parameters.
 *   <li/>However, in some cases you may want to include parameters in your custom annotations. This
 *       is where this class comes in.
 *   <li/>For example, you can define a custom annotation using the following syntax:
 * </ul>
 *
 * <pre>
 * {@literal @}Test(value1, value2, ..., valueN)
 * </pre>
 *
 * @author Zahid Khan
 * @version 3/10/2023
 */
@RestControllerAdvice
@RestController(value = "customExceptionHandler")
@SuppressWarnings("unused")
class GlobalExceptionHandler {
  @ExceptionHandler
  ResponseEntity<Map<String, Object>> handleMethodArgumentException(
      MethodArgumentNotValidException exception) {
    Map<String, Object> body = new LinkedHashMap<>(6, 1);
    body.put("timestamp", new Date());
    body.put("status", BAD_REQUEST.value());
    body.put("error", BAD_REQUEST.getReasonPhrase());

     List<Set<ErrorStructure>> errorStructures =
        exception.getBindingResult().getFieldErrors().stream()
            .map(
                error ->
                        (Set<ErrorStructure>)error
                        .unwrap(HibernateConstraintViolation.class)
                        .getDynamicPayload(Object.class))
            .toList();
    final List<ErrorStructure> errorStructures1 = errorStructures.stream().flatMap(Set::stream).toList();
    
    body.put("messages", errorStructures1);
    return ResponseEntity.status(BAD_REQUEST).contentType(APPLICATION_JSON).body(body);
  }
}
