package com.nagarro.miniAssignment2.sorting;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserSortingFactory {
    private Map<String, UserSortStrategy> sortStrategies;

    public UserSortingFactory() {
        sortStrategies = new HashMap<>();
        sortStrategies.put("NameEven", new NameEvenSortStrategy());
        sortStrategies.put("NameOdd", new NameOddSortStrategy());
        sortStrategies.put("AgeEven", new AgeEvenSortStrategy());
        sortStrategies.put("AgeOdd", new AgeOddSortStrategy());
        
    }

    public UserSortStrategy getSortStrategy(String sortType, String sortOrder) {
        String key = sortType.trim() + sortOrder.trim();
        return sortStrategies.getOrDefault(key, null);
    }
}
