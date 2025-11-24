package com.rocode.LibraryBorrowingSystem.service.impl;

import com.rocode.LibraryBorrowingSystem.dto.member.MemberRequestDto;
import com.rocode.LibraryBorrowingSystem.dto.member.MemberResponseDto;
import com.rocode.LibraryBorrowingSystem.entity.Member;
import com.rocode.LibraryBorrowingSystem.exception.ResourceNotFoundException;
import com.rocode.LibraryBorrowingSystem.repository.MemberRepository;
import com.rocode.LibraryBorrowingSystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        Member member = modelMapper.map(requestDto,Member.class);
        Member save = memberRepository.save(member);
        return modelMapper.map(save,MemberResponseDto.class);
    }

    @Override
    public MemberResponseDto getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found at: " + id));
        return modelMapper.map(member,MemberResponseDto.class);
    }

    @Override
    public List<MemberResponseDto> getAllMember() {
        return memberRepository.findAll().stream().map(m-> modelMapper.map(m,MemberResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto updateMember(Long id, MemberRequestDto requestDto) {
        Member existingMember = memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found at:" + id));
        Member updateMember = Member.builder().id(existingMember.getId()).name(requestDto.getName()).email(requestDto.getEmail()).phone(requestDto.getPhone()).build();
        Member save = memberRepository.save(updateMember);
        return modelMapper.map(save,MemberResponseDto.class);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found at: " + id));
        memberRepository.deleteById(id);
    }
}
