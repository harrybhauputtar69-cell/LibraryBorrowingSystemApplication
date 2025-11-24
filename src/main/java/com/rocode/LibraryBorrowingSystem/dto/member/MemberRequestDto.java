package com.rocode.LibraryBorrowingSystem.dto.member;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequestDto {
    @NotBlank(message = "name required")
    private String name;

    @NotBlank(message = "email required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "phone no is required required")
    private String phone;
}
