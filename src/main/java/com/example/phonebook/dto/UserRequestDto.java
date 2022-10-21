package com.example.phonebook.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "Please enter first name")
    @Size(min = 4, message = "Name should be atleast 4 characters")
    @Size(max = 15, message = "Name should not be greater than 15 characters")
    private String firstName;
    @NotBlank(message = "Please enter last name")
    @Size(min = 4, message = "Name should be atleast 4 characters")
    @Size(max = 15, message = "Name should not be greater than 15 characters")
    private String lastName;
    private List<Long> phoneIds;
}
