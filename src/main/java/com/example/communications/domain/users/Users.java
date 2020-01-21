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

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer victory;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private String results;

    // null 이면 0 값 or 빈 스트링 값 넣어서 insert
    @PrePersist
    public void prePersist() {
        this.victory = ((this.victory == null) ? 0 : this.victory);
        this.rank = ((this.rank == null) ? 0 : this.rank);
        this.results = ((this.results == null) ? "" : this.results);
    }

    @Builder
    public Users(Long id, String name, String password, Integer victory, Integer rank, String results) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }
}
