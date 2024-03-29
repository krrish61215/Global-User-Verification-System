package com.nagarro.miniAssignment2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.exception.InvalidInputException;
import com.nagarro.miniAssignment2.model.CustomErrorResponse;
import com.nagarro.miniAssignment2.service.UserCreationService;
import com.nagarro.miniAssignment2.service.UserService;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserCreationService userCreationService;

    @Autowired
    public UserController(UserService userService, UserCreationService userCreationService) {
        this.userService = userService;
        this.userCreationService = userCreationService;
    }

    @PostMapping
    public ResponseEntity<?> createUsers(@RequestParam int size) {
        if (size < 1 || size > 5) {
            return ResponseEntity.badRequest().body(
                new CustomErrorResponse("Invalid size parameter", 400, getCurrentTimestamp())
            );
        }
        List<User> users = userCreationService.createUsers(size);
        return ResponseEntity.ok(users);
    }

    @GetMapping
    public ResponseEntity<?> getUsersWithPagination(
        @RequestParam(required = false) String sortType,
        @RequestParam(required = false) String sortOrder,
        @RequestParam(defaultValue = "5") int limit,
        @RequestParam(defaultValue = "0") int offset
    ) {
        try {
            Map<String, Object> response = userService.getUsersWithPagination(sortType, sortOrder, limit, offset);
            return ResponseEntity.ok(response);
        } catch (InvalidInputException ex) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse(ex.getMessage(), ex.getCode(), ex.getTimestamp()));
        }
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss").format(LocalDateTime.now());
    }
}
