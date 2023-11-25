package com.server.dosopt.seminar.sample;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor // 모든 매개변수를 받는 생성자를 만들어준다
//@NoArgsConstructor // 아무것도 받지 않는 생성자
//@RequiredArgsConstructor // 찾아보세요
public class Sopt {
    private String part;
    private int age;
    private boolean isOK;
    private String name;
    private String filed1;
    private String filed2;
    private String filed3;
}
