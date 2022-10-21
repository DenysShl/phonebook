package com.example.phonebook.dto.mapper.impl;

import com.example.phonebook.dto.UserRequestDto;
import com.example.phonebook.dto.UserResponseDto;
import com.example.phonebook.dto.mapper.MapperRequestDto;
import com.example.phonebook.dto.mapper.MapperResponseDto;
import com.example.phonebook.model.Phone;
import com.example.phonebook.model.User;
import com.example.phonebook.repository.PhoneRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements MapperResponseDto<UserResponseDto, User>,
        MapperRequestDto<User, UserRequestDto> {
    private final PhoneRepository phoneRepository;

    public UserMapper(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        List<String> list = user.getPhones()
                .stream()
                .map(Phone::getNumberPhone)
                .collect(Collectors.toList());
        userResponseDto.setPhones(list);
        return userResponseDto;
    }

    @Override
    public User toModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setPhones(userRequestDto.getPhoneIds()
                .stream()
                .map(p -> phoneRepository.findById(p).get())
                .collect(Collectors.toList()));
        return user;
    }
}
