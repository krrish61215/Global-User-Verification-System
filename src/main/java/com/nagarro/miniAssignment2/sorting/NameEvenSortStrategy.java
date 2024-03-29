package com.nagarro.miniAssignment2.sorting;


import com.nagarro.miniAssignment2.model.User;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NameEvenSortStrategy implements UserSortStrategy {
    @Override
    public List<User> sort(List<User> users) {
        Comparator<User> nameLengthComparator = Comparator.comparingInt(u -> u.getName().length());
        Comparator<User> evenFirstComparator = Comparator.comparingInt(u -> u.getName().length() % 2);

        return users.stream()
                    .sorted(evenFirstComparator.thenComparing(nameLengthComparator))
                    .collect(Collectors.toList());
    }
}
