package org.junk.gui;

public interface ViewChangeListener {
	public void handleViewChanged(ViewChangeEvent event);
	public void handleTileSelected(ViewChangeEvent event);
	public void handleUnitSelected(ViewChangeEvent event);
}
