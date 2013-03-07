/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.gui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.junk.model.GameState;
import org.junk.model.Tile;


/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MainMouseListener implements MouseListener {
	private static final int MOVEMENT_SPEED_X = 49;	
	private static final int MOVEMENT_SPEED_Y = 32;
	private Rectangle rect;
	public MainMouseListener(Rectangle rect) {
		this.rect = rect;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		Tile[][] tiles = GameState.getInstance().getBoardState().getTiles();
		int startX = Renderer.getStartX(rect);
		int startY = Renderer.getStartY(rect);
		int endX = Renderer.getEndX(rect);
		int endY = Renderer.getEndY(rect);
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				int y = Renderer.getYPosition(tiles, i);
				int x = Renderer.getXPosition(tiles, j);
				tiles[y][x].setSelected(
						tiles[y][x].isPointInBounds(e.getPoint()));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	public Point watchMouse() {
		Point pi = MouseInfo.getPointerInfo().getLocation();
		int x = 0;
		int y = 0;
		if (pi.getX() <= 0) {
			rect.x = rect.x - MOVEMENT_SPEED_X;
			x = MOVEMENT_SPEED_X;
			//System.out.println("left");
		} else if (pi.getX() >= rect.width-1) {
			rect.x = rect.x +MOVEMENT_SPEED_X;
			x = -MOVEMENT_SPEED_X;
			//System.out.println("right");
		}
		if (pi.getY() <= 0) {
			rect.y = rect.y - MOVEMENT_SPEED_Y;
			y = MOVEMENT_SPEED_Y;
			//System.out.println("up");
		} else if (pi.getY() >= rect.height-1) {
			rect.y = rect.y +MOVEMENT_SPEED_Y;
			y = -MOVEMENT_SPEED_Y;
			//System.out.println("down");
		}
		pi = new Point(x,y);
		return pi;
	}
}
