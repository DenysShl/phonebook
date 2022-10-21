package com.example.phonebook.dto.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.phonebook.dto.UserRequestDto;
import com.example.phonebook.dto.UserResponseDto;
import com.example.phonebook.model.Phone;
import com.example.phonebook.model.User;
import com.example.phonebook.repository.PhoneRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static java.time.LocalDateTime.now;

class UserMapperTest {
    private Phone phone;
    private User user;
    private UserResponseDto userResponseDto;
    private UserRequestDto userRequestDto;
    private static UserMapper userMapper;
    private static PhoneRepository phoneRepository;

    @BeforeAll
    static void beforeAll() {
        phoneRepository = Mockito.mock(PhoneRepository.class);
        userMapper = new UserMapper(phoneRepository);
    }

    @BeforeEach
    void setUp() {
        phone = getPhone();
        user = getUser();
        userResponseDto = getUserResponseDto();
        userRequestDto = getUserRequestDto();
    }

    @Test
    void toDto_Ok() {
        UserResponseDto actual = userMapper.toDto(user);
        assertEquals(userResponseDto, actual);
    }

    @Test
    void toModel_Ok() {
        Mockito.when(phoneRepository.findById(1L)).thenReturn(Optional.of(phone));
        User actual = userMapper.toModel(userRequestDto);
        assertEquals(user.getPhones(), actual.getPhones());
        assertEquals(user.getFirstName(), actual.getFirstName());
        assertEquals(user.getLastName(), actual.getLastName());
    }

    private UserResponseDto getUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setFirstName("Den");
        userResponseDto.setLastName("Shl");
        userResponseDto.setPhones(List.of(phone.getNumberPhone()));
        return userResponseDto;
    }

    private UserRequestDto getUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName("Den");
        userRequestDto.setLastName("Shl");
        userRequestDto.setPhoneIds(List.of(phone.getId()));
        return userRequestDto;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Den");
        user.setLastName("Shl");
        user.setPhones(List.of(this.phone));
        user.setDataCreatedUser(now());
        return user;
    }

    private Phone getPhone() {
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumberPhone("+380501472536");
        phone.setCreated(now());
        return phone;
    }
}
