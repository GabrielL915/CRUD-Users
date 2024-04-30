package com.estudoTestes.api.service;


import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.repository.custom.UserRepository;
import com.estudoTestes.api.service.adapter.custom.UserAdapter;
import com.estudoTestes.api.service.custom.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    private static final Integer ID = 1;
    private static final String NAME = "AA";
    private static final String EMAIL = "AA@gmail.com";
    private static final String PASSWORD = "123";


    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserAdapter adapter;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserIstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = repository.findById(ID).orElseThrow();
        assertNotNull(response);

        assertEquals(ID, response.getId());
//        assertEquals(NAME, response.name());
//        assertEquals(EMAIL, response.email());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of((new User(ID, NAME, EMAIL, PASSWORD)));

    }
}
