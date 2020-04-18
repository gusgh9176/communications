package com.example.communications.domain.game;

import com.example.communications.domain.users.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
	private String user;
	private int selectCard;
	private int gameHash;
}
