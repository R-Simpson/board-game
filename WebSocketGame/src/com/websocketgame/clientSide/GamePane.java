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
		
		unitGroup = new Group();
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
				if (!unitGroup.getChildren().contains(land.getUnit().getShape()))
				{
					client.updateDebug("Adding a unit for land " + land.getLandId());
					
					Unit unit = land.getUnit();
					Shape shape = unit.getShape();
								
					// Using Group to try and get a handle on these shapes to resize all (when a unit is selected, deselect all others)
					unitGroup.getChildren().add(shape); 
					
					// Need to remove every time to prevent duplicate child error...
					pane.getChildren().removeAll(unitGroup);
					pane.getChildren().add(unitGroup);
					
					shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
						public void handle(MouseEvent e) {
							if (shape instanceof Circle)
							{
								if (shape.getScaleX() == 2)
								{
									shape.setScaleX(1);
									shape.setScaleY(1);
									client.deselectUnit(unit);
								}
								else
								{
									// Moves all units down and right, could use to offset more than one unit per land?
									//unitGroup.setTranslateX(10);
									//unitGroup.setTranslateY(10);
									
									// shrink all other units to indicate deselection when a new unit is selected.
									Iterator<Node> iterator = unitGroup.getChildren().iterator();
					                  while (iterator.hasNext()) {
					                    Node next = iterator.next();
					                    next.setScaleX(1);
					                    next.setScaleY(1);
					                  }

									shape.setScaleX(2);
									shape.setScaleY(2);
									client.selectUnit(unit);
								}
									
								/*if (((Circle) shape).getRadius() == 20.0)
								{
									((Circle) shape).setRadius(10.0);
									client.deselectUnit(unit);
								}
								else
								{
									((Circle) shape).setRadius(20.0); 
									// unitGroup.setScaleX(1);
									
									shape.setScaleX(2);
									shape.setScaleY(2);
									
									client.selectUnit(unit);
								}
								*/
							}
							client.updateDebug("UNIT CLICKED");
						}
					});	
				}
			}
		}
	}
	
	public void test()
	{
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
