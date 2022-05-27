package com.erhan.onlineticket.model;

public enum Gender {
	ERKEK,
	KADIN;	
	
	public static Gender getRandom() {
		int randomNumber = 0 + (int) Math.round(Math.random() * (1 - 0));
		return values()[randomNumber];
	}
}
