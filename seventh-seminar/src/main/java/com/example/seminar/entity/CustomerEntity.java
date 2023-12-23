package com.example.seminar.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(nullable = false)
    private String nickname;

    private int age;

    @Builder
    public CustomerEntity(String name, String nickname, int age) {
        validateAge(age);
        validateName(name);
        validateNickname(nickname);
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }

    private void validateNickname(String nickname) {
        Assert.notNull(nickname, "닉네임은 널이면 안됩니다.");
    }

    private void validateName(String name) {
        Assert.notNull(name, "이름은 널이면 안됩니다.");
    }

    private void validateAge(int age) {
        Assert.isTrue(age > 0 && age < 200 , "나이는 1살부터 200살 사이로 설정해야합니다.");
    }
}
