package com.estudoTestes.api.service;


import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.repository.CRUDRepository;
import com.estudoTestes.api.repository.custom.UserRepository;
import com.estudoTestes.api.service.adapter.custom.UserAdapter;
import com.estudoTestes.api.service.custom.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    private CRUDRepository crudRepository;

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
    void whenFindByIdThenReturnAnUserInstance() {
        User entity = mock(User.class);
        CRUDRepository<User, Integer> repository = mock(CRUDRepository.class);

        when(repository.findById(any())).thenReturn(Optional.ofNullable(entity));

        UserDTO response = mock(UserDTO.class);

        when(adapter.fromEntity(entity)).thenReturn(response);

        var result = service.findById(1);

        verify(repository).findById(any());
        verify(adapter).fromEntity(entity);

    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of((new User(ID, NAME, EMAIL, PASSWORD)));

    }
}
