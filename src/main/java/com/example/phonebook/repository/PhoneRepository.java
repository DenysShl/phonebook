package com.example.phonebook.repository;

import com.example.phonebook.model.Phone;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> getAllByCreatedAfterOrderByCreatedDesc(LocalDateTime created);
}
