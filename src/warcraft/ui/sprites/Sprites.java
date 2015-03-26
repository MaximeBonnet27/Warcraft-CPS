/**
 * 
 */
package warcraft.ui.sprites;

import java.awt.Image;

/**
 * @author 3100381
 *
 */
public class Sprites {
	public static final int SIZE = 32;
	private Image image;
	
	public Sprites(Image image){
		this.image=image;
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	
}
