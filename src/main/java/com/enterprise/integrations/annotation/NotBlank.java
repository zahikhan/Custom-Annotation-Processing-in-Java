package com.enterprise.integrations.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

import com.enterprise.integrations.annotation.processor.NotBlankAnnotationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * The annotated element must not be {@code null} and must contain at least one non-whitespace
 * character. Accepts {@code CharSequence}.
 *
 * @author Zahid Khan
 * @version 3/10/2023
 * @see Character#isWhitespace(char)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER})
@Constraint(validatedBy = {NotBlankAnnotationProcessor.class})
public @interface NotBlank {
  String message() default "{taas.commons.validation.constraints.NotBlank.message}";

  @SuppressWarnings("unused")
  Class<?>[] groups() default {};

  @SuppressWarnings("unused")
  Class<? extends Payload>[] payload() default {};

  long errorCode() default 400;
}
