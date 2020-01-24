package com.example.communications.domain.pvps;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Pvps {

    @Id
    private String id;

    private String name;
    private Integer victory;
    private Integer rank;
    private String results;

    @Builder
    public Pvps(String id, String name, Integer victory, Integer rank, String results) {
        this.id = id;
        this.name = name;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }
}
