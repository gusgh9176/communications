package com.example.communications.service;

import com.example.communications.domain.users.Users;
import com.example.communications.domain.users.UsersRepository;
import com.example.communications.dto.UsersSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @After
    public void cleanup () {
        usersRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_uesrs테이블에_저장된다 () {
        //given
        UsersSaveRequestDto dto = UsersSaveRequestDto.builder()
                .id((long)1)
                .name("test_name")
                .password("test_password")
                .build();

        //when
        usersService.joinUser(dto);

        //then
        Users materials = usersRepository.findAll().get(0);
        assertThat(materials.getId()).isEqualTo(dto.getId());
        assertThat(materials.getName()).isEqualTo(dto.getName());
        assertThat(materials.getPassword()).isEqualTo(dto.getPassword());
    }
}
