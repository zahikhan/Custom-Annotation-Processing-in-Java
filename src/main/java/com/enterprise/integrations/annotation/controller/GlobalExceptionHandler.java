package com.enterprise.integrations.annotation.controller;

import com.enterprise.integrations.annotation.entities.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.enterprise.integrations.annotation.utility.AnnotationProcessorFormatAdapter.deserialize;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * <h1>This class  particularly for the custom Annotations.</h1>
 *
 * <p>
 * The Structure we required this for is to include the parameters in the annotations.
 * <br/>
 * Example:
 * <br/>
 * <pre>
 *       @ Test(value1, value2 , .. , valueN)
 *
 *     </pre>
 * </p>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 3.0.4
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
