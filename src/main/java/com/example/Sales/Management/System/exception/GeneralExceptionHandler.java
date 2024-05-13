package com.example.Sales.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.ArrayList;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionFormat> handleRecordNotFound(RecordNotFoundException recordNotFoundEcxeption)
    {
        return new ResponseEntity<>(
                new ExceptionFormat(false,LocalTime.now(),recordNotFoundEcxeption.getMessage(),new ArrayList<>())
                , HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RecordAlreadyExistException.class)
    public ResponseEntity<ExceptionFormat> handleRecordNotFound(RecordAlreadyExistException recordAlreadyExistException)
    {
        return new ResponseEntity<>(
                new ExceptionFormat(false,LocalTime.now(),recordAlreadyExistException.getMessage(),new ArrayList<>())
                , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionFormat> handleRecordNotFound(RuntimeException runtimeException)
    {
        return new ResponseEntity<>(
                new ExceptionFormat(false,LocalTime.now(),runtimeException.getMessage(),new ArrayList<>())
                , HttpStatus.CONFLICT);
    }
}
