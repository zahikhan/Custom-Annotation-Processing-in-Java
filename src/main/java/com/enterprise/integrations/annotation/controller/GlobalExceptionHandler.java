package com.enterprise.integrations.annotation.controller;

import com.enterprise.integrations.annotation.entities.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.enterprise.integrations.annotation.utility.AnnotationProcessorFormatAdapter.deserialize;

/**
 * <h1>This class  particularly for the custom Annotations.</h1>
 *
 * <p>
 *     The Structure we required this for is to include the parameters in the annotations.
 *     <br/>
 *     Example:
 *     <br/>
 *     <pre>
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @SuppressWarnings("unused")
    public List<ErrorStructure> handleMethodArgumentException(BindException exception) {
        return exception.getBindingResult().getFieldErrors().
                stream().map(error -> deserialize(error.getDefaultMessage()))
                .toList();
    }
}
