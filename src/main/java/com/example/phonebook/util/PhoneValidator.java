package com.example.phonebook.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    private static final String VALID_PHONE_NUMBER_REGEX
            = "^\\+[0-9]{1,2}[-.]?[0-9]{1,3}[-.]?[0-9]{1,3}[-.]?[0-9]{1,4}";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber != null && phoneNumber.matches(VALID_PHONE_NUMBER_REGEX);
    }
}
