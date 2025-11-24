package com.rocode.LibraryBorrowingSystem.service;

import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowResponseDto;

import java.util.List;

public interface BorrowService {
    BorrowResponseDto borrowBook(BorrowRequestDto dto);
    BorrowResponseDto returnBook(Long id);
    List<BorrowResponseDto> getBorrowRecordsByMember(Long memberId);
    List<BorrowResponseDto> getBorrowRecordsByBook(Long bookId);
    List<BorrowResponseDto> getAllBorrowRecords();
}
