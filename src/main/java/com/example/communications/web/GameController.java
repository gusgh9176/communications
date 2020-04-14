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
	
	private String isWinner(int hash) {
		String user = super.gameManagers.get(hash).getUserInfo().getUser();
		if(super.gameManagers.get(hash).getVictory().get(user) >= 5) return user+user;	//완전 승리시 유저이름 2번 반복 후 반환
		else return null;
	}
	
	@GetMapping("/color")
	public DeferredResult<String> getColor(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		return deferredResult;
	}
	
	@GetMapping("/winner")
	public DeferredResult<String> getWinner(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		deferredResult.onCompletion(new Runnable() {
			@Override
			public void run() {
				gameRequests.remove(deferredResult);
			}
		});
		
		
		return deferredResult;
	}
	
	
	@PostMapping("/cardnum")	//클라에서 정보 받아오기
	public void postCardNum(@RequestParam UserInfo userInfo) {
		//GameManager game = super.gameManagers.get(userInfo.getGameHash());
		int gameHash = userInfo.getGameHash();
		
		if(super.gameManagers.get(gameHash).getUserInfo() == null) {
			super.gameManagers.get(gameHash).setUserInfo(userInfo);
			DeferredResult<String> deferredResult = gameRequests.get(userInfo.getGameHash());
			deferredResult.setResult(super.gameManagers.get(gameHash).printColor());
		}
		else {
			super.gameManagers.get(gameHash).fightCards(userInfo);
			DeferredResult<String> deferredResult = gameRequests.get(userInfo.getGameHash());
			deferredResult.setResult(super.gameManagers.get(gameHash).getUserInfo().getUser());
		}
	}
	
}
