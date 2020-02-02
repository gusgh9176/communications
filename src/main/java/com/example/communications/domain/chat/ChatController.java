package com.example.communications.domain.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
	private final SimpMessagingTemplate template = null;
	
	@MessageMapping("/chat/join")
	@SendTo("/topic/chat")
	public ChatMessage join(ChatMessage message) {
		return new ChatMessage("System", message.getName() + "님이 입장하셨습니다.");
	}
	
	
	/*
	@MessageMapping("/chat/join")	//클라이언트가 send하는 경로
    public void join(ChatMessage message) {
		message.setMessage(message.getName() + "님이 입장하셨습니다.");
		message.setName("System");
        template.convertAndSend("/topic/chat", message);
    }
	*/
	@MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/topic/chat", message);
    }
	
	
	@MessageMapping("/chat/whisper")
	public void whisper(WhisperMessage message) {
		template.convertAndSend("/queue/" + message.getReceiver(), new ChatMessage(message.getName(), message.getMessage()));
	}
	
}
