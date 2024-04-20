package com.estudoTestes.api.service.custom;

import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.repository.CRUDRepository;
import com.estudoTestes.api.repository.custom.UserRepository;
import com.estudoTestes.api.service.CRUDService;
import com.estudoTestes.api.service.adapter.Adapter;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUDService<User, Integer, UserDTO> {

    private final UserRepository userRepository;

    public UserService(CRUDRepository<User, Integer> repository, Adapter<User, UserDTO> adapter, UserRepository userRepository) {
        super(repository, adapter);
        this.userRepository = userRepository;
    }

    @Override
    protected void updateData(UserDTO userDTO, User user) {
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
    }

    @Override
    protected void checkSave(UserDTO userDTO, User user) {
        boolean userAlreadyExists = userRepository.findByEmail(userDTO.email()).isPresent();
        if (userAlreadyExists) {
            throw new RuntimeException("usuario ja existe");
        }
    }
}
