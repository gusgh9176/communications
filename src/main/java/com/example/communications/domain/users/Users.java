package com.example.communications.domain.users;


import com.example.communications.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer victory;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private String results;

    @Builder
    public Users(String name, Integer victory, Integer rank, String results) {
        this.name = name;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }
}
