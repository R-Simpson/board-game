package com.websocketgame.clientSide;

import javafx.scene.shape.Polygon;
import com.websocketgame.serverSide.Land;

public class LandPoly {
	private Land land;
	private Polygon polygon;
	
	public LandPoly(Land land, Polygon polygon)
	{
		this.setLand(land);
		this.setPolygon(polygon);
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	
	
}
