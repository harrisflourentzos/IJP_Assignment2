package model;
import java.util.HashSet;

import javafx.scene.image.Image;

/**
 * 
 * The Direction Class provides a representation of 2D views a user encounters
 * when turning around a location in the "ApplicationName" application. A
 * collection of directions will be used to populate an instance of the Location
 * Class.
 * 
 * @author Harris Flourentzos
 * @version 1.0
 *
 */
public class Direction {
	
	private int direction;
	private Image directionImage;
	private HashSet<Item> items;
	private Location exit;

	public Direction(int direction, Image directionImage) {
		this.direction = direction;
		this.directionImage = directionImage;
		items = new HashSet<Item>();
		exit = null;
	}

	public int getDirection() {
		return direction;
	}
	
	public Image getDirectionImage() {
		return directionImage;
	}
	
	public HashSet<Item> getItems() {
		return items;
	}
	
	public Location getExit() {
		return exit;
	}
	
	public void setExit(Location exit) {
		this.exit = exit;
	}
}
