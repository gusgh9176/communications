package com.example.communications.domain;

import com.example.communications.domain.users.Users;
import com.example.communications.domain.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        usersRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        usersRepository.save(Users.builder()
                .id((long)10)
                .name("test-id")
                .password("255")
                .build());

        //when
        List<Users> materialsList = usersRepository.findAll();

        //then
        Users users = materialsList.get(0);
        assertThat(users.getName(), is("test-id"));
        assertThat(users.getPassword(), is("255"));
    }

    @Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        usersRepository.save(Users.builder()
                .id((long)10)
                .name("test-id")
                .password("255")
                .build());
        //when
        List<Users> materialsList = usersRepository.findAll();

        //then
        Users users = materialsList.get(0);
        assertTrue(users.getSignDate().isAfter(now));
        assertTrue(users.getModifiedDate().isAfter(now));
    }

}
