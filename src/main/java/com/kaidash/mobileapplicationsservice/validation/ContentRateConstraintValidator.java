package com.kaidash.mobileapplicationsservice.validation;


import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ContentRateConstraintValidator implements ConstraintValidator<ContentRate, Integer> {

    private String authorizedValuesString;

    @Override
    public void initialize(ContentRate constraintAnnotation) {
        authorizedValuesString = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        List<Integer> authorizedValues = new ArrayList<>();
        for(String s : authorizedValuesString.split(" ")){
            authorizedValues.add(Integer.parseInt(s));
        }

        for(Integer value : authorizedValues){
            if(integer == value){
                return true;
            }
        }
        return false;
    }
}
