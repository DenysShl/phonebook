package com.example.phonebook.dto.mapper.impl;

import com.example.phonebook.dto.PhoneRequestDto;
import com.example.phonebook.dto.PhoneResponseDto;
import com.example.phonebook.dto.mapper.MapperRequestDto;
import com.example.phonebook.dto.mapper.MapperResponseDto;
import com.example.phonebook.model.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper implements MapperResponseDto<PhoneResponseDto, Phone>,
        MapperRequestDto<Phone, PhoneRequestDto> {
    @Override
    public PhoneResponseDto toDto(Phone phone) {
        PhoneResponseDto responseDto = new PhoneResponseDto();
        responseDto.setId(phone.getId());
        responseDto.setNumberPhone(phone.getNumberPhone());
        return responseDto;
    }

    @Override
    public Phone toModel(PhoneRequestDto phoneRequestDto) {
        Phone phone = new Phone();
        phone.setNumberPhone(phoneRequestDto.getNumberPhone());
        return phone;
    }
}
