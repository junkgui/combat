/*
 * Created on May 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.model;

import java.awt.Image;

/**
 * @author Hadden
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Unit implements Element {
	private Image image;
	private String name;
	private String description;
	private int maxHitPoints;
	private int attackPoints;
	private int movement;
	private int currentHitPoints;
	protected int xPosition;
	protected int yPosition;

	public Unit(Image image, String name, String description, int maxHitPoints,
			int attackPoints, int movement, int x, int y) {
		this.image = image;
		this.name = name;
		this.description = description;
		this.maxHitPoints = maxHitPoints;
		this.attackPoints = attackPoints;
		this.movement = movement;
		this.xPosition = x;
		this.yPosition = y;
	}

	public Image getImage() {
		return image;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public String getDescription() {
		return description;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getMovement() {
		return movement;
	}

	public String getName() {
		return name;
	}

	public int getCurrentHitPoints() {
		return currentHitPoints;
	}

	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = Math.min(Math.max(currentHitPoints, 0),
				maxHitPoints);
	}

	public boolean isAlive() {
		return currentHitPoints > 0;
	}

	public int getXPosition() {
		return xPosition;
	}

	private void setXPosition(int position) {
		xPosition = position;
	}

	public int getYPosition() {
		return yPosition;
	}

	private void setYPosition(int position) {
		yPosition = position;
	}

}
