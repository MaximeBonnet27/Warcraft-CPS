package warcraft.components.error;

import warcraft.services.IRouteService;

public class RouteError implements IRouteService{

	private int largeur, hauteur;
	private double multiplicateur;


	public RouteError() {
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
		//error
		//if(!(largeur>0))
		//	throw new Exception("\\pre : largeur > 0");
		//error
		//if(!(hauteur>0))
		//	throw new Exception("\\pre : hauteur > 0");
		//error
		//if(!(multiplicateur>=1))
		//	throw new Exception("\\pre : multiplicateur >= 1");

		this.largeur = largeur;
		this.hauteur = hauteur;
		//error
		this.multiplicateur = 0;
	}

}
