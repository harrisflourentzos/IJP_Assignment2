import java.util.HashMap;
/**
 * 
 * The Class Player represents the person navigating through the "ApplicationName" application. 
 * 
 * @author Harris Flourentzos
 * @version 1.0
 *
 */
public class Player {
	// *************************** FIELDS ***************************
	private Location currentLocation;
	private Direction currentDirection;
	private HashMap<String,Item> myItems;
	
	// *************************** CONSTRUCTORS ***************************
	public Player(Location location) {
		currentLocation = location;
		currentDirection = currentLocation.getDirections().get(0);
		myItems = new HashMap<String, Item>();
	}
	
	// *************************** METHODS ***************************
	
	// Accessor & Mutator Methods:
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Location location) {
		currentLocation = location;
	}
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(Direction direction) {
		currentDirection = direction;
	}
	public Item getItem(String itemName) {
		return myItems.get(itemName);
	}
	
	// Other Methods:
	public void pickUpItem(Item item) {
		myItems.put(item.getName(), item);
	}
	public void dropItem(String itemName) {
		myItems.remove(itemName);
	}

}
