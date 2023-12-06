package com.Herbaciarnia.DrinkTeaWithMe.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String property) {
        super("Invalid property value: " + property);
    }
}
