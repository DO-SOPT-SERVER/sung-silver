package com.example.seminar.service;

import com.example.seminar.dto.CustomerRequest;
import com.example.seminar.entity.CustomerEntity;
import com.example.seminar.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerJpaRepository customerJpaRepository;

    @Transactional
    public void create(CustomerRequest request) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(request.name())
                .age(request.age())
                .nickname(request.nickname())
                .build();
        System.out.println(customer);
        customerJpaRepository.save(customer);
    }
}
