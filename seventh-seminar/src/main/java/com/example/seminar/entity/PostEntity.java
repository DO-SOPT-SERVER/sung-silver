package com.example.seminar.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "post")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String content;

    @Builder
    public PostEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }
}