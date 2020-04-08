package com.example.communications.domain.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager {
	protected UserInfo userInfo = null;

	public void loadUser(String name) {
		
	}

	public String printColor() {
		if(this.userInfo.getSelectCard()%2 == 0) return "red";
		else return "blue";
	}
	
	public void fightCards(UserInfo newInfo) {
		if(this.userInfo.getSelectCard() < newInfo.getSelectCard()) {
			this.userInfo = newInfo;
		}
	}
	
	public void saveUser() {
		
	}
}
