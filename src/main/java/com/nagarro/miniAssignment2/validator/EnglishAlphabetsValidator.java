package com.nagarro.miniAssignment2.validator;

public class EnglishAlphabetsValidator implements Validator {
    @Override
    public boolean validate(String input) {
        return input.matches("[a-zA-Z]+");
    }
}

