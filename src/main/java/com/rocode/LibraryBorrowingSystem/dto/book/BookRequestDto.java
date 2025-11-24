package com.rocode.LibraryBorrowingSystem.dto.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookRequestDto {

    @NotBlank(message = "title required")
    private String title;

    private String author;

    @NotBlank(message = "isbn required")
    private String isbn;
}
