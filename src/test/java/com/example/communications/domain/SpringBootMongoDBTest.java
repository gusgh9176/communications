package com.example.communications.domain;

import com.example.communications.domain.users.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpringBootMongoDBTest {
    @Autowired()
    private UsersRepository projectMongoDBRepository;

    @Test
    public void printProjectData() {
        System.out.println(projectMongoDBRepository.findAll());
    }
}