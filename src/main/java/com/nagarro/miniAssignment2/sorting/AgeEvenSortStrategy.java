package com.nagarro.miniAssignment2.sorting;

import com.nagarro.miniAssignment2.model.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AgeEvenSortStrategy implements UserSortStrategy {
    @Override
    public List<User> sort(List<User> users) {
        Comparator<User> ageComparator = Comparator.comparingInt(User::getAge);
        Comparator<User> evenFirstComparator = Comparator.comparingInt(u -> u.getAge() % 2);

        return users.stream()
                    .sorted(evenFirstComparator.thenComparing(ageComparator))
                    .collect(Collectors.toList());
    }
}
