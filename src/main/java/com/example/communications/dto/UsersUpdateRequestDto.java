package com.example.communications.dto;

import com.example.communications.domain.users.Users;
import lombok.Builder;

public class UsersUpdateRequestDto {

    private String name;
    private Integer victory;
    private Integer rank;
    private String results;

    @Builder
    public UsersUpdateRequestDto(String name, Integer victory, Integer rank, String results) {
        this.name = name;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }

    public Users toEntity(){
        return Users.builder()
                .name(name)
                .victory(victory)
                .rank(rank)
                .results(results)
                .build();
    }
}
