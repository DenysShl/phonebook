package com.example.phonebook.service.impl;

import com.example.phonebook.model.User;
import com.example.phonebook.repository.UserRepository;
import com.example.phonebook.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can`t find user by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsersByPhone(String phoneNumber) {
        return userRepository.findUsersByPhone(phoneNumber);
    }
}
