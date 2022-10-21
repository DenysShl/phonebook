package com.example.phonebook.dto.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.phonebook.dto.PhoneRequestDto;
import com.example.phonebook.dto.PhoneResponseDto;
import com.example.phonebook.model.Phone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.time.LocalDateTime.now;

class PhoneMapperTest {
    private static PhoneMapper phoneMapper;
    private Phone phone;
    private PhoneResponseDto phoneResponseDto;
    private PhoneRequestDto phoneRequestDto;

    @BeforeAll
    static void beforeAll() {
        phoneMapper = new PhoneMapper();
    }

    @BeforeEach
    void setUp() {
        phone = getPhone();
        phoneResponseDto = getPhoneResponseDto();
        phoneRequestDto = getPhoneRequestDto();
    }

    @Test
    void toDto_Ok() {
        PhoneResponseDto actual = phoneMapper.toDto(phone);
        assertEquals(phoneResponseDto, actual);
        assertEquals(phoneResponseDto.getNumberPhone(), actual.getNumberPhone());
    }

    @Test
    void toModel_Ok() {
        Phone actual = phoneMapper.toModel(phoneRequestDto);
        assertEquals(phone.getNumberPhone(), actual.getNumberPhone());
    }

    private PhoneRequestDto getPhoneRequestDto() {
        PhoneRequestDto phoneRequestDto = new PhoneRequestDto();
        phoneRequestDto.setNumberPhone("+380501472536");
        return phoneRequestDto;
    }

    private PhoneResponseDto getPhoneResponseDto() {
        PhoneResponseDto phoneResponseDto = new PhoneResponseDto();
        phoneResponseDto.setId(1L);
        phoneResponseDto.setNumberPhone("+380501472536");
        return phoneResponseDto;
    }

    private Phone getPhone() {
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumberPhone("+380501472536");
        phone.setCreated(now());
        return phone;
    }
}
