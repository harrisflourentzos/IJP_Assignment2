package model;

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
	private Image image;

	public Item(String name, Image image) {
		this.name = name;
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public String getName() {
		return name;
	}
}
