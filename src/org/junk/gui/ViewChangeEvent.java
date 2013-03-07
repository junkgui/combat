package org.junk.gui;

import org.junk.model.Tile;
import org.junk.model.Unit;

public class ViewChangeEvent {
	public static final int VIEW_CHANGED = 0;
	public static final int TILE_SELECTED = 0;
	public static final int UNIT_SELECTED = 0;
	private Renderer source;
	private Unit unitSelected;
	private Tile tileSelectd;
	private int x;
	private int y;
	private int eventType;
	
	public ViewChangeEvent(Renderer source, Unit unitSelected, Tile tileSelected, int x, int y, int eventType) {
		this.source = source;
		this.unitSelected = unitSelected;
		this.tileSelectd = tileSelected;
		this.x = x;
		this.y = y;
		this.eventType = eventType;
	}

	private void setEventType(int eventType) {
		this.eventType = eventType;
	}

	private void setSource(Renderer source) {
		this.source = source;
	}

	private void setTileSelectd(Tile tileSelectd) {
		this.tileSelectd = tileSelectd;
	}

	private void setUnitSelected(Unit unitSelected) {
		this.unitSelected = unitSelected;
	}

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}

	public Renderer getSource() {
		return source;
	}

	public int getEventType() {
		return eventType;
	}

	public Tile getTileSelectd() {
		return tileSelectd;
	}

	public Unit getUnitSelected() {
		return unitSelected;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
