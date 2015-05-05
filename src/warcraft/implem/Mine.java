package warcraft.implem;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IMineService;

public class Mine implements IMineService {
	
	private int largeur,hauteur,orRestant,compteurAbandon;
	private ERace appartenance;
	
	public Mine() {
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
	public int orRestant() {
		return orRestant;
	}

	@Override
	public boolean estLaminne() {
		return orRestant()==0;
	}

	@Override
	public int compteurAbandon() {
		return compteurAbandon;
	}

	@Override
	public EETAT etat_d_appartenance() {
		if(compteurAbandon()==51)
			return EETAT.LIBRE;
		return EETAT.OCCUPE;
	}

	@Override
	public ERace appartenance() {
		return appartenance;
	}

	@Override
	public void init(int largeur, int hauteur) {
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.orRestant=500;
		this.compteurAbandon=51;
	}

	@Override
	public void retrait(int s) {
		this.orRestant=orRestant()-s;
	}

	@Override
	public void accueil(ERace race) {
		this.compteurAbandon=0;
		this.appartenance=race;
	}

	@Override
	public void abandoned() {
		this.compteurAbandon=compteurAbandon()+1;
	}
	
}