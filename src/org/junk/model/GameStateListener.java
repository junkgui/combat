package org.junk.model;

public interface GameStateListener {
	public void handleUnitMoved(GameStateChangeEvent event);
	public void handleUnitChaned(GameStateChangeEvent event);
	public void handleTileChanged(GameStateChangeEvent event);
	public void handleMapChanged(GameStateChangeEvent event);
}
