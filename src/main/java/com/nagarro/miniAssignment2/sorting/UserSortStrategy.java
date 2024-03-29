package com.nagarro.miniAssignment2.sorting;


import com.nagarro.miniAssignment2.model.User;
import java.util.List;

public interface UserSortStrategy {
    List<User> sort(List<User> users);
}
