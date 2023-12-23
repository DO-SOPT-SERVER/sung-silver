package com.server.dosopt.seminar.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonTest {
    @GetMapping("/test/person")
    public ResponseEntity<String> testPerson() {

        Person person = Person.builder()
                .lastName("Kim")
                .firstName("SungEun")
                .build();
        return ResponseEntity.ok(person.toString());
    }
}
