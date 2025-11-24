package com.rocode.LibraryBorrowingSystem.service.impl;

import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowResponseDto;
import com.rocode.LibraryBorrowingSystem.entity.Book;
import com.rocode.LibraryBorrowingSystem.entity.BorrowRecord;
import com.rocode.LibraryBorrowingSystem.entity.Member;
import com.rocode.LibraryBorrowingSystem.exception.ResourceNotFoundException;
import com.rocode.LibraryBorrowingSystem.repository.BookRepository;
import com.rocode.LibraryBorrowingSystem.repository.BorrowRecordRepository;
import com.rocode.LibraryBorrowingSystem.repository.MemberRepository;
import com.rocode.LibraryBorrowingSystem.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public BorrowResponseDto borrowBook(BorrowRequestDto dto) {
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(()->new ResourceNotFoundException("Resource not found at: " + dto.getBookId()));
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(()-> new ResourceNotFoundException("Resource not found at: " + dto.getMemberId()));

        BorrowRecord borrowRecord = modelMapper.map(dto,BorrowRecord.class);
        borrowRecord.setBook(book);
        borrowRecord.setMember(member);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setReturnDate(null);
        borrowRecord.setStatus(BorrowRecord.BorrowStatus.BORROWED);

        BorrowRecord saved = borrowRecordRepository.save(borrowRecord);

        BorrowResponseDto resp = modelMapper.map(saved, BorrowResponseDto.class);
        resp.setBookId(saved.getBook().getId());
        resp.setMemberId(saved.getMember().getId());
        resp.setStatus(saved.getStatus().name());

        return resp;
    }

    @Override
    public BorrowResponseDto returnBook(Long id) {
        BorrowRecord returnBook = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found: " + id));
        if (returnBook.getReturnDate() != null) {
            throw new IllegalStateException("Already returned");
        }
        returnBook.setReturnDate(LocalDate.now());
        returnBook.setStatus(BorrowRecord.BorrowStatus.RETURNED);

        BorrowRecord saved = borrowRecordRepository.save(returnBook);

        BorrowResponseDto resp = modelMapper.map(saved, BorrowResponseDto.class);
        resp.setBookId(saved.getBook().getId());
        resp.setMemberId(saved.getMember().getId());
        resp.setStatus(saved.getStatus().name());
        return resp;
    }

    @Override
    public List<BorrowResponseDto> getBorrowRecordsByMember(Long memberId) {
        return borrowRecordRepository.findByMemberId(memberId).stream().map(borrowRecord -> {
            BorrowResponseDto responseDto = modelMapper.map(borrowRecord, BorrowResponseDto.class);
            responseDto.setBookId(borrowRecord.getBook().getId());
            responseDto.setMemberId(borrowRecord.getMember().getId());
            responseDto.setStatus(borrowRecord.getStatus().name());
            return responseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BorrowResponseDto> getBorrowRecordsByBook(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId).stream().map(borrowRecord -> {
            BorrowResponseDto responseDto = modelMapper.map(borrowRecord,BorrowResponseDto.class);
            responseDto.setBookId(borrowRecord.getBook().getId());
            responseDto.setMemberId(borrowRecord.getMember().getId());
            responseDto.setStatus(borrowRecord.getStatus().name());
            return responseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BorrowResponseDto> getAllBorrowRecords() {
        return borrowRecordRepository.findAll().stream().map(borrowRecord -> {
            BorrowResponseDto responseDto = modelMapper.map(borrowRecord,BorrowResponseDto.class);
            responseDto.setBookId(borrowRecord.getBook().getId());
            responseDto.setMemberId(borrowRecord.getMember().getId());
            responseDto.setStatus(borrowRecord.getStatus().name());
            return responseDto;
        }).collect(Collectors.toList());
    }
}
