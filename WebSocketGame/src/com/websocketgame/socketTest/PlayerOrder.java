package com.websocketgame.socketTest;

import java.io.Serializable;

public class PlayerOrder implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private int areaWhereOrderIsPlace;
	private int orderType;
	
	public int getAreaWhereOrderIsPlace() {
		return areaWhereOrderIsPlace;
	}
	public void setAreaWhereOrderIsPlace(int areaWhereOrderIsPlace) {
		this.areaWhereOrderIsPlace = areaWhereOrderIsPlace;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

}
