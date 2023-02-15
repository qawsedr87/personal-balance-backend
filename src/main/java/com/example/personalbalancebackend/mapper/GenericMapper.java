package com.example.personalbalancebackend.mapper;

import java.util.List;

/**
 *
 *
 * @param <D> Data Transfer Object: for Api using
 * @param <E> Entity: for Database using
 */
public interface GenericMapper<D, E> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toListEntity(List<D> dtos);

    List<D> toListDTO(List<E> entities);
}
