package com.rocode.LibraryBorrowingSystem.dto.borrow;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BorrowResponseDto {
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}
