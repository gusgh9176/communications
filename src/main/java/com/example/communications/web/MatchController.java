package com.example.communications.web;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.communications.domain.game.GameManager;

@RestController
public class MatchController {
	protected final Map<Integer, GameManager> gameManagers = new ConcurrentHashMap<Integer, GameManager>();
	private Queue<String> matchQueue = new LinkedList<String>();
	private Set<String> matchSet = new LinkedHashSet<String>();			//matchQueue에 중복 등록되는지 체크
	private boolean isMatched = true;
	private int Hash;
	
	public void resetGame(int gameHash) {
		this.gameManagers.remove(gameHash);
	}
	
	private void setNewHash() {
		Object obj = new Object();
		this.Hash = obj.hashCode();
		obj = null;
	}
	
	@GetMapping("/game/match")
	public int getGameHash(@RequestParam("id") String id) {
		if(matchSet.add(id)) {		//set에 중복값 존재하지 않을 시 true반환
			this.matchQueue.add(id);
		}
		
		
		while(true) {
			if(matchQueue.peek() == id) {	//queue의 top에 있는지 검사
				if(this.isMatched == true) {
					this.setNewHash();
					this.isMatched = false;
					this.matchQueue.remove();
					this.matchSet.remove(id);
					return this.Hash;
				}
				else {
					this.isMatched = true;
					this.matchQueue.remove();
					this.matchSet.remove(id);
					this.gameManagers.put(this.Hash, new GameManager());
					return this.Hash;
				}
			}
			else {
				try {
					Thread.sleep(500);	//0.5초 후 while문 다시 실행
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
