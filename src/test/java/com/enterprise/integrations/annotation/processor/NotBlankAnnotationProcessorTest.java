package com.enterprise.integrations.annotation.processor;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * <h1>This class is for NotNullAnnotationProcessorTest.</h1>
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class NotBlankAnnotationProcessorTest {
	private NotNullAnnotationProcessor notNullAnnotationProcessor;
	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
	@Mock
	private NotBlank notBlank;
	@Mock
	private ConstraintValidatorContext constraintValidatorContext;
	
	private static Employee getEmployee(final String name, final String address) {
		return new Employee(name, address);
	}
	
	@BeforeEach
	void setUp() {
		lenient().when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
		
		when(notBlank.message()).thenReturn("Error");
		when(notBlank.errorCode()).thenReturn(400L);
		
		notNullAnnotationProcessor = new NotNullAnnotationProcessor();
		notNullAnnotationProcessor.initialize(notBlank);
	}
	
	@Test
	void testAnnotation() {
		Employee employee = getEmployee("Zahid Khan", "XYZ USA");
		
		assertTrue(notNullAnnotationProcessor.isValid(employee.name(), constraintValidatorContext));
		assertTrue(notNullAnnotationProcessor.isValid(employee.address(), constraintValidatorContext));
	}
	
	
	@Test
	void givenAnAnnotation_WhenEmpty_ThenShouldReturnNotValid() {
		Employee employee = getEmployee("", "XYZ USA");
		
		assertFalse(notNullAnnotationProcessor.isValid(employee.name(), constraintValidatorContext));
		assertTrue(notNullAnnotationProcessor.isValid(employee.address(), constraintValidatorContext));
	}
	
	@Test
	void givenAnAnnotation_WhenNullPassed_ThenShouldReturnNotValid() {
		Employee employee = getEmployee(null, null);
		
		assertFalse(notNullAnnotationProcessor.isValid(employee.name(), constraintValidatorContext));
		assertFalse(notNullAnnotationProcessor.isValid(employee.address(), constraintValidatorContext));
		
	}
}