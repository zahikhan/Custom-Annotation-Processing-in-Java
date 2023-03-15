package com.enterprise.integrations.annotation.controller;

import com.enterprise.integrations.annotation.entities.ErrorStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.enterprise.integrations.annotation.utility.AnnotationProcessorFormatAdapter.deserialize;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * <ul>
 * <li/>This class is designed to support custom annotations that include parameters.
 * <li/>
 * Custom annotations are a powerful feature in Java that allow you to define your own annotations with specific behavior.
 * <li/>
 * However, in some cases you may want to include parameters in your custom annotations. This is where this class comes in.
 * <li/>
 * For example, you can define a custom annotation using the following syntax:
 * </ul>
 *
 *
 *
 * <pre>
 * {@literal @}Test(value1, value2, ..., valueN)
 * </pre>
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 */
@RestControllerAdvice
@SuppressWarnings("unused")
class GlobalExceptionHandler {
	@ExceptionHandler
	@SuppressWarnings("unused")
	ResponseEntity<Map<String, Object>> handleMethodArgumentException(BindException exception) {
		Map<String, Object> body = new LinkedHashMap<>(5, 1);
		body.put("timestamp", new Date());
		body.put("status", BAD_REQUEST.value());
		body.put("error", BAD_REQUEST.getReasonPhrase());
		
		final List<ErrorStructure> errorStructures = exception.getBindingResult().getFieldErrors().
				stream().map(error -> deserialize(error.getDefaultMessage()))
				.toList();
		body.put("messages", errorStructures);
		return ResponseEntity.status(BAD_REQUEST)
				.contentType(APPLICATION_JSON).body(body);
	}
}
