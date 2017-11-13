package model;
import java.util.HashSet;

import javafx.scene.image.Image;
/**
 * 
 * The Class Player represents the person navigating through the "ApplicationName" application. 
 * 
 * @author Harris Flourentzos
 * @version 1.0
 *
 */
public class WorldMap {
	
	private Location currentLocation;
	private Direction currentDirection;
	private HashSet<Item> inventory = new HashSet<Item>();
	
	public WorldMap() {
		buildWorld();
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
	
	public void buildWorld() {
    	// Create the Locations:
    	Location room1, room2;
    	
    	room1 = new Location("room1");
    	room2 = new Location("room2");
    	
    	// Initialise Directions for each room and set their exits if they have any:
    	
    	// Room1:
    	Direction r1d1 = new Direction(0, new Image("LocationImages/R1D1.png"));
    	Direction r1d2 = new Direction(1, new Image("LocationImages/R1D2.png"));
    	Direction r1d3 = new Direction(2, new Image("LocationImages/R1D3.png"));
    	r1d3.setExit(room2);
    	Direction r1d4 = new Direction(3, new Image("LocationImages/R1D4.png"));
    	
    	//Room2:
    	Direction r2d1 = new Direction(0, new Image("LocationImages/R2D1.png"));
    	r2d1.setExit(room1);
    	Direction r2d2 = new Direction(1, new Image("LocationImages/R2D2.png"));
    	Direction r2d3 = new Direction(2, new Image("LocationImages/R2D3.png"));
    	Direction r2d4 = new Direction(3, new Image("LocationImages/R2D4.png"));
    	
    	// Initialise items :
    	Item cat = new Item("cat", new Image("ItemImages/cat.png"));
    	Item dog = new Item("dog", new Image("ItemImages/dog.png"));
    	Item grape = new Item("grape", new Image("ItemImages/grape.png"));
    	Item apple = new Item("apple", new Image("ItemImages/apple.png"));
    	Item mushroom = new Item("mushroom", new Image("ItemImages/mushroom.png"));
    	Item basketball = new Item("basketball", new Image("ItemImages/basketball.png"));
    	
    	// Add items to directions:
    	r1d1.getItems().add(cat);
    	r1d4.getItems().add(dog);
    	r1d4.getItems().add(mushroom);
    	r2d3.getItems().add(grape);
    	r2d1.getItems().add(basketball);
    	r2d4.getItems().add(apple);
    	
    	// Add directions to each room:
    	room1.addDirection(r1d1);
    	room1.addDirection(r1d2);
    	room1.addDirection(r1d3);
    	room1.addDirection(r1d4);
    	
    	room2.addDirection(r2d1);
    	room2.addDirection(r2d2);
    	room2.addDirection(r2d3);
    	room2.addDirection(r2d4);
    	
    	currentLocation = room1;
		currentDirection = currentLocation.getDirections().get(0);
    	
    }
}
