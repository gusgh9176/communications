package com.example.communications.domain.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
	@Autowired
	private final SimpMessagingTemplate template;
	
	@MessageMapping("/chat/join")	//클라이언트가 send하는 경로
    public void join(ChatMessage message) {
        message.setContent(message.getUser().getName() + "님이 입장하셨습니다.");
        template.convertAndSend("/topic/chat", message);
    }
	
	@MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/topic/chat", message);
    }
	
	@MessageMapping("/chat/whisper")
	public void whisper(ChatMessage message) {
		template.convertAndSend("/queue/" + message.getReceiver(), message);	//TODO ChatMessage클래스에 <Optional>receiver 구현
	}
}
