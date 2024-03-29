package com.nagarro.miniAssignment2.validator;

public class NumericValidator implements Validator {
    @Override
    public boolean validate(String input) {
       
        return input.matches("^[0-9]+$");
    }
}
