package com.example.communications.domain;

import com.example.communications.domain.pvps.Pvps;
import com.example.communications.domain.pvps.PvpsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PvpsRepositoryTest {

    @Autowired
    PvpsRepository pvpsRepository;

    @After
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/
        pvpsRepository.deleteAll();
    }

    @Test
    public void 유저저장_불러오기() {
        // id 값은 domain @ 설정으로 인해 1부터 차례로 증가
        //given
        pvpsRepository.save(Pvps.builder()
                .name("test-id")
                .victory(1)
                .build());

        //when
        List<Pvps> materialsList = pvpsRepository.findAll();

        //then
        Pvps users = materialsList.get(0);
        assertThat(users.getName(), is("test-id"));
        assertThat(users.getVictory(), is(1));
    }
}
