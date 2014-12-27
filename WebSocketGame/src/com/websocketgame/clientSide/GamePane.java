package com.websocketgame.clientSide;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;
import com.websocketgame.shared.Unit;

public class GamePane {

	private Pane pane;
	private ScrollPane scrollPane;
	private Slider slider;
	private Client client;
	
	public GamePane(Client client)
	{
		this.client = client;
		
		pane = new Pane();
		Image image = new Image("file:res/got.jpg");
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		pane.getChildren().add(iv1);

		for(Land land: Game.INSTANCE.getGameState())
		{	
			pane.getChildren().add(land.getPolygon());

			land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {

					if (client.getSelectedUnit() != null)
					{
						try {
							PlayerMessage message = new PlayerMessage(client.playerid, land.getLandId());					
							client.out.writeObject(message);
							
							client.updateDebug("Send message from client " + client.playerid + " to claim " + land.getLandId() 
									+ ", centroid: " + land.getCentroid()[0] + "," + land.getCentroid()[1] );

						} catch (IOException e1) {
							client.updateDebug("Unable to send message to server");
						}
					}
					else
					{
						client.updateChat("GAME: Select a unit to move first");
					}

				}
			});
		}

		pane.setStyle("-fx-background-color: teal;");

		slider = new Slider(0.36,1,0.36);
		ZoomPane zoomPane = new ZoomPane(pane);
		zoomPane.zoomFactorProperty().bind(slider.valueProperty());

		scrollPane = new ScrollPane(zoomPane);				
		scrollPane.setMinWidth(540);
	}
	
	public ScrollPane getGamePane()
	{
		return scrollPane;
	}
	
	public Slider getSlider()
	{
		return slider;
	}
	
	void refreshDisplay()	// package private
	{
		// Better than before, but need to remove old unit / shape?
		System.out.println("Refreshing Display");
		for(Land land: Game.INSTANCE.getGameState())
		{					
			if (land.getUnit() != null)
			{
				if (!pane.getChildren().contains(land.getUnit().getShape()))
				{
					client.updateDebug("Adding a unit for land " + land.getLandId());
					
					Unit unit = land.getUnit();
					Shape shape = unit.getShape();
					
					pane.getChildren().add(shape);
					
					shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
						public void handle(MouseEvent e) {
							if (shape instanceof Circle)
							{
								if (((Circle) shape).getRadius() == 20.0)
								{
									((Circle) shape).setRadius(10.0);
									client.deselectUnit(unit);
								}
								else
								{
									((Circle) shape).setRadius(20.0); 
									client.selectUnit(unit);
								}
							}
							client.updateDebug("UNIT CLICKED");
						}
					});	
				}
			}
		}
	}
	
}
