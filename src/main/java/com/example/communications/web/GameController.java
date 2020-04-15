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
	
	private boolean isWinner(int hash) {		//아무나 5선승 달성 시 true 반환
		String user = super.gameManagers.get(hash).getUserInfo().getUser();
		if(super.gameManagers.get(hash).getVictory().get(user) >= 5) return true;
		else return false;
	}
	
	@GetMapping("/color")		//후공 유저의 요청
	public DeferredResult<String> getColor(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		deferredResult.onCompletion(new Runnable() {	//요청 완료시 실행
			@Override
			public void run() {
				gameRequests.remove(deferredResult);
			}
		});
		
		return deferredResult;
	}
	
	@GetMapping("/winner")		//선공 유저의 요청
	public DeferredResult<String> getWinner(@RequestParam("hash") int hash) {
		
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		gameRequests.put(hash, deferredResult);
		
		deferredResult.onCompletion(new Runnable() {	//요청 완료시 실행
			@Override
			public void run() {
				gameRequests.remove(deferredResult);
			}
		});
		return deferredResult;
	}
	
	
	@PostMapping("/cardnum")		//클라에서 정보 받아오기
	public String postCardNum(@RequestParam UserInfo userInfo) {
		int gameHash = userInfo.getGameHash();
		DeferredResult<String> deferredResult = gameRequests.get(gameHash);
		
		if(super.gameManagers.get(gameHash).getUserInfo() == null) {		//선공 user의 request 발생 시 실행
			super.gameManagers.get(gameHash).setUserInfo(userInfo);
			deferredResult.setResult(super.gameManagers.get(gameHash).printColor());
			return "";
		}
		else {		//후공 user의 request 발생 시 실행
			super.gameManagers.get(gameHash).fightCards(userInfo);
			if(this.isWinner(gameHash)) {		//5선승 달성 시 실행
				deferredResult.setResult("Game End");
				super.resetGame(gameHash);
			}
			else {
				deferredResult.setResult(super.gameManagers.get(gameHash).getUserInfo().getUser());
			}
			return (String) deferredResult.getResult();
		}
	}
	
}
