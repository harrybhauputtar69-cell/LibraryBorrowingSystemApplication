package com.rocode.LibraryBorrowingSystem.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.typeMap(com.rocode.LibraryBorrowingSystem.dto.borrow.BorrowRequestDto.class,
                        com.rocode.LibraryBorrowingSystem.entity.BorrowRecord.class)
                .addMappings(m -> m.skip(com.rocode.LibraryBorrowingSystem.entity.BorrowRecord::setId));

        return mapper;
    }
}
