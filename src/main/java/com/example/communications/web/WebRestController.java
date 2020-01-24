package com.example.communications.web;

import com.example.communications.domain.users.Users;
import com.example.communications.dto.UsersSaveRequestDto;
import com.example.communications.dto.UsersUpdateRequestDto;
import com.example.communications.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class WebRestController {

    private UsersService usersService;

    @GetMapping("hello")
    public String hello() {
        return "HelloWorld";
    }



    @PostMapping("update")
    public String updateUsers(@RequestBody UsersUpdateRequestDto dto){
        return usersService.update(dto);
    }
}

