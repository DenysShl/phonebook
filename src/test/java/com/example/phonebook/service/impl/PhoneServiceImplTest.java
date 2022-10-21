package com.example.phonebook.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import com.example.phonebook.model.Phone;
import com.example.phonebook.repository.PhoneRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PhoneServiceImplTest {
    private static PhoneServiceImpl phoneService;
    private static PhoneRepository repository;
    private Phone phoneOk;
    private Phone phoneNotOk;

    @BeforeAll
    static void beforeAll() {
        repository = Mockito.mock(PhoneRepository.class);
        phoneService = new PhoneServiceImpl(repository);
    }

    @BeforeEach
    void setUp() {
        phoneOk = new Phone();
        phoneOk.setId(1L);
        phoneOk.setNumberPhone("+38-05-111-2233");
        phoneNotOk = new Phone();
        phoneNotOk.setNumberPhone("+380");
    }

    @Test
    void create_Ok() {
        Mockito.when(repository.save(phoneOk)).thenReturn(phoneOk);
        Phone actual = phoneService.create(phoneOk);
        assertEquals(phoneOk, actual);
        assertEquals(phoneOk.getNumberPhone(), actual.getNumberPhone());
        assertNotNull(actual.getCreated(), "Field 'created' can`t is empty");
    }

    @Test
    void create_notOk() {
        Mockito.when(repository.save(phoneNotOk)).thenReturn(phoneNotOk);
        Phone actual = phoneService.create(phoneNotOk);
        assertEquals(phoneNotOk, actual);
        assertEquals(phoneNotOk.getNumberPhone(), actual.getNumberPhone());
        assertEquals(LocalDateTime.now().getDayOfMonth(),actual.getCreated().getDayOfMonth());
        assertNotNull(actual.getCreated());

    }

    @Test
    void getAll_Ok() {
        Mockito.when(repository.findAll()).thenReturn(List.of(phoneOk));
        List<Phone> actual = phoneService.getAll();
        assertEquals(List.of(phoneOk), actual);
        assertEquals(1, actual.size());
    }

    @Test
    void getById_Ok() {
        long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(phoneOk));
        Phone actual = phoneService.getById(id);
        assertEquals(phoneOk, actual);
    }

    @Test
    void getById_nonExistingId_notOk() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                () -> phoneService.getById(-5l));
    }


    @Test
    void deleteById_Ok() {
        try {
            phoneService.deleteById(1L);
        } catch (Exception e) {
            throw new RuntimeException("Can`t delete phone by id", e);
        }
    }

    @Test
    void getAllByCreatedAfter_Ok() {
        Mockito.when(repository.getAllByCreatedAfterOrderByCreatedDesc(any()))
                .thenReturn(List.of(phoneOk));
        List<Phone> actual = phoneService.getAllByCreatedAfter(LocalDateTime.now());
        assertEquals(List.of(phoneOk), actual);
        assertEquals(1,actual.size());
    }
}
