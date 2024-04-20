package com.estudoTestes.api.repository.custom;

import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.repository.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CRUDRepository<User, Integer> {
     Optional<User> findByEmail(String email);
}
