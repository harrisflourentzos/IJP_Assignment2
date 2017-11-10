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
	// *************************** FIELDS ***************************
	private int direction;
	private Image directionImage;
	private HashSet<Item> items;
	private Location exit;

	// *************************** CONSTRUCTORS ***************************
	/**
	 * Constructor for the Class Direction.
	 * 
	 * @param direction
	 * @param directionImage
	 */
	public Direction(int direction, Image directionImage) {
		this.direction = direction;
		this.directionImage = directionImage;
		items = new HashSet<Item>();
		exit = null;
	}
	// *************************** METHODS ***************************
	
	// Accessor Methods:
	/**
	 * @return The direction of the Direction.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * @return The image of the direction the user is looking at.
	 */
	public Image getDirectionImage() {
		return directionImage;
	}
	
	/**
	 * 
	 * @return The items present in the direction the user is looking at.
	 */
	public HashSet<Item> getItems() {
		return items;
	}
	
	/**
	 * @return The exits available to the user in the direction he is looking at.
	 */
	public Location getExit() {
		return exit;
	}
	
	// Mutator Methods:
	
	/**
	 * Set items into the direction the user is looking at.
	 */
	public void setItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Set the available exits in this direction.
	 */
	public void setExit(Location exit) {
		this.exit = exit;
	}

}
