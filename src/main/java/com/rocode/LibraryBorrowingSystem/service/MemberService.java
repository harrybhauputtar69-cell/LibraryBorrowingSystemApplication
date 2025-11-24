package com.rocode.LibraryBorrowingSystem.service;

import com.rocode.LibraryBorrowingSystem.dto.member.MemberRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.member.MemberResponseDto;

import java.util.List;

public interface MemberService {
    MemberResponseDto createMember(MemberRequestDto requestDto);
    MemberResponseDto getMemberById(Long id);
    List<MemberResponseDto> getAllMember();
    MemberResponseDto updateMember(Long id, MemberRequestDto requestDto);
    void deleteMember(Long id);
}
