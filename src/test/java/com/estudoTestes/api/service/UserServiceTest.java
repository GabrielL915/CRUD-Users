package com.estudoTestes.api.service;


import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.repository.CRUDRepository;
import com.estudoTestes.api.repository.custom.UserRepository;
import com.estudoTestes.api.service.adapter.Adapter;
import com.estudoTestes.api.service.custom.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CRUDRepository<User, Integer> crudRepository;

    @Mock
    private Adapter<User, UserDTO> userAdapter;

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        User user = mock(User.class);
        when(crudRepository.findById(anyInt())).thenReturn(Optional.of(user));

        UserDTO response = mock(UserDTO.class);
        when(userAdapter.fromEntity(any(User.class))).thenReturn(response);

        userService.findById(1);

        verify(crudRepository).findById(1);
        verify(userAdapter).fromEntity(user);
    }

    @Test
    void whenFindAllThenReturnUserInstance() {
        User user = mock(User.class);
        when(crudRepository.findAll()).thenReturn(List.of(user));

        UserDTO response = mock(UserDTO.class);
        when(userAdapter.fromEntity(any(User.class))).thenReturn(response);

        userService.findAll();

        verify(crudRepository).findAll();
        verify(userAdapter).fromEntity(user);
    }

    @Test
    void whenCreateValidUserThenReturnUserDTO() {
        UserDTO newUserDTO = new UserDTO(1, "aa", "email@example.com", "senha123");
        User newUser = new User();

        newUser.setId(newUserDTO.id());
        newUser.setName(newUserDTO.name());
        newUser.setEmail(newUserDTO.email());
        newUser.setPassword(newUserDTO.password());

        when(userAdapter.fromDTO(any(UserDTO.class))).thenReturn(newUser);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(crudRepository.save(any(User.class))).thenReturn(newUser);
        when(userAdapter.fromEntity(any(User.class))).thenReturn(newUserDTO);


        userService.create(newUserDTO);

        verify(crudRepository).save(newUser);
        verify(userAdapter).fromEntity(newUser);

    }
}
