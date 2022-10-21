package com.example.phonebook.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<String> phones;
}
