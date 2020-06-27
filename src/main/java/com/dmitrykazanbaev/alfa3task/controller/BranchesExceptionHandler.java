package com.dmitrykazanbaev.alfa3task.controller;

import com.dmitrykazanbaev.alfa3task.exception.ApiError;
import com.dmitrykazanbaev.alfa3task.exception.NotFoundBranchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BranchesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handle(NotFoundBranchException exception) {
        return new ResponseEntity<>(new ApiError("branch not found"), HttpStatus.NOT_FOUND);
    }
}
