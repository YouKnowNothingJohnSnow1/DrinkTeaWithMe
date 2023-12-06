package com.Herbaciarnia.DrinkTeaWithMe.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String tea, String id, Long Long){
        super("Resource not found") ;
    }

    public ResourceNotFoundException(String s) {
        super("Resource not found" );
    }
}
