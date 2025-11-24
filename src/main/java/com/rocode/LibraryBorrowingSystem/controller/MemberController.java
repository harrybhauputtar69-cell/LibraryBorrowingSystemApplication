package com.rocode.LibraryBorrowingSystem.controller;

import com.rocode.LibraryBorrowingSystem.dto.member.MemberRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.member.MemberResponseDto;
import com.rocode.LibraryBorrowingSystem.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<MemberResponseDto> creteMember(@Valid @RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(memberService.createMember(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers(){
        return  ResponseEntity.ok(memberService.getAllMember());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(memberService.updateMember(id,requestDto));
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "Member deleted successfully";
    }
}
