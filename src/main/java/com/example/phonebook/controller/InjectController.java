package com.example.phonebook.controller;

import com.example.phonebook.model.Phone;
import com.example.phonebook.model.User;
import com.example.phonebook.service.PhoneService;
import com.example.phonebook.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final UserService userService;
    private final PhoneService phoneService;

    public InjectController(UserService userService, PhoneService phoneService) {
        this.userService = userService;
        this.phoneService = phoneService;
    }

    @GetMapping
    @ApiOperation(value = "Inject data test")
    public String dataInject() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Phone phone1 = createPhone("050-132-2323",
                LocalDateTime.parse("02-02-2002 18:25:37", formatter));
        Phone phone2 = createPhone("050-152-5252", LocalDateTime.now());
        Phone phone3 = createPhone("050-111-2233", LocalDateTime.now());

        createUser("Den", "Sh", List.of(phone1, phone3));
        createUser("Mak", "Sh", List.of(phone2));

        return "Successfully";
    }

    private void createUser(String firstName, String lastName, List<Phone> phones) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhones(phones);
        userService.create(user);
    }

    private Phone createPhone(String numberPhone, LocalDateTime dateTime) {
        Phone phone = new Phone();
        phone.setNumberPhone(numberPhone);
        phone.setCreated(dateTime);
        return phoneService.create(phone);
    }
}
