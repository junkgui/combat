/*
 * Created on May 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import org.junk.model.Layer;
import org.junk.model.Tile;

/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Renderer {
	private static int tileHeight;
	private static int tileWidth;
	private long fpsTime = System.currentTimeMillis(); 
	private long fpsCount;
	private long fps;
	private Rectangle lastBounds;
	private Color transparentRed = new Color(1.0F,0.0F,0.0F,0.5F);
	
	private void setup(Polygon p) {
		Rectangle r = p.getBounds();
		tileHeight = r.height/2;
		tileWidth = r.width+17;
	}
	
	
	public void render(Graphics2D g, Layer[] layers, Rectangle bounds, Rectangle cleanRect) {
		g.setColor(Color.BLACK);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,   // Anti-alias!
			    RenderingHints.VALUE_ANTIALIAS_ON);
		//g.fillRect(0,0,bounds.width, bounds.height);
		ArrayList<Tile> selection = new ArrayList<Tile>();
		if (tileHeight == 0 || tileWidth == 0) {
			setup(layers[0].getTiles()[0][0].getBorder());
		}
		for (int i = 0; i < layers.length; i++) {
			Tile[][] ii = layers[i].getTiles();
			int startY = getStartY(bounds);
			int endY = getEndY(bounds)+1;
			
	        for (int j = startY; j < endY; j++) {
	        	int offset = 0;
	        	if (Math.abs(j % 2) == 1) {
	        		offset = 32;
	        	}
	        	int startX = getStartX(bounds);
				int endX = getEndX(bounds)+1;
	        	for (int k = startX; k < endX; k++) {
	        		int ypos = getYPosition(ii, j);
	        		int xpos = getXPosition(ii, k);
	        		Tile tile = ii[ypos][xpos];
	        		if (!bounds.equals(lastBounds) || tile.isSelected()) {
		        		int x =(offset+(k*(48+17))) - bounds.x -24;
		        		int y = (((j)*16)) - bounds.y -16 -16;
		        		//if (x >= 0 && y >= 0 && x <= bounds.width-48 && y <= bounds.height-48) {
		        		if (!cleanRect.contains(tile.getBorder().getBounds())) {
			        		g.drawImage(tile.getImage(), x, y, null);
			        		tile.setTranslateX(x);
			        		tile.setTranslateY(y);
			        		g.drawString(xpos + ", " +ypos, x+10,y+35);
		        		}
	        		}
	        		if (tile.isSelected()) {
	        			selection.add(tile);
	        		}
	        		//}
	        	}
	        }
		}
		Iterator<Tile> it = selection.iterator();
		
		//Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3F);
		//Composite old = g.getComposite();
		while (it.hasNext()) {
				Tile selectedTile = it.next();
				//g.setComposite(old);
				g.setColor(Color.RED);
				g.draw(selectedTile.getBorder());
				g.setColor(transparentRed);
				//g.setComposite(alpha);
				g.fill(selectedTile.getBorder());
			//g.drawImage(selection.getImage(), selection.getTranslateX(), selection.getTranslateY(), null);
		}
		fps = (fpsCount++ * fps + (System.currentTimeMillis() - fpsTime)) / fpsCount;
		fpsTime = System.currentTimeMillis();
		fpsCount = Math.max(10, fpsCount);
		g.setColor(Color.RED);
		g.drawString("FPS: "+fps, 20, 20);
	}
	int count = 0;


	/**
	 * @param ii
	 * @param x
	 * @param ypos
	 * @return
	 */
	public static int getXPosition(Tile[][] ii, int x) {
		int xpos;
		if (x >= 0) {
			xpos = x%ii[0].length;
		} else {
			xpos = (Integer.MAX_VALUE-46+x)%ii[0].length;
		}
		return xpos;
	}


	/**
	 * @param ii
	 * @param Y
	 * @return
	 */
	public static int getYPosition(Tile[][] ii, int Y) {
		int ypos;
		if (Y >= 0) {
			ypos = Y%ii.length;
		} else {
			ypos = (Integer.MAX_VALUE-46+Y)%ii.length;
		}
		return ypos;
	}


	/**
	 * @param bounds
	 * @param startX
	 * @return
	 */
	public static int getEndX(Rectangle bounds) {
		int startX = getStartX(bounds);
		int endX = startX + ((bounds.width+48) / tileWidth);
		return endX;
	}


	/**
	 * @param bounds
	 * @return
	 */
	public static int getStartX(Rectangle bounds) {
		int startX = bounds.x/tileWidth;
		return startX;
	}


	/**
	 * @param bounds
	 * @param startY
	 * @return
	 */
	public static int getEndY(Rectangle bounds) {
		int startY = getStartY(bounds);
		int endY = startY + (bounds.height / tileHeight);
		return endY;
	}


	/**
	 * @param bounds
	 * @return
	 */
	public static int getStartY(Rectangle bounds) {
		int startY = bounds.y/tileHeight;
		return startY;
	}
}
