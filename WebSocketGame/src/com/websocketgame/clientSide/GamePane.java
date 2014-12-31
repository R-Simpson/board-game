package com.websocketgame.clientSide;

import java.io.IOException;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import com.websocketgame.gamePieces.GamePieceFootman;
import com.websocketgame.gamePieces.GamePieceKnight;
import com.websocketgame.messaging.GameMessage;
import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;

public class GamePane {

	private Pane pane;
	private ScrollPane scrollPane;
	private Slider slider;
	private Client client;
	private Group unitGroup;

	public GamePane(Client client)
	{
		this.client = client;

		pane = new Pane();
		pane.setStyle("-fx-background-color: teal;");
		
		// Add gameboard map image
		Image image = new Image("file:res/got.jpg");
		ImageView iv1 = new ImageView();
		iv1.setImage(image);
		pane.getChildren().add(iv1);
		
		// Add polygon map areas overlay with event listener
		for(Land land: Game.INSTANCE.getGameState())
		{	
			pane.getChildren().add(land.getPolygon());

			land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					if (client.getSelectedUnit() != null)
					{
						// Should we do this on the server side? Would make client thinner but results should be same; as is there's only one map..
						boolean adjacent = false;				
						for (int adjacentLand : client.getSelectedUnit().getLand().getAdjacency())
						{
							if (adjacentLand == land.getLandId())
							{
								adjacent = true;
							}
						}
						if (adjacent)
						{
							try {
								GameMessage message = new GameMessage(client.getPlayerId(), client.getSelectedUnit(), land.getLandId());	
								client.out.writeObject(message);
								client.updateDebug("Selected unit at " + client.getSelectedUnit().getLand().getLandId());
								client.updateDebug("Send message from client " + client.getPlayerId() + " to claim " + land.getLandId() 
										+ ", centroid: " + land.getCentroid()[0] + "," + land.getCentroid()[1] );
								deselectAllUnits();

							} catch (IOException e1) {
								client.updateDebug("Unable to send message to server");
							}
						}
						else
						{
							client.updateChat("GAME: Can't move that unit there, select an adjacent land");
						}
					}
					else
					{
						client.updateChat("GAME: Select a unit to move first");
					}
				}
			});
		}

		// Add unitgroup to contain the gamepieces, added in refreshDisplay()
		unitGroup = new Group();
		pane.getChildren().add(unitGroup);

		// Add the main game pane to a zoompane to add zooming
		slider = new Slider(0.36,1,0.36);
		ZoomPane zoomPane = new ZoomPane(pane);
		zoomPane.zoomFactorProperty().bind(slider.valueProperty());

		// add the zoompane to a scrollpane to add scrolling
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
		
		System.out.println("Refreshing Display");

		unitGroup.getChildren().clear();

		for(Land land: Game.INSTANCE.getGameState())
		{				
			if (land.getUnit() != null)
			{
				client.updateDebug("Adding a unit for land " + land.getLandId());
				Unit unit = land.getUnit();
				Shape shape = unit.getShape();
				unitGroup.getChildren().add(shape); 

				shape.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent e) {
						if (shape.getScaleX() == 2)
						{
							client.deselectUnit(unit);
							shape.setScaleX(1);
							shape.setScaleY(1);
							client.updateDebug("UNIT DESELECTED - SHRINK");
						}
						else
						{
							boolean selected = client.selectUnit(unit);
							if (selected)
							{
								deselectAllUnits();
								shape.setScaleX(2);
								shape.setScaleY(2);
								client.updateDebug("UNIT SELECTED - ENLARGE");
							}
							else
							{
								client.updateChat("GAME: Can't select opponents units");
							}
						}	
					}
				});
			}
		}
	}

	public void deselectAllUnits()
	{
		Iterator<Node> iterator = unitGroup.getChildren().iterator();
		while (iterator.hasNext()) {
			Node next = iterator.next();
			next.setScaleX(1);
			next.setScaleY(1);
		}
	}

}
