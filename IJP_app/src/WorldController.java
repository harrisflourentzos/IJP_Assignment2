import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class WorldController {
	
	private Player player;
//	private ArrayList<ImageView> itemViews;

	@FXML
	private ImageView imageView;
	
	@FXML
	private TextField message;
	
	@FXML
	private ImageView item1;
	@FXML
	private ImageView item2;
	@FXML
	private ImageView item3;
	@FXML
	private ImageView item4;
	
	
	public void initialise() {
		// create the world and the player.
		buildWorld();
		// display the direction the player is looking at.
        updateView();
        // create a collection of itemViews depending on the maximum number of items a direction will have.
//        itemViews.add(item1);
//        itemViews.add(item2);
//        itemViews.add(item3);
//        itemViews.add(item4);
	}
	
	/**
	 * Update the view the player is looking at and the items present in that view.
	 */
	public void updateView() {
		
		imageView.setImage(player.getCurrentDirection().getDirectionImage());
		
		clearItemViews();
		Iterator<Item> it = player.getCurrentDirection().getItems().iterator();
		
		if(it.hasNext()) {
			item1.setImage(it.next().getItemImage());
		}
		if(it.hasNext()) {
			item2.setImage(it.next().getItemImage());
		}
		if(it.hasNext()) {
			item3.setImage(it.next().getItemImage());
		}
		if(it.hasNext()) {
			item4.setImage(it.next().getItemImage());
		}
		
		
//		Iterator<ImageView> it = itemViews.iterator();
//		for(String item : player.getCurrentDirection().getItems().keySet()) {
//			it.next().setImage(player.getCurrentDirection().getItems().get(item).getItemImage());
//		}
	}

	public void clearItemViews() {
		item1.setImage(null);
		item2.setImage(null);
		item3.setImage(null);
		item4.setImage(null);
	}
	
	public void clearMessage() {
		message.setText("");
	}
	
    public void turnRight(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	clearMessage();
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( ((indexOfCurrentDirection)+1)% NoOfDir ));
        updateView();
    }
    
    public void turnLeft(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	clearMessage();
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( (NoOfDir + (indexOfCurrentDirection)-1)% NoOfDir));
        updateView();
    }
    
	public void moveForward(ActionEvent event) {

		if (player.getCurrentDirection().getExit() == null) {
			message.setText("You cannot move into this direction");
		} else {
			Location newLocation = player.getCurrentDirection().getExit();

			player.setCurrentLocation(newLocation);
			player.setCurrentDirection(newLocation.getDirections().get(player.getCurrentDirection().getDirection()));
			updateView();
		}
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
    	Item cat = new Item("cat",new Image("ItemImages/cat.png"));
    	Item dog = new Item("dog",new Image("ItemImages/dog.png"));
    	Item grape = new Item("grape",new Image("ItemImages/grape.png"));
    	Item apple = new Item("apple",new Image("ItemImages/apple.png"));
    	Item mushroom = new Item("mushroom",new Image("ItemImages/mushroom.png"));
    	Item basketball = new Item("basketball",new Image("ItemImages/basketball.png"));
    	
    	// Add items to directions:
    	r1d1.setItem(cat);
    	r1d4.setItem(dog);
    	r1d4.setItem(mushroom);
    	r2d3.setItem(grape);
    	r2d1.setItem(basketball);
    	r2d4.setItem(apple);
    	
    	// Add directions to each room:
    	room1.addDirection(r1d1);
    	room1.addDirection(r1d2);
    	room1.addDirection(r1d3);
    	room1.addDirection(r1d4);
    	
    	room2.addDirection(r2d1);
    	room2.addDirection(r2d2);
    	room2.addDirection(r2d3);
    	room2.addDirection(r2d4);
    	
    	
    	// Initialise Player:
    	player = new Player(room1);
    }
}
