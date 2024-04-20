package com.estudoTestes.api.service.adapter;

public interface Adapter<E, D> {

    E fromDTO(D dto);

    D fromEntity(E entity);
}