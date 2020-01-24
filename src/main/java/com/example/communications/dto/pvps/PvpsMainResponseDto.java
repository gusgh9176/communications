package com.example.communications.dto.pvps;

import com.example.communications.domain.pvps.Pvps;
import lombok.Getter;

@Getter
public class PvpsMainResponseDto {

    private String name;
    private Integer victory;
    private Integer rank;
    private String results;

    public PvpsMainResponseDto(Pvps entity) {
        name = entity.getName();
        victory = entity.getVictory();
        rank = entity.getRank();
        results = entity.getResults();
    }

}
