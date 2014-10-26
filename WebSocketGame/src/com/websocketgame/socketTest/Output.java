/*
package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Output implements Runnable{

	DataOutputStream out;

	public Output(DataOutputStream out){
		this.out = out;
	}

	public void run() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			
			PlayerMessage message = new PlayerMessage();
			PlayerOrder order1 = new PlayerOrder();
			PlayerOrder order2 = new PlayerOrder();
			System.out.print("Enter an order area (int) : ");
			order1.setAreaWhereOrderIsPlace(sc.nextInt());
			System.out.print("Enter an order type (int) : ");
			order1.setOrderType(sc.nextInt());
			System.out.print("Enter another order area (int) : ");
			order2.setAreaWhereOrderIsPlace(sc.nextInt());
			System.out.print("Enter another order type (int) : ");
			order2.setOrderType(sc.nextInt());		
			System.out.print("Enter player id (int) : ");
			message.setPlayerId(sc.nextInt());
			System.out.print("Enter chat line (String) : ");
			message.setChat(sc.next()); // no spaces
			message.setOrders(new PlayerOrder[]{order1, order2});
			

		}
	}
}

*/