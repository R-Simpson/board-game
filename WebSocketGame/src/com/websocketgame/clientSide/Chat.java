package com.websocketgame.clientSide;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chat extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		final TextField textField = new TextField();

		Button sendButton = new Button();
		sendButton.setText("Send");
		sendButton.setDefaultButton(true);  
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(textField,sendButton);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFocusTraversable(false);
		textArea.setWrapText(true);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(textArea, hbox);
		
		StackPane stackPane = new StackPane();

		sendButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textArea.appendText(textField.getText() + "\n");
				textField.clear();
			}
		});
		


		stackPane.getChildren().add(vbox);
		stage.setScene(new Scene(stackPane));
		stage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
