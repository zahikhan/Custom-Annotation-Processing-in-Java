package com.enterprise.integrations.annotation.processor;

import com.enterprise.integrations.annotation.NotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import static com.enterprise.integrations.annotation.utility.AnnotationProcessorFormatAdapter.getSerializedObject;

/**
 * This class is for Annotation processing.
 * <p>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 1.0
 */

@Getter
public class NotNullAnnotationProcessor implements ConstraintValidator<NotNull, String> {
    private String message;
    private long errorCode;


    @Override
    public void initialize(NotNull annotation) {
        message = annotation.message();
        errorCode = annotation.errorCode();
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = !Strings.isBlank(value);

        if (isValid) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        var serializedJSON = getSerializedObject(getMessage(), getErrorCode());
        context.buildConstraintViolationWithTemplate(serializedJSON).addConstraintViolation();
        return false;
    }
}
