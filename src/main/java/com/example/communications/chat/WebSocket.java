package com.example.communications.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.server.ServerEndpoint;

import com.example.communications.dto.users.UsersMainResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@ServerEndpoint("/websocket")
public class WebSocket {
	private static List<Session> sessionList = Collections.synchronizedList(new ArrayList<Session>());
	
	//클라이언트로부터 메시지 수신 시 실행
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		String username = (String)session.getUserProperties().get("username");	//session의 username 속성으로 초기화
		
		//username 프로퍼티가 존재하지 않을 시, json객체를 UsersMainResponseDto객체로 맵핑하여 getName() 메서드 사용
		if(username == null) {
			ObjectMapper mapper = new ObjectMapper();
			UsersMainResponseDto userdto = mapper.readValue(message, UsersMainResponseDto.class);
			session.getUserProperties().put("username", userdto.getName());
		}
		
		//sessionList의 전체 session에게 메시지 전송
		Iterator<Session> iterator = sessionList.iterator();
		while(iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(buildJson(username, message));	
		}
		
	}
	
	//클라이언트의 웹소켓 연결시 세션 등록
	@OnOpen
	public void onOpen(Session session) {
		sessionList.add(session);
	}
	
	//웹소켓 연결종료시 세션 삭제
	@OnClose
	public void onClose(Session session) {
		sessionList.remove(session);
	}
	
	
	//Jackson을 이용해 json 문자열 반환
	public String buildJson(String username, String message) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		String json = "";
		
		map.put("username", username);
		map.put("message", message);
		
		try {
			json = mapper.writeValueAsString(map);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
}
