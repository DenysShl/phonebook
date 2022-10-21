package com.example.phonebook.dto.mapper;

public interface MapperResponseDto<U, V> {
    U toDto(V v);
}
