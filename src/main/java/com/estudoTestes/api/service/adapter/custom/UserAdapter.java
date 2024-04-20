package com.estudoTestes.api.service.adapter.custom;

import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.service.adapter.Adapter;
import org.springframework.stereotype.Service;

@Service
public class UserAdapter implements Adapter<User, UserDTO> {
    @Override
    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.name(), userDTO.email(), userDTO.password());
    }

    @Override
    public UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
