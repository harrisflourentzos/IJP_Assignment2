import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class WorldController {
	
	private Player player;

	@FXML
	private ImageView imageView;

	@FXML
	private ImageView basketView;

	public void Initialise() {
		// create the world and the player.
		initialiseWorld();
		// display the direction the player is looking at.
        imageView.setImage(player.getCurrentDirection().getDirectionImage());
	}
	
    public void pressRightButton(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( ((indexOfCurrentDirection)+1)% NoOfDir ));
        imageView.setImage(player.getCurrentDirection().getDirectionImage());
    }
    public void pressLeftButton(ActionEvent event) {
    	int indexOfCurrentDirection = player.getCurrentDirection().getDirection();
    	int NoOfDir = player.getCurrentLocation().getNumberOfDirections();
    	
    	player.setCurrentDirection(player.getCurrentLocation().getDirections().get( (NoOfDir + (indexOfCurrentDirection)-1)% NoOfDir));
        imageView.setImage(player.getCurrentDirection().getDirectionImage());
    	
    }
    public void pressForwardButton(ActionEvent event) {
    	
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
    	r1d3.setExits(room2);
    	Direction r1d4 = new Direction(3, new Image("LocationImages/R1D4.png"));
    	
    	//Room2:
    	Direction r2d1 = new Direction(0, new Image("LocationImages/R2D1.png"));
    	Direction r2d2 = new Direction(1, new Image("LocationImages/R2D2.png"));
    	r2d2.setExits(room1);
    	Direction r2d3 = new Direction(2, new Image("LocationImages/R2D3.png"));
    	Direction r2d4 = new Direction(3, new Image("LocationImages/R2D3.png"));
    	
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
