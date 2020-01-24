package com.example.communications.dto.pvps;

import com.example.communications.domain.pvps.Pvps;
import com.example.communications.domain.users.Users;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PvpsSaveRequestDto {

    private String id;
    private String name;
    private Integer victory;
    private Integer rank;
    private String results;


    @Builder
    public PvpsSaveRequestDto(String id, String name, Integer victory, Integer rank, String results) {
        this.id = id;
        this.name = name;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }

    public Pvps toEntity(){
        return Pvps.builder()
                .id(id)
                .name(name)
                .victory(victory)
                .rank(rank)
                .results(results)
                .build();
    }
}
