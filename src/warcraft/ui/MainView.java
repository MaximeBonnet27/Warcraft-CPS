/**
 * 
 */
package warcraft.ui;

import javax.swing.JFrame;

import warcraft.ui.sprites.Sprites;

public class MainView extends JFrame implements IMainViewDelegate {

	private IMainViewDelegate delegate;
	/**
	 * 
	 */
	public MainView(IMainViewDelegate delegate) {
		this.delegate = delegate;
	}

	/** 
	 */
	public void init(int width, int height) {
		setTitle("Warcraft - CPS");
		setSize(width * Sprites.SIZE, height * Sprites.SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	/**
	 */
	public void show() {
		setVisible(true);
	}
	
	
	
}
