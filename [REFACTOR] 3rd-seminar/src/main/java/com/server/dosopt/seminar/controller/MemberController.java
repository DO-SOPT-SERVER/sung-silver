package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.common.response.Success;
import com.server.dosopt.seminar.dto.request.MemberCreateRequest;
import com.server.dosopt.seminar.dto.request.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.dto.response.MemberGetResponse;
import com.server.dosopt.seminar.dto.response.UriResponse;
import com.server.dosopt.seminar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ApiResponse.success(Success.GET_MEMBER_SUCCESS,memberService.getMemberByIdV2(memberId));
    }

    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ApiResponse.success(Success.GET_MEMBER_SUCCESS,memberService.getMemberByIdV2(memberId));
    }

    // 생성
    @PostMapping
    public ApiResponse<UriResponse> createMember(@RequestBody MemberCreateRequest request) {
        URI location =  URI.create(memberService.create(request));
        return ApiResponse.success(Success.SIGNUP_SUCCESS, UriResponse.of(location.toString()));
    }

    // 목록 조회
    @GetMapping
    public ApiResponse<List<MemberGetResponse>> getMembersProfile() {
        return ApiResponse.success(Success.GET_MEMBERS_SUCCESS,memberService.getMembers());
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ApiResponse updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ApiResponse.success(Success.UPDATE_MEMBER_SUCCESS);
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ApiResponse deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.success(Success.DELETE_MEMBER_SUCCESS);
    }
}
