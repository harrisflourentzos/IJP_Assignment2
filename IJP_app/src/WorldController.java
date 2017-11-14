import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Item;
import model.Location;
import model.WorldMap;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class WorldController {
	
	private WorldMap worldMap;

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
		worldMap = new WorldMap();
		// display the direction the player is looking at.
        updateView();
	}
	
	/**
	 * Update the view the player is looking at and the items present in that view.
	 */
	public void updateView() {
		view.setImage(worldMap.getCurrentDirection().getDirectionImage());
		
		locationInventory.getItems().clear();
		
		for(Item item : worldMap.getCurrentDirection().getItems()) {
			locationInventory.getItems().add(item);
		}
		
		playerInventory.getItems().clear();
		
		for(Item item : worldMap.getInventory()) {
			playerInventory.getItems().add(item);
		}
	}
	
	public void clearMessage() {
		message.setText("");
	}
	
    public void turnRight(ActionEvent event) {
    	int indexOfCurrentDirection = worldMap.getCurrentDirection().getDirection();
    	int NoOfDir = worldMap.getCurrentLocation().getNumberOfDirections();
    	
    	clearMessage();
    	worldMap.setCurrentDirection(worldMap.getCurrentLocation().getDirections().get( ((indexOfCurrentDirection)+1)% NoOfDir ));
        updateView();
    }
    
    public void turnLeft(ActionEvent event) {
    	int indexOfCurrentDirection = worldMap.getCurrentDirection().getDirection();
    	int NoOfDir = worldMap.getCurrentLocation().getNumberOfDirections();
    	
    	clearMessage();
    	worldMap.setCurrentDirection(worldMap.getCurrentLocation().getDirections().get( (NoOfDir + (indexOfCurrentDirection)-1)% NoOfDir));
        updateView();
    }
    
	public void moveForward(ActionEvent event) {

		if (worldMap.getCurrentDirection().getExit() == null) {
			message.setText("You cannot move into this direction");
		} else {
			Location newLocation = worldMap.getCurrentDirection().getExit();

			worldMap.setCurrentLocation(newLocation);
			worldMap.setCurrentDirection(newLocation.getDirections().get(worldMap.getCurrentDirection().getDirection()));
			updateView();
		}
	}
    
	public void pickUpItem(Item item) {
		if (item == null) return;
		worldMap.getCurrentDirection().getItems().remove(item);
		worldMap.getInventory().add(item);
		
		message.setText("You picked up " + item.getName());
		
		updateView();
	}
	
	public void dropItem(Item item) {
		if (item == null) return;
		worldMap.getInventory().remove(item);
		worldMap.getCurrentDirection().getItems().add(item);
		
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
                getStyleClass().add("mystyleclass");
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	// set the name of the item as the text
                    setText(item.getName());
                    
                    // set the picture of the item as the thumbnail
                    imageView.setImage(item.getImage());
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
	
    
}
