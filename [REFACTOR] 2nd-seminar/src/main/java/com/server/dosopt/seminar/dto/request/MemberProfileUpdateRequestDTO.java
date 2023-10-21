package com.server.dosopt.seminar.dto.request;

import com.server.dosopt.seminar.domain.Part;
import lombok.Data;

@Data
public class MemberProfileUpdateRequestDTO {
    private int generation;
    private Part part;
}
