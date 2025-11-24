package com.rocode.LibraryBorrowingSystem.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}
