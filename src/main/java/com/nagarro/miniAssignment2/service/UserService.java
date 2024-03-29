package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.exception.InvalidInputException;
import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPaginationService userPaginationService;
    @Autowired
    private InputValidationService inputValidationService;

   

    public Map<String, Object> getUsersWithPagination(String sortType, String sortOrder, int limit, int offset) {
        try {
            inputValidationService.validateInput(sortType, sortOrder, limit, offset);
        } catch (InvalidInputException ex) {
           
            throw ex;
        }

        List<User> allUsers = userRepository.findAll();
        return userPaginationService.getPaginatedAndSortedUsers(sortType, sortOrder, limit, offset, allUsers);
    }
}
