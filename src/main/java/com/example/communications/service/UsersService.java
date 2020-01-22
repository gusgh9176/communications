package com.example.communications.service;


import com.example.communications.domain.Role;
import com.example.communications.domain.users.Users;
import com.example.communications.domain.users.UsersRepository;
import com.example.communications.dto.UsersMainResponseDto;
import com.example.communications.dto.UsersSaveRequestDto;
import com.example.communications.dto.UsersUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UsersService implements UserDetailsService {
    private UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<UsersMainResponseDto> findAllDesc() {
        return usersRepository.findAllDesc()
                .map(UsersMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Stream<Users> update(UsersUpdateRequestDto dto) { return usersRepository.update(dto.toEntity()); }

    //Spring Security 관련
    @Transactional
    public Long joinUser(UsersSaveRequestDto usersSaveRequestDto) {
        // null이 아니라면 true 반환함, 중복 아이디 있다면 db에 추가 안함
        if(usersRepository.findByName(usersSaveRequestDto.getName()).isPresent()){
            System.out.println("현재 디비에 저장된 것"+usersRepository.findByName(usersSaveRequestDto.getName()));
            return (long)-1;
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usersSaveRequestDto.setPassword(passwordEncoder.encode(usersSaveRequestDto.getPassword()));

        return usersRepository.save(usersSaveRequestDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Users> userEntityWrapper = usersRepository.findByName(userEmail);
        if(!userEntityWrapper.isPresent()){
            return null;
        }
        Users userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getName(), userEntity.getPassword(), authorities);
    }
}
