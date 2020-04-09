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
public class GameController extends MatchController {
	
	//private final Map<Integer, GameManager> gameManagers = new ConcurrentHashMap<Integer, GameManager>();
	private final Map<Integer, DeferredResult<String>> gameRequests = new ConcurrentHashMap<Integer, DeferredResult<String>>();
	
	@GetMapping
	public DeferredResult<String> getColor(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		return deferredResult;
	}
	
	@GetMapping
	public DeferredResult<String> getWinner(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		return deferredResult;
	}
	
	
	@PostMapping	//클라에서 정보 받아오기
	public void postCardNum(@RequestParam UserInfo userInfo) {
		//GameManager game = super.gameManagers.get(userInfo.getGameHash());
		
		if(((GameManager) super.gameManagers).getUserInfo() == null) {
			((GameManager) super.gameManagers).setUserInfo(userInfo);
			DeferredResult<String> deferredResult = gameRequests.get(userInfo.getGameHash());
			deferredResult.setResult(((GameManager) super.gameManagers).printColor());
		}
		else {
			((GameManager) super.gameManagers).fightCards(userInfo);
			DeferredResult<String> deferredResult = gameRequests.get(userInfo.getGameHash());
			deferredResult.setResult(((GameManager) super.gameManagers).getUserInfo().getUser());
		}
	}
	
}
