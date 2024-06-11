package com.estudoTestes.api.controller;

import com.estudoTestes.api.domain.entity.User;
import com.estudoTestes.api.domain.dto.UserDTO;
import com.estudoTestes.api.service.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private static final String PATH_VARIABLE = "/{id}";
    private static final String PATH_PAGINATE = "/page/{pageNumber}/{pageSize}";
    private final CRUDService<User, Integer, UserDTO> service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(PATH_VARIABLE)
                .buildAndExpand(service.create(dto).id())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(PATH_PAGINATE)
    public ResponseEntity<List<UserDTO>> getAllPage(@PathVariable("pageNumber") final int pageNumber,
                                                    @PathVariable("pageSize") final int pageSize) {
        return ResponseEntity.ok().body(service.findAllByPage(pageNumber, pageSize));
    }

    @GetMapping(PATH_VARIABLE)
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(PATH_VARIABLE)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(PATH_VARIABLE)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
