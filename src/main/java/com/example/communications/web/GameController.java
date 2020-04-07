package com.example.communications.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.communications.domain.game.GameManager;
import com.example.communications.domain.game.UserInfo;

@RestController
@RequestMapping("/game")
public class GameController {
	
	private final Map<Integer, GameManager> gameManagers = new ConcurrentHashMap<Integer, GameManager>();
	private final Map<Integer, DeferredResult<String>> gameRequests = new ConcurrentHashMap<Integer, DeferredResult<String>>();
	
	@GetMapping
	public DeferredResult<String> getCardColor(@RequestParam int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		return deferredResult;
	}
	
	@PostMapping	//클라에서 정보 받아오기
	public void postCardNum(@RequestParam UserInfo userInfo) {
		GameManager game = gameManagers.get(userInfo.getGameHash());
		
		if(game.getUserInfo() == null) {
			game.setUserInfo(userInfo);
		}
		else {
			game.fightCards(userInfo);
		}
	}
	
}
