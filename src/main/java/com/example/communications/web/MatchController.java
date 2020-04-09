package com.example.communications.web;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.communications.domain.game.GameManager;
import com.example.communications.domain.users.Users;

public class MatchController {
	protected final Map<Integer, GameManager> gameManagers = new ConcurrentHashMap<Integer, GameManager>();
	private Queue<String> matchQueue = new LinkedList<String>();
	
	@GetMapping
	public int getGameHash(@RequestParam String id) {
		this.matchQueue.add(id);
		
		return 0;
	}
}
