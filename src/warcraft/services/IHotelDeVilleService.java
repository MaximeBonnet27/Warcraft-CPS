package warcraft.services;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;

public interface IHotelDeVilleService {

	/*Observators*/
	
	public int largeur();
	public int hauteur();
	public int orRestant();
	public boolean estLaminne();
	public int compteurAbandon();
	public EETAT etat_d_appartenance();
	
	// \pre: etat_d_appartenance()==ETAT.OCCUPE
	public ERace appartenance();
	
	
	/*Invariant*/
	// \inv: estLaminee() == (orRestant() == 0)
	// \inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)
	// \inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)
	// \inv: orRestant()>=0
	// \inv: 0<=compteurAbandon()<=51
	
	/*Constructors*/
	
	// \pre: largeur>0
	// \pre: hauteur>0 
	// \post: largeur() == largeur
	// \post: hauteur() == hauteur
	// \post: orRestant() == 16
	// \post: compteurAbandon() == 0
	// \post: appartenance() == race
	public void init(int largeur, int hauteur, ERace race);
	
	/*Operators*/
	
	// \pre: orRestant()-s>=0
	// \pre: s>=0
	// \post: orRestant() == (orRestant()@pre-s)
	// \post: compteurAbandon() == compteurAbandon()@pre
	// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
	public void retrait(int s);
	
	// \pre: d>=0
	// \post: orRestant() == (orRestant()@pre +d)
	// \post: compteurAbandon() == compteurAbandon()@pre
	// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre) 
	public void depot(int d);
	
	// \pre: (etat_d_appartenance()==ETAT.OCCOPE) ==> (race == appartenance())
	// \post: orRestant() == orRestant()@pre 
	// \post: compteurAbandon() == 0
	// \post: appartenance() == r
	public void accueil(ERace race);
	
	// \pre: etat_d_appartenance() == ETAT.LIBRE
	// \post: orRestant() == orRestant()@pre
	// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
	// \post: appartenance() == appartenance()@pre
	public void abandoned();
}
