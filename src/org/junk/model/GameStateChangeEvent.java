package org.junk.model;


public class GameStateChangeEvent {
	public static final int MAP_CHANGED = 0;
	public static final int UNIT_CHANGED = 1;
	public static final int UNIT_MOVED = 2;
	public static final int TILE_CHANGED = 3;
	private GameState source;
	private Unit unit;
	private Tile tile;
	private int eventType;
	private int x;
	private int y;

	public GameStateChangeEvent(GameState source, Tile tile, Unit unit, int x, int y, int eventType) {
		this.source = source;
		this.unit = unit;
		this.eventType = eventType;
		this.tile = tile;
		this.x = x;
		this.y = y;
	}
	
	public GameState getSource() {
		return source;
	}

	private void setSource(GameState source) {
		this.source = source;
	}

	public int getEventType() {
		return eventType;
	}

	private void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public Unit getUnit() {
		return unit;
	}

	private void setUnit(Unit unit) {
		this.unit = unit;
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}
}
