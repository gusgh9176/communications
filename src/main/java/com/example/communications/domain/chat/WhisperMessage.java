package com.example.communications.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WhisperMessage extends ChatMessage {
	private String receiver;

	public WhisperMessage(String name, String message, String receiver) {
		super(name, message);
		this.receiver = receiver;
	}
}
