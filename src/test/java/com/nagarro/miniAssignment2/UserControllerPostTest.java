package com.nagarro.miniAssignment2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nagarro.miniAssignment2.controller.UserController;
import com.nagarro.miniAssignment2.model.User;
import com.nagarro.miniAssignment2.service.UserCreationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerPostTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserCreationService userCreationService;

   

    @Test
    public void createUsers_Success() {
        
        int size = 3;
        List<User> mockUsers = createMockUsers(size);
        when(userCreationService.createUsers(size)).thenReturn(mockUsers);

       
        ResponseEntity<?> response = userController.createUsers(size);

      
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(size, ((List<?>) response.getBody()).size());
    }

    @Test
    public void createUsers_InvalidSize() {
  
        int size = 6;

    
        ResponseEntity<?> response = userController.createUsers(size);

        
        assertEquals(400, response.getStatusCodeValue());
    }

    private List<User> createMockUsers(int size) {
        return Collections.nCopies(size, new User());
    }
}
