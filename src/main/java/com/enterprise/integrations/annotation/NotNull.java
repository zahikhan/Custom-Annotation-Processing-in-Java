package com.enterprise.integrations.annotation;


import com.enterprise.integrations.annotation.processor.NotNullAnnotationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * <h1>This class is for creation of Annotation.</h1>
 * <h2>Use this @annotation only for Field validation and not for the Functional Signatures.</h2>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {NotNullAnnotationProcessor.class})
public @interface NotNull {
    String message() default "Field cannot be NULL.";

    @SuppressWarnings("unused")
    Class<?>[] groups() default {};

    @SuppressWarnings("unused")
    Class<? extends Payload>[] payload() default {};

    long errorCode() default 400;
}
