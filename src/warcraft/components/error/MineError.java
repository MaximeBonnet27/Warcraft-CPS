package warcraft.components.error;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IMineService;

public class MineError implements IMineService {
	
	private int largeur,hauteur,orRestant,compteurAbandon;
	private ERace appartenance;
	
	public MineError() {
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
	public void init(int largeur, int hauteur) throws Exception {
		if(!(largeur>0))
			throw new Exception("\\pre: largeur>0");
		
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.orRestant=500;
		this.compteurAbandon=50;//error
		//this.compteurAbandon=51;
	}

	@Override
	public void retrait(int s) throws Exception {
		if(!(s>=0))
			throw new Exception("\\pre: s>=0");
		
		this.orRestant=orRestant()-1;//error
		//this.orRestant=orRestant()-s;
	}

	@Override
	public void accueil(ERace race) {
		this.compteurAbandon=0;
		//this.appartenance=race;//error
	}

	@Override
	public void abandoned() {
		this.compteurAbandon=51;//error
		//this.compteurAbandon=compteurAbandon()+1;
	}
	
}
