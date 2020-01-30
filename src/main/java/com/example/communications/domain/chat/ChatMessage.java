package com.example.communications.domain.chat;

import com.example.communications.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
	private Users user;
	private String content;
	
	
	@Builder
    public ChatMessage(Users user, String content) {
        this.user = user;
        this.content = content;
    }
}
