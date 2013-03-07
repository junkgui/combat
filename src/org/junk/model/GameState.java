/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.model;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GameState {
	private BoardState tileSet;
	private Set<GameStateListener> listenerSet;
	
	private GameState() {
		listenerSet = new HashSet<GameStateListener>();
		tileSet = new BoardState();
	}
	
	private static GameState instance;
	
	public static GameState getInstance() {
		if (instance == null) {
			instance = new GameState();
		}
		return instance;
	}
	
	public BoardState getBoardState() {
		return tileSet;
	}
	
	public void addGameStateListener(GameStateListener listener) {
		listenerSet.add(listener);
	}
	
	public void removeGameStateListener(GameStateListener listener) {
		listenerSet.remove(listener);
	}
}
