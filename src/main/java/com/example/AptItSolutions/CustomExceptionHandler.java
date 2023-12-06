package com.example.AptItSolutions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        // Log the error or handle it as needed
        return "error/400"; // Return the name of the Thymeleaf template for 400 errors
    }
}
