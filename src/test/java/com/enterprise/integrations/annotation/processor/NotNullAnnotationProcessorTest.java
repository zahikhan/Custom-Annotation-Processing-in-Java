package com.enterprise.integrations.annotation.processor;

import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * <h1>This class is for NotNullAnnotationProcessorTest.</h1>
 * <p>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 1.0
 */
class NotNullAnnotationProcessorTest {
    private NotNullAnnotationProcessor customAnnotationProcessor;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setup() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        customAnnotationProcessor = new NotNullAnnotationProcessor();

        when(context.buildConstraintViolationWithTemplate(null)).thenReturn(null);
    }

    @Test
    void testIsValidWithValidValue() {
        assertTrue(customAnnotationProcessor.isValid("test", context));
    }

    @Test
    void testIsValidWithNullValue() {
        assertFalse(customAnnotationProcessor.isValid(null, context));
    }

    @Test
    void testIsValidWithBlankValue() {
        assertFalse(customAnnotationProcessor.isValid(Strings.EMPTY, context));
    }
}