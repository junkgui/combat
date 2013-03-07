/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.model;

import java.awt.Polygon;
import java.util.Random;

import javax.management.InstanceNotFoundException;

import org.junk.gui.ResourceLoader;


/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BoardState implements Layer {
	private Tile[][] tiles = null;
	public BoardState() {
		ResourceLoader loader = null;
		try {
			loader = ResourceLoader.getInstance();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			System.exit(255);
		}
		int[] x = new int[] {0,16,32,48,32,16,0};
		int[] y = new int[] {32,16,16,32,48,48,32};
		Polygon basePoly = new Polygon(x,y,7);
		Random r = new Random();
		tiles = new Tile[60][];
		int width = 30;
		for (int i = 0; i < tiles.length; i++) {
			Tile[] row = new Tile[width];
			for (int j = 0; j < row.length; j++) {
				if (r.nextFloat() > .33) {
					row[j] = new Tile(loader.getWater(), basePoly, j, i);
				} else {
					if (r.nextFloat() > .91) {
						row[j] = new Tile(loader.getHill(), basePoly, j, i);
					} else {
						row[j] = new Tile(loader.getGrass(), basePoly, j, i);
					}
				}
			}
			tiles[i] = row;
		}
		for (int i = 0; i < tiles.length; i++) {
			Tile[] row = tiles[i];
			for (int j = 0; j < row.length; j++) {
				boolean isWater = tiles[i][j].getImage() == loader.getWater();
				boolean one = tiles[constrain(i-1,tiles.length)][constrain(j,row.length)]
				        .getImage() == loader.getWater();
				boolean two = tiles[constrain(i-1,tiles.length)][constrain(j,row.length)]
				                         				        .getImage() == loader.getWater();
			}
			tiles[i] = row;
		}
		
	}
	
	private static int constrain(int value, int max) {
		return Math.max(0, Math.min(max, value));
	}
	
	
	
	
	public Tile[][] getTiles() {
		return tiles;
	}
}
