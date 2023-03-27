package com.enterprise.integrations.annotation.example;

import com.enterprise.integrations.annotation.NotBlank;
import lombok.Data;

/**
 * This class is for
 *
 * <p>Project Name: Annotation
 *
 * @author Zahid Khan @Time 3/10/2023
 * @since 1.0
 */
@Data
public class Employee {
  @NotBlank String name;

  @NotBlank(message = "Invalid fullname") String fullname;
}
