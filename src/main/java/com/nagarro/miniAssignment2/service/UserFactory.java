package com.nagarro.miniAssignment2.service;

import org.springframework.stereotype.Service;

import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.model.UserData;

@Service
public class UserFactory {

    public User createUser(UserData userData) {
        User user = new User();
        user.setName(userData.getName().getFullName());
        user.setAge(userData.getDob().getAge());
        user.setGender(userData.getGender());
        user.setDob(userData.getDob().getDate());
        user.setNationality(userData.getNat());
        user.setVerificationStatus("TO_BE_VERIFIED");
        return user;
    }
}

