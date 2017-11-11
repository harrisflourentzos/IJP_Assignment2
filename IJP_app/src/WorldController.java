import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Direction;
import model.Item;
import model.Location;
import model.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class WorldController {
	
	private Player player;

	@FXML
	private ImageView view;
	
	@FXML
	private Label message;
	
	@FXML
	private ListView<Item> playerInventory;
	
	@FXML
	private ListView<Item> locationInventory;
	
	public void initialise() {
		// set up UI-related components
		configureComponents();
		// create the world and the player.
		buildWorld();
		// display the direction the player is looking at.
        updateView();
	}
	
	/**
	 * Update the view the player is looking at and the items present in that view.
	 */
	public void updateView() {
		view.setImage(player.getCurrentDirection().getDirectionImage());
		
		locationInventory.getItems().clear();
		
		for(Item item : player.getCurrentDirection().getItems()) {
			locationInventory.getItems().add(item);
		}
		
		playerInventory.getItems().clear();
		
		for(Item item : player.getInventory()) {
			playerInventory.getItems().add(item);
		}
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
    
	public void pickUpItem(Item item) {
		if (item == null) return;
		player.getCurrentDirection().getItems().remove(item);
		player.getInventory().add(item);
		
		message.setText("You picked up " + item.getName());
		
		updateView();
	}
	
	public void dropItem(Item item) {
		if (item == null) return;
		player.getInventory().remove(item);
		player.getCurrentDirection().getItems().add(item);
		
		message.setText("You dropped " + item.getName());
		
		updateView();
	}
	
	public void configureComponents() {
		// render the items with a thumbnail followed by the name of the item
		final Callback<ListView<Item>, ListCell<Item>> cellFactory = listView -> new ListCell<Item>() {
            private final ImageView imageView = new ImageView();

           @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	// set the name of the item as the text
                    setText(item.getName());
                    
                    // set the picture of the item as the thumbnail
                    imageView.setImage(new Image(item.getImage()));
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    setGraphic(imageView);
                }
            }
        };
        
		playerInventory.setCellFactory(cellFactory);
		locationInventory.setCellFactory(cellFactory);
		
		// when the player clicks on an item in the world, it is transferred to his inventory
		locationInventory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
		    @Override
		    public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
		    	Platform.runLater(() -> pickUpItem(newValue));
		    }
		});
		
		// when the player clicks on an item in his inventory, it is dropped in the current view
		playerInventory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
		    @Override
		    public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
		    	Platform.runLater(() -> dropItem(newValue));
		    }
		});
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
    	Item cat = new Item("cat", "ItemImages/cat.png");
    	Item dog = new Item("dog", "ItemImages/dog.png");
    	Item grape = new Item("grape", "ItemImages/grape.png");
    	Item apple = new Item("apple", "ItemImages/apple.png");
    	Item mushroom = new Item("mushroom", "ItemImages/mushroom.png");
    	Item basketball = new Item("basketball", "ItemImages/basketball.png");
    	
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
    	
    	
    	// Initialise Player:
    	player = new Player(room1);
    }
}
