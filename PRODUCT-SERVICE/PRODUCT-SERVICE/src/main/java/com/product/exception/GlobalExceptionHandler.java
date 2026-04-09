package com.product.exception;

import com.product.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> globalExceptionHandler(ResourceNotFoundException e , WebRequest request){
        ErrorDto errorDto = new ErrorDto(new Date(), e.getMessage(), request.getDescription(false));

        return  new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);



    }
}
