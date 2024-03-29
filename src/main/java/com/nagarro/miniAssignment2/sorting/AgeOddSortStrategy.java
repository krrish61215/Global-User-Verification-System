package com.nagarro.miniAssignment2.sorting;

import com.nagarro.miniAssignment2.model.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AgeOddSortStrategy implements UserSortStrategy {
    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                    .sorted(Comparator.comparingInt((User u) -> u.getAge() % 2 != 0 ? 0 : 1)
                            .thenComparing(User::getAge))
                    .collect(Collectors.toList());
    }
}

