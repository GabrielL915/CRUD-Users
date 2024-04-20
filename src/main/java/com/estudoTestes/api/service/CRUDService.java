package com.estudoTestes.api.service;

import com.estudoTestes.api.repository.CRUDRepository;
import com.estudoTestes.api.service.adapter.Adapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class CRUDService<E, ID, D> {

    private final CRUDRepository<E, ID> repository;

    private final Adapter<E, D> adapter;

    public D create(D dto) {
        E newEntity = adapter.fromDTO(dto);
        checksave(dto, newEntity);
        return getDTOFromEntity(repository.save(newEntity));
    }

    public List<D> findAll() {
        return repository.findAll().stream().map(this::getDTOFromEntity).toList();
    }

    public D findById(ID id) {
        var entity = getById(id);
        return getDTOFromEntity(entity);
    }

    public D update(ID id, D dto) {
        E entity = getById(id);
        checksave(dto, entity);
        updateData(dto, entity);
        return getDTOFromEntity(repository.save(entity));
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }


    private E getById(ID id) {
        return repository.findById(id).orElseThrow();
    }

    protected abstract void updateData(D dto, E entity);

    protected abstract void checksave(D dto, E newEntity);

    private D getDTOFromEntity(E entity) {
        return adapter.fromEntity(entity);
    }


}
