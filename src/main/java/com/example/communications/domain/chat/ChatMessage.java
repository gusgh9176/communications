package com.example.communications.domain.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
	private String name;
	private String message;
	
	
	@Builder
    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
