package com.enterprise.integrations.annotation.processor;

import static com.enterprise.integrations.annotation.utility.AnnotationProcessorToExceptionHandlerAdapter.getSerializedObject;

import com.enterprise.integrations.annotation.NotBlank;
import com.enterprise.integrations.annotation.entities.ErrorStructure;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is for ConstraintValidator.
 *
 * @author Zahid Khan
 * @version 3/10/2023
 * @see ConstraintValidator#isValid(Object, ConstraintValidatorContext)
 */
@Getter
public class NotBlankAnnotationProcessor implements ConstraintValidator<NotBlank, String> {
  private String message;
  private long errorCode;

  @Override
  public void initialize(NotBlank annotation) {
    message = annotation.message();
    errorCode = annotation.errorCode();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid = !Strings.isBlank(value);

    if (isValid) {
      return true;
    }
    Set<ErrorStructure> errors = new HashSet<>();
    if ((context instanceof HibernateConstraintValidatorContext)) {
      errors.add(new ErrorStructure(getMessage(), getErrorCode()));

      errors.add(new ErrorStructure("Hellow1", 32L));
      context.unwrap(HibernateConstraintValidatorContext.class).withDynamicPayload(errors);
    }
    return false;
  }
}
