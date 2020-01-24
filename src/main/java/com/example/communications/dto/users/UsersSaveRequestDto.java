package com.example.communications.dto.users;



import com.example.communications.domain.users.Users;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsersSaveRequestDto {

    private String id;
    private String name;
    private String password;

    @Builder
    public UsersSaveRequestDto(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Users toEntity(){
        return Users.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
    }
}
