package com.example.phonebook.controller;

import com.example.phonebook.dto.PhoneRequestDto;
import com.example.phonebook.dto.PhoneResponseDto;
import com.example.phonebook.dto.mapper.impl.PhoneMapper;
import com.example.phonebook.model.Phone;
import com.example.phonebook.service.PhoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    public PhoneController(PhoneService phoneService, PhoneMapper phoneMapper) {
        this.phoneService = phoneService;
        this.phoneMapper = phoneMapper;
    }

    @GetMapping
    @ApiOperation("Get all phones")
    public List<PhoneResponseDto> getAll() {
        return phoneService.getAll()
                .stream()
                .map(phone -> phoneMapper.toDto(phone))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation("Create new phone, mask +XX-XXX-XXX-XXXX")
    public PhoneResponseDto create(@Valid @RequestBody PhoneRequestDto phoneRequestDto) {
        return phoneMapper.toDto(phoneService.create(phoneMapper.toModel(phoneRequestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find phone by `id`")
    public PhoneResponseDto getById(@PathVariable Long id) {
        return phoneMapper.toDto(phoneService.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update phone by `id`")
    public PhoneResponseDto update(@PathVariable Long id,
                                   @Valid @RequestBody PhoneRequestDto phoneRequestDto) {
        Phone phone = phoneMapper.toModel(phoneRequestDto);
        phone.setId(id);
        return phoneMapper.toDto(phoneService.create(phone));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete phone by `id`")
    public void delete(@PathVariable Long id) {
        phoneService.deleteById(id);
    }

    @GetMapping("/after")
    @ApiOperation(value = "Get all phones list after created date by sort DESC")
    public List<PhoneResponseDto> getAllPhoneAfterDate(@RequestParam
            @ApiParam(value = "format is `dd-MM-yyyy HH:mm:ss`, example `02-02-2002 18:25:37`")
            String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return phoneService.getAllByCreatedAfter(LocalDateTime.parse(dateTime, formatter))
                .stream()
                .map(phone -> phoneMapper.toDto(phone))
                .collect(Collectors.toList());
    }
}
