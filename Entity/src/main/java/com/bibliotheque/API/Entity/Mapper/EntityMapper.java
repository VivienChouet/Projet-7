package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.User;

import java.util.List;

public interface EntityMapper<D,E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}