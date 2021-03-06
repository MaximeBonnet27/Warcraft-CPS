package warcraft.components.correct;

import warcraft.services.IRouteService;

public class Route implements IRouteService{

	private int largeur, hauteur;
	private double multiplicateur;


	public Route() {
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public double multiplicateur() {
		return multiplicateur;
	}

	@Override
	public void init(int largeur, int hauteur, double mult) throws Exception {
		if(!(largeur>0))
			throw new Exception("\\pre : largeur > 0");
		if(!(hauteur>0))
			throw new Exception("\\pre : hauteur > 0");
		if(!(mult>=1))
			throw new Exception("\\pre : multiplicateur >= 1");

		this.largeur = largeur;
		this.hauteur = hauteur;
		this.multiplicateur = mult;
	}

}
