import javafx.scene.image.Image;

/**
 * The Class Item is used to instantiate various item objects used in the
 * "ApplicationName" application.
 * 
 * 
 * @author Harris Flourentzos
 * @version 1.0 08/11/2017
 */
public class Item {
	
	private String name;
	private Image itemImage;
//	private String description;
//	private int weight;

	public Item(String name, Image itemImage) {
		this.name = name;
		this.itemImage = itemImage;
//		this.description = description;
//		this.weight = weight;
	}

//	public String getItemInfo() {
//		return getName() + " " + getDescription() + " " + getWeight() + "kg";
//	}

//	public String getDescription() {
//		return description;
//	}

	public Image getItemImage() {
		return itemImage;
	}

	public String getName() {
		return name;
	}
	
//	public int getWeight() {
//		return weight;
//	}
}
