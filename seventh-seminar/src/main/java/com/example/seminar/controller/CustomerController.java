package com.example.seminar.controller;


import com.example.seminar.dto.CustomerRequest;
import com.example.seminar.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerRequest request) {
        customerService.create(request);
        return ResponseEntity.ok().build();
    }
}
