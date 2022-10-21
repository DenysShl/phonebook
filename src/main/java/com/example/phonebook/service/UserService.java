package com.example.phonebook.service;

import com.example.phonebook.model.User;
import java.util.List;

public interface UserService extends GenericService<User> {
    List<User> getAllUsersByPhone(String phoneNumber);
}
