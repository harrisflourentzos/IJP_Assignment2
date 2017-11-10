import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class WorldController {
	
	private Player player;

	@FXML
	private ImageView imageView;
	
	@FXML
	private TextField messageID;
	
	
	public void Initialise() {
		// create the world and the player.
		initialiseWorld();
		// display the direction the player is looking at.
        updateView();
	}
	
	/**
	 * Update the view the player is looking at.
	 */
	public void updateView() {
		imageView.setImage(player.getCurrentDirection().getDirectionImage());
	}
	/**
	 * Clear the text in the message.
	 */
	public void resetTextField() {
		messageID.setText("");
	}
	/**
	 * Player turns and looks to the right.
	 * @param event
	 */
    public void pressRightButton(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	resetTextField();
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( ((indexOfCurrentDirection)+1)% NoOfDir ));
        imageView.setImage(player.getCurrentDirection().getDirectionImage());
    }
    /**
     * Player turns and looks to the left.
     * @param event
     */
    public void pressLeftButton(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	resetTextField();
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( (NoOfDir + (indexOfCurrentDirection)-1)% NoOfDir));
        imageView.setImage(player.getCurrentDirection().getDirectionImage());
    	
    }
    /**
     * Player moves towards the direction he/she is looking at.
     * @param event
     */
	public void pressForwardButton(ActionEvent event) {

		if (player.getCurrentDirection().getExit() == null) {
			messageID.setText("You cannot move into this direction");
		} else {
			Location newLocation = player.getCurrentDirection().getExit();

			player.setCurrentLocation(newLocation);
			player.setCurrentDirection(newLocation.getDirections().get(player.getCurrentDirection().getDirection()));
			updateView();
		}
	}
    
    // ************************************** Build World ***************************************
    
    public void initialiseWorld() {
    	// Create the Locations:
    	Location room1, room2;
    	
    	room1 = new Location("room1", 4);
    	room2 = new Location("room2", 4);
    	
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
    	
    	// Add directions to each room:
    	room1.setDirections(r1d1);
    	room1.setDirections(r1d2);
    	room1.setDirections(r1d3);
    	room1.setDirections(r1d4);
    	
    	room2.setDirections(r2d1);
    	room2.setDirections(r2d2);
    	room2.setDirections(r2d3);
    	room2.setDirections(r2d4);
    	
    	// Initialise Player:
    	player = new Player(room1);
    	
    }
}
