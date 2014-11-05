package com.websocketgame.socketTest;

import java.util.List;

import com.thoughtworks.xstream.XStream;

public class XMLBuilder {

	
	
	public String createXML()
	{
		StringBuilder xml = new StringBuilder();
		
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"? \n");
		
		xml.append("<playerId>" + "</playerId>")
		   .append("<order>" + "</order>" )
		   .append("<chat>" + "<chat>");
		
		String xmlAsString = xml.toString();
		
		return xmlAsString;
		
		
	}
	
	public List<PlayerMessage> parseXML(String xmlMessage)
	{
		XStream xstream = new XStream();
		
		xstream.alias("com.websocketgame.got.PlayerMessage", PlayerMessage.class);
		xstream.alias("com.websocketgame.got.PlayerOrder", PlayerOrder.class);
		
		List<PlayerMessage> playerMessage = (List<PlayerMessage>) xstream.fromXML(xmlMessage);
		
		// xstream.toXML(xmlMessage);

		return playerMessage;
		
		
	}
	
}
