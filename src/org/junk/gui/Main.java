/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.junk.gui;

import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import org.junk.model.GameState;
import org.junk.model.Layer;

/**
 * @author Hadden
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Main {
	
    private static DisplayMode[] BEST_DISPLAY_MODES = new DisplayMode[] {
    	//new DisplayMode(1024, 768, 32, 0),
    	new DisplayMode(1024, 768, 24, 0),
        //new DisplayMode(1024, 768, 16, 0),
        //new DisplayMode(1024, 768, 8, 0),
    	//new DisplayMode(800, 600, 32, 0),
        //new DisplayMode(800, 600, 16, 0),
        //new DisplayMode(800, 600, 8, 0)
    };
    
    Frame mainFrame;
    
    public Main(int numBuffers, GraphicsDevice device) {
        try {
            GraphicsConfiguration gc = device.getDefaultConfiguration();
            mainFrame = new Frame(gc);
            
            mainFrame.addKeyListener(new MainKeyListener());
            
            mainFrame.setUndecorated(true);
            mainFrame.setIgnoreRepaint(true);
            device.setFullScreenWindow(mainFrame);
            if (device.isDisplayChangeSupported()) {
                chooseBestDisplayMode(device);
            }
            ResourceLoader.getInstance(mainFrame);
            Layer[] layers = new Layer[] {GameState.getInstance().getBoardState()};
            
            Renderer r = new Renderer();
            
            Rectangle bounds = mainFrame.getBounds();
            Rectangle cleanRect = new Rectangle(0, 0, 0, 0);
            bounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
            MainMouseListener ml = new MainMouseListener(bounds);
            mainFrame.addMouseListener(ml);
//            
            mainFrame.createBufferStrategy(numBuffers);
            BufferStrategy bufferStrategy = mainFrame.getBufferStrategy();
            int i =0;
            while (true) {
            	i = i++ % numBuffers;
            	long time = System.currentTimeMillis();
		            //for (int i = 0; i < numBuffers; i++) {
		            	Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		            	Point delta = ml.watchMouse();
		            	//g.setTransform(AffineTransform.getTranslateInstance(bounds.getX(), bounds.getY()));
		            	//mainFrame.getBounds().setLocation(bounds.getLocation());
	//	            	bi[(i+1)%numBuffers].getGraphics().drawImage(bi[i],delta.x,delta.y,null);
	//	            	r.render((Graphics2D)bi[(i+1)%numBuffers].getGraphics(), layers, bounds, 
	//	            			new Rectangle(-delta.x,-delta.y,bounds.width, bounds.height));
		            	//if (!(delta.x == 0 && delta.y == 0)) {
		            		//r.render((Graphics2D) bi[i].getGraphics(), layers, bounds, cleanRect);
		            	
			                if (!bufferStrategy.contentsLost()) {
			                    r.render(g, layers, bounds, cleanRect);
			                	//g.drawImage(bi[(i+1)%numBuffers], 0,0,null);
			                	//g.drawImage(bi[i], 0,0,null);
			                }
		            	//}
		                bufferStrategy.show();
		                //cleanRect = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		            	
		                
		                g.dispose();
		            //}
		            try {
		            	System.out.println(100 - (System.currentTimeMillis() - time));
	                    Thread.sleep(Math.max(0, 100 - (System.currentTimeMillis() - time)));
	                } catch (InterruptedException e) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            device.setFullScreenWindow(null);
        }
    }
    
    /**
	 * @return
	 */
	

	private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
        for (int x = 0; x < BEST_DISPLAY_MODES.length; x++) {
            DisplayMode[] modes = device.getDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].getWidth() == BEST_DISPLAY_MODES[x].getWidth()
                   && modes[i].getHeight() == BEST_DISPLAY_MODES[x].getHeight()
                   && modes[i].getBitDepth() == BEST_DISPLAY_MODES[x].getBitDepth()
                   ) {
                    return BEST_DISPLAY_MODES[x];
                }
            }
        }
        return null;
    }
    
    public static void chooseBestDisplayMode(GraphicsDevice device) {
        DisplayMode best = getBestDisplayMode(device);
        if (best != null) {
            device.setDisplayMode(best);
        }
    }
    
    public static void main(String[] args) {
        try {
            int numBuffers = 2;
            if (args != null && args.length > 0) {
                numBuffers = Integer.parseInt(args[0]);
            }
            GraphicsEnvironment env = GraphicsEnvironment.
                getLocalGraphicsEnvironment();
            GraphicsDevice device = env.getDefaultScreenDevice();
            Main test = new Main(numBuffers, device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
}
