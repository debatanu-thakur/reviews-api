package com.udacity.course3.reviews.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_FAILED_MESSAGE = "Validation Failed";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException mEx,
        HttpHeaders httpH,
        HttpStatus status,
        WebRequest request
    ) {
        List<String> errors = mEx.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.toList());
        ApiError apiError = new ApiError(DEFAULT_FAILED_MESSAGE, errors);

        return handleExceptionInternal(mEx, apiError, httpH, HttpStatus.BAD_REQUEST, request);

    }
}
