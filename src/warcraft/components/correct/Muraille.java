package warcraft.components.correct;

import warcraft.services.IMurailleService;

public class Muraille implements IMurailleService{

	private int largeur, hauteur;
	private int pointsDeVie;

	public Muraille() {
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
	public int pointsDeVie() {
		return pointsDeVie;
	}

	@Override
	public boolean estDetruite() {
		return pointsDeVie <= 0;
	}

	@Override
	public void init(int largeur, int hauteur, int pv) throws Exception{

		if(!(largeur>0))
			throw new Exception("\\pre : largeur > 0");

		if(!(hauteur>0))
			throw new Exception("\\pre : hauteur > 0");

		if(!(pv>0))
			throw new Exception("\\pre : pv > 0");

		this.largeur = largeur;
		this.hauteur = hauteur;
		this.pointsDeVie = pv;

	}

	@Override
	public void taper(int degats) throws Exception{
		if(estDetruite())
			throw new Exception("\\pre : !estDetruite() ");

		if(!(degats>0))
			throw new Exception("\\pre : degats > 0");
		pointsDeVie -= degats;
	}

}
