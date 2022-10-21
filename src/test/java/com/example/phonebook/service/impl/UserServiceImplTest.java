package com.example.phonebook.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.phonebook.model.Phone;
import com.example.phonebook.model.User;
import com.example.phonebook.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static java.time.LocalDateTime.now;

class UserServiceImplTest {
    private static UserRepository userRepository;
    private static UserServiceImpl userService;
    private User user;

    @BeforeAll
    static void beforeAll() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @BeforeEach
    void setUp() {
        Phone phone = createdPhoneForTest(1L, "+38-050-585-56-71");
        user = createUserForTest(1L, "Den", "Shl", List.of(phone), now());
    }

    @Test
    void create_Ok() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User actual = userService.create(user);
        assertNotNull(actual);
        assertEquals(user, actual);
        assertEquals(user.getFirstName(), actual.getFirstName());
        assertEquals(user.getPhones(), actual.getPhones());

    }

    @Test
    void getAll_Ok() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> actual = userService.getAll();
        assertEquals(List.of(user), actual);
        assertEquals(1, actual.size());
    }

    @Test
    void getById() {
        long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User actual = userService.getById(id);
        assertEquals(user, actual);
    }

    @Test
    void getById_notOk() {
        long id = -11L;
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,
                () -> userService.getById(id));
    }

    @Test
    void deleteById() {
        try {
            userService.deleteById(1L);
        } catch (Exception e) {
            throw new RuntimeException("Can`t delete user by id", e);
        }
    }

    @Test
    void getAllUsersByPhone_Ok() {
        Mockito.when(userRepository.findUsersByPhone("58556"))
                .thenReturn(List.of(user));
        List<User> actual = userService.getAllUsersByPhone("58556");
        assertEquals(List.of(user), actual);
    }

    private User createUserForTest(Long id,
                                   String fName,
                                   String lName,
                                   List<Phone> phones,
                                   LocalDateTime dateNow) {
        User user = new User();
        user.setId(id);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setPhones(phones);
        user.setDataCreatedUser(dateNow);
        return user;
    }

    private Phone createdPhoneForTest(Long id, String number) {
        Phone phone = new Phone();
        phone.setId(id);
        phone.setNumberPhone(number);
        return phone;
    }
}
