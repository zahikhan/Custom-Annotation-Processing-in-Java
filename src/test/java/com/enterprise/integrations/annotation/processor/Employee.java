package com.enterprise.integrations.annotation.processor;

import com.enterprise.integrations.annotation.NotNull;

/**
 * This class is for
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 1.0
 */
record Employee(
		@NotNull(message = "Name can't be null", errorCode = 23)
		String name,
		
		@NotNull(message = "FULL name can't be null")
		String address
) {
}

