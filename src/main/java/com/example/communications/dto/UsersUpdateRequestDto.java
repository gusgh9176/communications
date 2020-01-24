package com.example.communications.dto;

import com.example.communications.domain.users.Users;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsersUpdateRequestDto {

    private String id;
    private String name;
    private Integer victory;
    private Integer rank;
    private String results;

    @Builder
    public UsersUpdateRequestDto(String id, String name, Integer victory, Integer rank, String results) {
        this.id = id;
        this.name = name;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }

    public Users toEntity(){
        return Users.builder()
                .id(id)
                .name(name)
                .victory(victory)
                .rank(rank)
                .results(results)
                .build();
    }
}
