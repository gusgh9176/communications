package com.example.communications.service;

import com.example.communications.domain.pvps.Pvps;
import com.example.communications.domain.pvps.PvpsRepository;
import com.example.communications.domain.users.Users;
import com.example.communications.domain.users.UsersRepository;
import com.example.communications.dto.pvps.PvpsSaveRequestDto;
import com.example.communications.dto.users.UsersSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PvpsServiceTest {
    @Autowired
    private PvpsService pvpsService;

    @Autowired
    private PvpsRepository pvpsRepository;

    @After
    public void cleanup () {
        pvpsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_pvps테이블에_저장된다 () {
        //given
        PvpsSaveRequestDto dto = PvpsSaveRequestDto.builder()
                .name("test_name")
                .victory(1)
                .build();

        //when
        pvpsService.save(dto);

        //then
        Pvps materials = pvpsRepository.findAll().get(0);
        assertThat(materials.getName()).isEqualTo(dto.getName());
        assertThat(materials.getVictory()).isEqualTo(dto.getVictory());
    }

    @Test
    public void Dto데이터가_pvps테이블에_업데이트된다 () {
        //given
        PvpsSaveRequestDto dto = PvpsSaveRequestDto.builder()
                .name("test_name")
                .victory(1)
                .build();

        PvpsSaveRequestDto updateDto = PvpsSaveRequestDto.builder()
                .name("test_name")
                .rank(1)
                .victory(2)
                .results("승")
                .build();

        //when
//        pvpsService.save(dto);
        pvpsService.update(updateDto);

        //then
        Pvps materials = pvpsRepository.findAll().get(0);
        assertThat(materials.getName()).isEqualTo(updateDto.getName());
        assertThat(materials.getRank()).isEqualTo(updateDto.getRank());
        assertThat(materials.getVictory()).isEqualTo(updateDto.getVictory());
    }
}
