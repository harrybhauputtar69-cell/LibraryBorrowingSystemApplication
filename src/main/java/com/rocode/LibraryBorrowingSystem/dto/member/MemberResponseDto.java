package com.rocode.LibraryBorrowingSystem.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
