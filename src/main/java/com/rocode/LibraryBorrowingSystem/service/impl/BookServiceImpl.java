package com.rocode.LibraryBorrowingSystem.service.impl;

import com.rocode.LibraryBorrowingSystem.dto.book.BookRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.book.BookResponseDto;
import com.rocode.LibraryBorrowingSystem.entity.Book;
import com.rocode.LibraryBorrowingSystem.exception.ResourceNotFoundException;
import com.rocode.LibraryBorrowingSystem.repository.BookRepository;
import com.rocode.LibraryBorrowingSystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    @Override
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = modelMapper.map(requestDto,Book.class);
        Book save = bookRepository.save(book);
        return modelMapper.map(save,BookResponseDto.class);
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found at: " + id));
        return modelMapper.map(book,BookResponseDto.class);
    }

    @Override
    public List<BookResponseDto> getAllBook() {
        return bookRepository.findAll().stream().map(b -> modelMapper.map(b,BookResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found at: " + id));
        Book updatBook = Book.builder().id(book.getId()).title(requestDto.getTitle()).author(requestDto.getAuthor()).isbn(requestDto.getIsbn()).build();
        Book savedBook = bookRepository.save(updatBook);
        return modelMapper.map(savedBook,BookResponseDto.class);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found at: " + id));
        bookRepository.deleteById(id);
    }
}
