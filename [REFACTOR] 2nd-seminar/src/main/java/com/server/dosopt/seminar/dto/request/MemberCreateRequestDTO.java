package com.server.dosopt.seminar.dto.request;

import com.server.dosopt.seminar.domain.SOPT;
import lombok.Data;

@Data
public class MemberCreateRequestDTO {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
