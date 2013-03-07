/*
 * Created on May 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Tile implements Element {
	public static final int HIDE = 0;
	private ArrayList<Unit> units = null;
	private Image image;
	private Polygon border;
	private boolean selected;
	private int xTranslation;
	private int yTranslation;
	protected int xPosition;
	protected int yPosition;
	/**
	 * 
	 */
	public Tile(Image image, Polygon border, int x, int y) {
		this.image = image;
		this.border = new Polygon(border.xpoints.clone(), border.ypoints.clone(), border.npoints);
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public Polygon getBorder() {
		return border;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setTranslateX(int x) {
		border.translate(x-xTranslation, 0);
		xTranslation = x;
		
	}
	
	public void setTranslateY(int y) {
		border.translate(0, y-yTranslation);
		yTranslation = y;
		
	}
	
	public int getTranslateX() {
		return xTranslation;
	}
	
	public int getTranslateY() {
		return yTranslation;
	}
	
	public boolean isPointInBounds(Point p) {
		return border.contains(p);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
}
