package com.nagarro.miniAssignment2.validator;

public class ValidatorFactory {
    public static Validator getValidator(String inputType) {
        if ("numeric".equals(inputType)) {
            return new NumericValidator();
        } else if ("alphabets".equals(inputType)) {
            return new EnglishAlphabetsValidator();
        }
        throw new IllegalArgumentException("Invalid input type");
    }
}

