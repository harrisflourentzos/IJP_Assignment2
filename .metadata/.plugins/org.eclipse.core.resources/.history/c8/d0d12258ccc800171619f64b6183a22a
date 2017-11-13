package model;
import java.util.HashSet;
/**
 * 
 * The Class Player represents the person navigating through the "ApplicationName" application. 
 * 
 * @author Harris Flourentzos
 * @version 1.0
 *
 */
public class Player {
	
	private Location currentLocation;
	private Direction currentDirection;
	private HashSet<Item> inventory = new HashSet<Item>();
	
	public Player(Location location) {
		currentLocation = location;
		currentDirection = currentLocation.getDirections().get(0);
	}
	
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
	
	public HashSet<Item> getInventory() {
		return inventory;
	}
}
