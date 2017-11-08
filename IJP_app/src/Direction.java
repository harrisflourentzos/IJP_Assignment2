import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<String, Item> items;
	private ArrayList<Location> exits;

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
		items = new HashMap<String, Item>();
		exits = new ArrayList<Location>();
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
	public HashMap<String, Item> getItems() {
		return items;
	}
	
	/**
	 * @return The exits available to the user in the direction he is looking at.
	 */
	public ArrayList<Location> getExits() {
		return exits;
	}
	
	// Mutator Methods:
	
	/**
	 * Set items into the direction the user is looking at.
	 */
	public void setItems(Item item) {
		items.put(item.getName(),item);
	}
	
	/**
	 * Set the available exits in this direction.
	 */
	public void setExits(Location exit) {
		exits.add(exit);
	}

}
