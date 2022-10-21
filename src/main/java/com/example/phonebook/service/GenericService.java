package com.example.phonebook.service;

import java.util.List;

public interface GenericService<T> {
    T create(T t);

    List<T> getAll();

    T getById(Long id);

    void deleteById(Long id);
}
