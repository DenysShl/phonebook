package com.example.phonebook.service;

import com.example.phonebook.model.Phone;
import java.time.LocalDateTime;
import java.util.List;

public interface PhoneService extends GenericService<Phone> {
    List<Phone> getAllByCreatedAfter(LocalDateTime dateTime);
}
