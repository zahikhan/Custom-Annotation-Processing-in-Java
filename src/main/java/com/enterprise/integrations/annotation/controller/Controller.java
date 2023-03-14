package com.enterprise.integrations.annotation.controller;

import com.enterprise.integrations.annotation.example.Employee;
import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>This class is for Controller.</h1>
 * <p>
 * Project Name: Annotation
 *
 * @author Zahid Khan
 * @Time 3/10/2023
 */
@RestController
public class Controller {
    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    void set(@Valid @RequestBody Employee employee) {
        System.out.println("Helo");

    }

}
