package com.example.communications.domain.users;


import com.example.communications.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Users extends BaseTimeEntity {

    // 한번 정해지면 불변의 값은 id, name
    @Id
    private String id;

    private String name;

    private String password;

    private Integer victory;

    private Integer rank;

    private String results;

    @Builder
    public Users(String id, String name, String password, Integer victory, Integer rank, String results) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.victory = victory;
        this.rank = rank;
        this.results = results;
    }
}
