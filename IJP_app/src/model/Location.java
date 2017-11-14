package model;
import java.util.ArrayList;

/**
 * 
 * The Location class represents locations on the "ApplicationName" application
 * available to the user to move between. Any Location hosts a number of
 * directions the user can look towards while looking around.
 * 
 * @author Harris Flourentzos
 * @version 1.0
 *
 */
public class Location {

	private String name;
	private ArrayList<Direction> directions = new ArrayList<Direction>();

	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfDirections() {
		return directions.size();
	}
	
	public int getIndexOfDirection(Direction direction) {
		return directions.indexOf(direction);
	}

	public ArrayList<Direction> getDirections() {
		return directions;
	}

	public void addDirection(Direction direction) {
		directions.add(direction);
	}
	
	public Direction getRightDirection(Direction currentDirection) {
		Direction rightDirection =  directions.get( (directions.indexOf(currentDirection)+1) % directions.size() );
		return rightDirection;
	}
	
	public Direction getLeftDirection(Direction currentDirection) {
		Direction leftDirection =  directions.get( (directions.size() + directions.indexOf(currentDirection)-1) % directions.size() );
		return leftDirection;
	}
}
