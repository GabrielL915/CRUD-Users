package com.estudoTestes.api.service;

import com.estudoTestes.api.repository.CRUDRepository;
import com.estudoTestes.api.service.adapter.Adapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public abstract class CRUDService<E, ID, D> {
    private final CRUDRepository<E, ID> repository;
    private final Adapter<E, D> adapter;

    public CRUDService(CRUDRepository<E, ID> repository, Adapter<E, D> adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }

    public D create(D dto) {
        E newEntity = adapter.fromDTO(dto);
        validateNewEntity(dto, newEntity);
        return getDTOFromEntity(repository.save(newEntity));
    }

    public List<D> findAll() {
        return repository.findAll().stream().map(this::getDTOFromEntity).toList();
    }

    public D findById(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Elemento com ID " + id + " não encontrado."));
        return getDTOFromEntity(entity);
    }

    public D update(ID id, D dto) {
        E entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Elemento com ID " + id + " não encontrado."));
        updateEntityData(dto, entity);
        return getDTOFromEntity(repository.save(entity));
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected abstract void updateEntityData(D dto, E entity);

    protected abstract void validateNewEntity(D dto, E entity);

    private D getDTOFromEntity(E entity) {
        return adapter.fromEntity(entity);
    }
}
