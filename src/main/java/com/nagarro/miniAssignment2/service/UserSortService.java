package com.nagarro.miniAssignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.sorting.UserSortStrategy;
import com.nagarro.miniAssignment2.sorting.UserSortingFactory;
import java.util.List;

@Service
public class UserSortService {
    private final UserSortingFactory userSortingFactory;

    @Autowired
    public UserSortService(UserSortingFactory userSortingFactory) {
        this.userSortingFactory = userSortingFactory;
    }

    public List<User> sortUsers(List<User> users, String sortType, String sortOrder) {
        UserSortStrategy strategy = userSortingFactory.getSortStrategy(sortType, sortOrder);
        if (strategy != null) {
        	System.out.println("stratagy is not null");
            return strategy.sort(users);
        }
        System.out.println(sortOrder + " "+ sortType);
        System.out.println(strategy);
        System.out.println("stratagy is  null");
        return users;
    }
}
