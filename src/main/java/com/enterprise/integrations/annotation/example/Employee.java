package com.enterprise.integrations.annotation.example;

import com.enterprise.integrations.annotation.NotNull;
import lombok.Data;

/**
 * This class is for
 * <p>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 * @since 1.0
 */
@Data
public class Employee {
    @NotNull(message = "Name can't be null", errorCode = 23)
    String name;

    @NotNull(message = "FULL name can't be null")
    String fullname;
}

