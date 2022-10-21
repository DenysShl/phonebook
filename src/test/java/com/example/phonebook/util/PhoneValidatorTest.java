package com.example.phonebook.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PhoneValidatorTest {
    private PhoneValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PhoneValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+38-050-111-2233",
            "+380501234569",
            "+38.050.111.2233"})
    void isValid_Ok(String string) {
        assertTrue(validator.isValid(string, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "123",
            "38-050-123-33-33",
            "+38,050,111,2233",
            "+38*050*123*45*69",
            "+38 050 222 55 78",
            "+38-O5O-111-22-3333",
            "+380050123123123123"})
    void isValid_IsNotValid_notOk(String string) {
        assertFalse(validator.isValid(string, null));
    }
}
