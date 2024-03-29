package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.exception.InvalidInputException;
import com.nagarro.miniAssignment2.validator.Validator;
import com.nagarro.miniAssignment2.validator.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class InputValidationService {

    public void validateInput(String sortType, String sortOrder, int limit, int offset) {
        Validator alphabetsValidator = ValidatorFactory.getValidator("alphabets");
        Validator numericValidator = ValidatorFactory.getValidator("numeric");

        if (sortType != null && !alphabetsValidator.validate(sortType.trim())) {
            throw new InvalidInputException("Invalid sortType format", 400, getCurrentTimestamp());
        }

        if (sortOrder != null && !alphabetsValidator.validate(sortOrder.trim())) {
            throw new InvalidInputException("Invalid sortOrder format", 400, getCurrentTimestamp());
        }

    
        if (!(numericValidator.validate(String.valueOf(limit)) && numericValidator.validate(String.valueOf(offset)))) {
            throw new InvalidInputException("Invalid limit or offset format", 400, getCurrentTimestamp());
        }

        
        if (limit < 1 || limit > 5) {
            throw new InvalidInputException("Limit should be between 1 and 5", 400, getCurrentTimestamp());
        }
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss").format(LocalDateTime.now());
    }
}
