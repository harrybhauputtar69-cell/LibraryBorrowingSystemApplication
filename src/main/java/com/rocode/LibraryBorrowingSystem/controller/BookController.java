package com.rocode.LibraryBorrowingSystem.controller;

import com.rocode.LibraryBorrowingSystem.dto.book.BookRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.book.BookResponseDto;
import com.rocode.LibraryBorrowingSystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto requestDto){
        return ResponseEntity.ok(bookService.createBook(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto requestDto){
        return ResponseEntity.ok(bookService.updateBook(id,requestDto));
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }

}
