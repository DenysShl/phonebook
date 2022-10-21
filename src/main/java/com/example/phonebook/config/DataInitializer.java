package com.example.phonebook.config;

import com.example.phonebook.model.Phone;
import com.example.phonebook.model.User;
import com.example.phonebook.service.PhoneService;
import com.example.phonebook.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DataInitializer {
    private final UserService userService;
    private final PhoneService phoneService;

    public DataInitializer(UserService userService, PhoneService phoneService) {
        this.userService = userService;
        this.phoneService = phoneService;
    }

    @PostConstruct
    public void dataInject() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Phone phone1 = createPhone("050-132-2323",
                LocalDateTime.parse("02-02-2002 18:25:37", formatter));
        Phone phone2 = createPhone("050-152-5252", LocalDateTime.now());
        Phone phone3 = createPhone("050-111-2233", LocalDateTime.now());
        createUser("Den", "Sh", List.of(phone1, phone3));
        createUser("Mak", "Sh", List.of(phone2));
        log.info("Successfully");
    }

    private void createUser(String firstName, String lastName, List<Phone> phones) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhones(phones);
        user.setDataCreatedUser(LocalDateTime.now());
        userService.create(user);
    }

    private Phone createPhone(String numberPhone, LocalDateTime dateTime) {
        Phone phone = new Phone();
        phone.setNumberPhone(numberPhone);
        phone.setCreated(dateTime);
        return phoneService.create(phone);
    }
}
