/**
 * 
 */
package warcraft.ui.sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author 3100381
 *
 */
public class TileSet {
	private Sprites[][] tab;
	
	/**
	 * @param string
	 */
	public TileSet(String filename) {
		
		try {
			BufferedImage tiles = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(int i, int j){
		return tab[i][j].getImage();
	}
}
