package com.example.phonebook.controller;

import com.example.phonebook.dto.UserRequestDto;
import com.example.phonebook.dto.UserResponseDto;
import com.example.phonebook.dto.mapper.impl.UserMapper;
import com.example.phonebook.model.User;
import com.example.phonebook.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new user")
    public UserResponseDto create(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        return userMapper.toDto(userService.create(user));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find user by `id`")
    public UserResponseDto getById(@PathVariable Long id) {
        return userMapper.toDto(userService.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by `id`")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        user.setId(id);
        return userMapper.toDto(userService.create(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by `id`")
    public void delete(@RequestParam Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all user")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Search users by phone number")
    @GetMapping("/search")
    public List<UserResponseDto> getAllByPhone(@RequestParam String phoneNumber) {
        return userService.getAllUsersByPhone(phoneNumber).stream()
                .map(userMapper::toDto)
                .toList();
    }
}
