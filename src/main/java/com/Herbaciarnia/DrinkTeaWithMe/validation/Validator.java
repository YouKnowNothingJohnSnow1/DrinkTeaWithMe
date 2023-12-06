package com.Herbaciarnia.DrinkTeaWithMe.validation;

import com.Herbaciarnia.DrinkTeaWithMe.dto.OrderDto;
import com.Herbaciarnia.DrinkTeaWithMe.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Validator {


    public void validate(OrderDto orderDto){
        List<String> validationErrors = new ArrayList<>();
        validateFields(orderDto.getFirstName(), "firstName" , validationErrors);
        validateFields(orderDto.getLastName(), "lastName" , validationErrors);
        validateFields(orderDto.getCity(), "city" , validationErrors);
        validateFields(orderDto.getStreet(), "street" , validationErrors);
        validateFields(orderDto.getZipCode(), "zipCode" , validationErrors);

        if(!validationErrors.isEmpty()){
            throw new ValidationException(String.join(", ", validationErrors));
        }
    }

    private void validateFields(String value, String fieldName , List<String> validationErrors){
        if(value == null || isBlank(value)){
            throw new ValidationException(fieldName + " cannot be blank");
        }
    }
    private boolean isBlank(String value){
        return Optional.ofNullable(value)
                .map(String::trim)
                .map(v->v.length() == 0)
                .orElse(true);
    }
}
