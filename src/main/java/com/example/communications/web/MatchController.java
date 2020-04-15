package com.example.communications.web;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.communications.domain.game.GameManager;

public class MatchController {
	protected final Map<Integer, GameManager> gameManagers = new ConcurrentHashMap<Integer, GameManager>();
	private Queue<String> matchQueue = new LinkedList<String>();
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
	
	@GetMapping
	public int getGameHash(@RequestParam String id) {
		this.matchQueue.add(id);
		
		while(true) {
			if(matchQueue.peek() == id) {	//queue의 top에 있는지 검사
				if(this.isMatched == true) {
					this.setNewHash();
					this.isMatched = false;
					this.matchQueue.remove();
					return this.Hash;
				}
				else {
					this.isMatched = true;
					this.matchQueue.remove();
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
