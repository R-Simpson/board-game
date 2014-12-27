package com.websocketgame.clientSide;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ChatPane {
	
	private StackPane stackPane;
	private TextField textField;
	private TextArea textArea;
	private boolean debug = false;
	
	public ChatPane()
	{
		
	stackPane = new StackPane();
	textField = new TextField();
	textArea = new TextArea();
	textArea.setEditable(false);
	textArea.setFocusTraversable(false);
	textArea.setWrapText(true);
	textArea.setPrefHeight(1000);

	Button sendButton = new Button();
	sendButton.setText("Send");
	sendButton.setDefaultButton(true);  
	
	HBox hbox = new HBox();
	hbox.getChildren().addAll(textField,sendButton);
    hbox.setHgrow(textField, Priority.ALWAYS);

	VBox vbox = new VBox();
	vbox.getChildren().addAll(textArea, hbox);
		
	sendButton.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent arg0) {

			if (textField.getLength() > 0)
			{
				textArea.appendText(textField.getText() + "\n");
				textField.clear();
			}
		}
	});
	
	stackPane.getChildren().add(vbox);		
	}
	
	public StackPane getChatPane()
	{
		return stackPane;
	}
	
	public void writeToChatPane(String text)
	{
		textArea.appendText(text + "\n");
	}
	
	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
	
	public void writeDebug(String string)
	{
		if (debug)
		{
			textArea.appendText("DEBUG: " + string + "\n");
		}
	}	
}
