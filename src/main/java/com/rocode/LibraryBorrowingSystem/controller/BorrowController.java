package com.rocode.LibraryBorrowingSystem.controller;

import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowResponseDto;
import com.rocode.LibraryBorrowingSystem.service.BorrowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BorrowResponseDto> borrowBook(@Valid @RequestBody BorrowRequestDto requestDto){
        return ResponseEntity.status(201).body(borrowService.borrowBook(requestDto));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowResponseDto> returnBook(@PathVariable Long id){
        return ResponseEntity.ok(borrowService.returnBook(id));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowResponseDto>> getBorrowRecordByMember(@PathVariable Long memberId){
        return ResponseEntity.ok(borrowService.getBorrowRecordsByMember(memberId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowResponseDto>> getBorrowRecordByBook(@PathVariable Long bookId){
        return ResponseEntity.ok(borrowService.getBorrowRecordsByBook(bookId));
    }

    @GetMapping
    public ResponseEntity<List<BorrowResponseDto>> getAllBorrowRecords(){
        return ResponseEntity.ok(borrowService.getAllBorrowRecords());
    }
}
