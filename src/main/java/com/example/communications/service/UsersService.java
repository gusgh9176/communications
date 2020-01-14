package com.example.communications.service;


import com.example.communications.domain.users.Users;
import com.example.communications.domain.users.UsersRepository;
import com.example.communications.dto.UsersMainResponseDto;
import com.example.communications.dto.UsersSaveRequestDto;
import com.example.communications.dto.UsersUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Transactional
    public Long save(UsersSaveRequestDto dto){
        return usersRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<UsersMainResponseDto> findAllDesc() {
        return usersRepository.findAllDesc()
                .map(UsersMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Stream<Users> update(UsersUpdateRequestDto dto) { return usersRepository.update(dto.toEntity()); }
}
