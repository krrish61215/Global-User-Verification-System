package com.nagarro.miniAssignment2;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nagarro.miniAssignment2.controller.UserController;
import com.nagarro.miniAssignment2.service.UserService;
import com.nagarro.miniAssignment2.exception.InvalidInputException;
import com.nagarro.miniAssignment2.model.CustomErrorResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class UserControllerGetTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

  

    @SuppressWarnings("deprecation")
	@Test
    public void testGetUsersWithPagination_Success() {
        
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("data", Collections.emptyList()); 
        mockResponse.put("pageInfo", new HashMap<>());
        when(userService.getUsersWithPagination("name", "asc", 5, 0)).thenReturn(mockResponse);

        ResponseEntity<?> response = userController.getUsersWithPagination("name", "asc", 5, 0);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
       
    }

    @Test
    public void testGetUsersWithPagination_InvalidInput() {
        
        when(userService.getUsersWithPagination("invalid", "asc", 5, 0))
                .thenThrow(new InvalidInputException("Invalid sortType or sortOrder format: invalid asc", 400, "timeStamp"));

      
        ResponseEntity<?> response = userController.getUsersWithPagination("invalid", "asc", 5, 0);

       
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof CustomErrorResponse);
        CustomErrorResponse errorResponse = (CustomErrorResponse) response.getBody();
        assertEquals("Invalid sortType or sortOrder format: invalid asc", errorResponse.getMessage());
        assertEquals(400, errorResponse.getCode());
    }
    }