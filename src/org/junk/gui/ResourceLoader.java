package org.junk.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.management.InstanceNotFoundException;

public class ResourceLoader {
	private static ResourceLoader instance;
	private int counter;
	private Image grass;
	private Image hill;
	private Image water;

	private ResourceLoader(Component c) {
		MediaTracker mt = new MediaTracker(c);
		grass = loadImage("/images/grass48x48.png", mt);
		hill = loadImage("/images/hill48x48.png", mt);
		water = loadImage("/images/water48x48.png", mt);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Image loadImage(String handle, MediaTracker mt) {
		Image i = Toolkit.getDefaultToolkit().getImage(
						getClass().getResource(handle));
		mt.addImage(i, counter++);
		return i;
	}
	
	public static ResourceLoader getInstance() throws InstanceNotFoundException {
		if (instance == null) {
			throw new InstanceNotFoundException("Singleton was not initialized");
		}
		return instance;
	}
	
	public static ResourceLoader getInstance(Component c) {
		if (instance == null) {
			instance = new ResourceLoader(c);
		}
		return instance;
	}

	public Image getGrass() {
		return grass;
	}

	public Image getHill() {
		return hill;
	}

	public Image getWater() {
		return water;
	}
}
