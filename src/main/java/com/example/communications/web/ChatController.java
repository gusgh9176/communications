package com.example.communications.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.communications.domain.chat.ChatMessage;
import com.example.communications.domain.chat.WhisperMessage;


@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/join")	//클라이언트가 send하는 경로
    public void join(ChatMessage message) {
        template.convertAndSend("/topic/chat", new ChatMessage("System", message.getName() + "님이 입장했습니다."));
    }
	
	@MessageMapping("/chat")
    public void message(ChatMessage message) {
        this.template.convertAndSend("/topic/chat", message);
    }

	@MessageMapping("/whisper")
	public void whisper(WhisperMessage message) {
		template.convertAndSend("/queue/" + message.getReceiver(), new ChatMessage(message.getName(), message.getMessage()));
	}
	
}
