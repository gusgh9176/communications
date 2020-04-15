package com.example.communications.domain.game;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager {
	protected UserInfo userInfo = null;
	protected Map<String, Integer> victory = new HashMap<String, Integer>();

	public void loadUser(String name) {
		
	}

	public String printColor() {
		if(this.userInfo.getSelectCard()%2 == 0) return "red";
		else return "blue";
	}
	
	public void fightCards(UserInfo newInfo) {
		if(this.userInfo.getSelectCard() < newInfo.getSelectCard()) {
			this.userInfo = newInfo;
			int vicNum = this.victory.get(newInfo.getUser());
			this.victory.put(newInfo.getUser(), vicNum+1);
		}
		else {
			int vicNum = this.victory.get(this.userInfo.getUser());
			this.victory.put(this.userInfo.getUser(), vicNum+1);
		}
	}
	
	public void saveUser() {
		
	}
}
