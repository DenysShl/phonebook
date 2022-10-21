package com.example.phonebook.dto.mapper;

public interface MapperRequestDto<U, V> {
    U toModel(V v);
}
