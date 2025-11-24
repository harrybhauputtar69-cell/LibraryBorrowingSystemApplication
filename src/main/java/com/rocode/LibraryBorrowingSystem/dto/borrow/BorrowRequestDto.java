package com.rocode.LibraryBorrowingSystem.dto.borrow;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BorrowRequestDto {
    @NotNull(message = "bookId is required")
    private Long bookId;

    @NotNull(message = "memberId is required")
    private Long memberId;
}
