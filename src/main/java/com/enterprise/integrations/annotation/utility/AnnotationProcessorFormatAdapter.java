package com.enterprise.integrations.annotation.utility;

import com.enterprise.integrations.annotation.entities.ErrorStructure;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * <h1>This class is for AnnotationProcessorFormat.</h1>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/14/2023
 */
public final class AnnotationProcessorFormatAdapter {


    //NON INSTANTIABLE UTILITY CLASS.
    private AnnotationProcessorFormatAdapter() {
        throw new AssertionError("No instance for you!");
    }

    @SneakyThrows
    public static String getSerializedObject(final String errorMessage, final long errorCode) {
        ErrorStructure errorStructure = new ErrorStructure(errorMessage, errorCode);
        return new ObjectMapper().writeValueAsString(errorStructure);
    }

    @SneakyThrows
    public static ErrorStructure deserialize(String serializedValue) {
        return new ObjectMapper().readValue(serializedValue, ErrorStructure.class);
    }
}
