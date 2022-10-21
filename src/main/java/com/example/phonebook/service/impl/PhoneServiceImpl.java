package com.example.phonebook.service.impl;

import com.example.phonebook.model.Phone;
import com.example.phonebook.repository.PhoneRepository;
import com.example.phonebook.service.PhoneService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Phone create(Phone phone) {
        if (phone.getCreated() == null) {
            phone.setCreated(LocalDateTime.now());
        }
        return phoneRepository.save(phone);
    }

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone getById(Long id) {
        return phoneRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can`t find phone by id:" + id));
    }

    @Override
    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public List<Phone> getAllByCreatedAfter(LocalDateTime dateTime) {
        return phoneRepository.getAllByCreatedAfterOrderByCreatedDesc(dateTime);
    }
}
