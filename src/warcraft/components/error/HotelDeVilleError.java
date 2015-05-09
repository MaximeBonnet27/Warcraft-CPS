package warcraft.components.error;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IHotelDeVilleService;

public class HotelDeVilleError implements IHotelDeVilleService {

	private int largeur,hauteur,orRestant,compteurAbandon;
	private ERace appartenance;
	
	public HotelDeVilleError() {
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
	public ERace appartenance() throws Exception {
		//error
		//if(!(etat_d_appartenance()==EETAT.OCCUPE))
		//	throw new Exception("\\pre: etat_d_appartenance()==ETAT.OCCUPE");
		return appartenance;
	}

	@Override
	public void init(int largeur, int hauteur, ERace race) throws Exception {
		if(!(largeur>0))
			throw new Exception("\\pre: largeur>0");
		
		//error
		//if(!(hauteur>0))
		//	throw new Exception("\\pre: hauteur>0");
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.appartenance=race;
		this.orRestant=16;
		this.compteurAbandon=0;
	}

	@Override
	public void retrait(int s) throws Exception {
		if(!((orRestant()-1)>=0))
			throw new Exception("\\pre: orRestant()-s>=0");
		if(!(s>=0))
			throw new Exception("\\pre: s>=0");
		this.orRestant=orRestant()+s;//error
	}

	@Override
	public void depot(int d) throws Exception {
		//error
		//if(!(d>=0))
		//	throw new Exception("\\pre: d>=0");
		
		this.orRestant=orRestant()+d;
	}

	@Override
	public void accueil(ERace race) throws Exception {
		if(!(!(etat_d_appartenance()==EETAT.OCCUPE) || (race==appartenance())))
			throw new Exception("\\pre: etat_d_appartenance()==ETAT.OCCOPE) ==> (race == appartenance())");
		//this.compteurAbandon=0;
		//error
		this.appartenance=race;
	}

	@Override
	public void abandoned() throws Exception {
		if(!(etat_d_appartenance()==EETAT.OCCUPE))
			throw new Exception("\\pre: etat_d_appartenance() == ETAT.OCCUPE");
		this.compteurAbandon=compteurAbandon()+10;//error
	}

}
