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

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;
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
								PlayerMessage message = new PlayerMessage(client.playerid, client.getSelectedUnit(), land.getLandId());	

								client.out.writeObject(message);
								client.updateDebug("Selected unit at " + client.getSelectedUnit().getLand().getLandId());
								client.updateDebug("Send message from client " + client.playerid + " to claim " + land.getLandId() 
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
		// When a move is made I think a second listener is being added for pre-existing units, then a third etc - so it will select/unselect, 
		// then select/unselect/select etc.
		
		System.out.println("Refreshing Display");

		pane.getChildren().remove(unitGroup);
		unitGroup = new Group();
		
		for(Land land: Game.INSTANCE.getGameState())
		{			
			if (land.getUnit() != null)
			{
				client.updateDebug("HEYYYY, unit found for land " + land.getLandId());
				if (!unitGroup.getChildren().contains(land.getUnit().getShape()))
				{
					client.updateDebug("Adding a unit for land " + land.getLandId());

					Unit unit = land.getUnit();
					Shape shape = unit.getShape();

					// Using Group to try and get a handle on these shapes to resize all (when a unit is selected, deselect all others)
					unitGroup.getChildren().add(shape); 

					shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
						public void handle(MouseEvent e) {
							if (shape.getScaleX() == 2)
							{
								client.updateDebug("Scale of unit is " + shape.getScaleX());
								client.deselectUnit(unit);
								shape.setScaleX(1);
								shape.setScaleY(1);
								client.updateDebug("UNIT DESELECTED - SHRINK");
							}
							else
							{
								client.updateDebug("Scale of unit is " + shape.getScaleX());
								boolean selected = client.selectUnit(unit);
								client.updateDebug("client.selectUnit() returned unit at " + unit.getLand().getLandId());
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
			else	// land.getUnit() == null
			{
					// no unit exists to use as a reference for unitGroup.getChildren().remove()
			}
		}
		
		// Need to remove every time to prevent duplicate child error...
		pane.getChildren().removeAll(unitGroup);
		pane.getChildren().add(unitGroup);
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
