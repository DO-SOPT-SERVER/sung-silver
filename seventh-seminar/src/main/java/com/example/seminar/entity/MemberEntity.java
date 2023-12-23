package com.example.seminar.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "member")
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private int age;

    @Builder
    public MemberEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
