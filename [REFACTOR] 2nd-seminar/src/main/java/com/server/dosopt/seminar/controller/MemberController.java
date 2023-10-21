package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.common.dto.ApiResponseDTO;
import com.server.dosopt.seminar.dto.ErrorType.SuccessType;
import com.server.dosopt.seminar.dto.request.MemberCreateRequestDTO;
import com.server.dosopt.seminar.dto.request.MemberProfileUpdateRequestDTO;
import com.server.dosopt.seminar.dto.response.MemberCreateResponseDTO;
import com.server.dosopt.seminar.dto.response.MemberGetResponseDTO;
import com.server.dosopt.seminar.dto.response.MemberProfileUpdateResponseDTO;
import com.server.dosopt.seminar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 단건 조회
    @GetMapping(value = "/{memberId}")
    public ApiResponseDTO<MemberGetResponseDTO> getMemberProfileV2(@PathVariable Long memberId) {
        return ApiResponseDTO.success(SuccessType.GET_MEMBER_INFO, memberService.getMemberById(memberId));
    }

    // 생성
    @PostMapping
    public ApiResponseDTO<MemberCreateResponseDTO> createMember(@RequestBody MemberCreateRequestDTO request) {
        return ApiResponseDTO.success(SuccessType.MEMBER_CREATE_SUCCESS, MemberCreateResponseDTO.of(memberService.create(request)));
    }

    // 목록 조회
    @GetMapping
    public ApiResponseDTO<List<MemberGetResponseDTO>> getMembersProfile() {
        return ApiResponseDTO.success(SuccessType.GET_ALL_MEMBER_INFO, memberService.getMembers());
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ApiResponseDTO<MemberProfileUpdateResponseDTO> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequestDTO request) {
        return ApiResponseDTO.success(SuccessType.UPDATE_MEMBER_INFO, memberService.updateSOPT(memberId, request));
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ApiResponseDTO deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponseDTO.success(SuccessType.DELETE_MEMBER);
    }
}
