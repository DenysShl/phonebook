package com.example.phonebook.dto;

import com.example.phonebook.util.ValidPhone;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PhoneRequestDto {
    @NotNull(message = "Please enter phone number")
    @ValidPhone
    @Size(min = 10, max = 17, message = "Number should have at least 10 or less than 17 digits")
    private String numberPhone;
}
