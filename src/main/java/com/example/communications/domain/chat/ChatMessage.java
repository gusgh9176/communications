package com.example.communications.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessage {
	private String name;
	private String message;
	
	public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
