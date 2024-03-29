package com.nagarro.miniAssignment2.service;
import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



    @Service
    public class UserCreationService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserVerificationService userVerificationService;

        @Autowired
        private UserFactory userFactory;

        public List<User> createUsers(int numberOfUsers) {
            return IntStream.range(0, numberOfUsers)
                    .mapToObj(i -> userVerificationService.verifyUser().block().getUserData())
                    .map(userFactory::createUser)
                    .map(userRepository::save)
                    .collect(Collectors.toList());
        }
    }
