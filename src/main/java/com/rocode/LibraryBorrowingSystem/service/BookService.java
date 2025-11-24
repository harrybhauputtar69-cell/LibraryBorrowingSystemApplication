package com.rocode.LibraryBorrowingSystem.service;

import com.rocode.LibraryBorrowingSystem.dto.book.BookRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.book.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto createBook(BookRequestDto requestDto);
    BookResponseDto getBookById(Long id);
    List<BookResponseDto> getAllBook();
    BookResponseDto updateBook(Long id, BookRequestDto requestDto);
    void deleteBook(Long id);
}
