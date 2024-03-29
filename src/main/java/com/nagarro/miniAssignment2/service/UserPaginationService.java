package com.nagarro.miniAssignment2.service;

import com.nagarro.miniAssignment2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPaginationService {

    @Autowired
    private UserSortService userSortService;

    public Map<String, Object> getPaginatedAndSortedUsers(String sortType, String sortOrder, int limit, int offset, List<User> allUsers) {

        List<User> processedUsers;

       
        if (sortType == null  || sortOrder == null ) {
            processedUsers = allUsers;
        } else {
            processedUsers = userSortService.sortUsers(allUsers, sortType, sortOrder);
        }

        int total = processedUsers.size();
        int start = Math.min(offset * limit, total);
        int end = Math.min(start + limit, total);

        List<User> paginatedUsers = processedUsers.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("data", paginatedUsers);
        response.put("pageInfo", Map.of(
            "hasNextPage", end < total,
            "hasPreviousPage", start > 0,
            "total", total
        ));

        return response;
    }
}
