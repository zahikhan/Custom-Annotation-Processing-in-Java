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
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	//NON INSTANTIABLE UTILITY CLASS.
	private AnnotationProcessorFormatAdapter() {
		throw new AssertionError("No instance for you!");
	}
	
	@SneakyThrows
	public static String getSerializedObject(final String errorMessage, final long errorCode) {
		ErrorStructure errorStructure = new ErrorStructure(errorMessage, errorCode);
		return objectMapper.writeValueAsString(errorStructure);
	}
	
	@SneakyThrows
	public static ErrorStructure deserialize(final String serializedValue) {
		return objectMapper.readValue(serializedValue, ErrorStructure.class);
	}
}
