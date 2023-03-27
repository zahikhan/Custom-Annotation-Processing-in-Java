package com.enterprise.integrations.annotation.utility;

import com.enterprise.integrations.annotation.entities.ErrorStructure;import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 *
 *
 * <h2>This class is for AnnotationProcessorToExceptionHandlerAdapter.</h2>
 *
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @version 3/14/2023
 */
public final class AnnotationProcessorToExceptionHandlerAdapter {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  // NON INSTANTIABLE UTILITY CLASS.
  private AnnotationProcessorToExceptionHandlerAdapter() {
    throw new AssertionError("No instance for you!");
  }

  /**
   * We are marshalling here, as there seems no other way we can send values to the {@link }
d
   */
  @SneakyThrows
  public static String getSerializedObject(final String errorMessage, final long errorCode) {
    ErrorStructure errorStructure = new ErrorStructure(errorMessage, errorCode);
    return OBJECT_MAPPER.writeValueAsString(errorStructure);
  }

  @SneakyThrows
  public static ErrorStructure deserialize(final String serializedValue) {
    return OBJECT_MAPPER.readValue(serializedValue, ErrorStructure.class);
  }
}
