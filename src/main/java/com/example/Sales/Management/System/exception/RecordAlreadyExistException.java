package com.example.Sales.Management.System.exception;

public class RecordAlreadyExistException extends RuntimeException {
    
    public RecordAlreadyExistException(String message){
        super(message);
    }

}
