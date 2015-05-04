package warcraft.contracts;

import static org.junit.Assert.*;
import warcraft.decorators.MineDelegator;
import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IMineService;

public class MineContrat extends MineDelegator {

	public MineContrat(IMineService delegate) {
		super(delegate);
	}

	public void checkInvariants(){
		// \inv: estLaminee() == (orRestant() == 0)
		assertTrue("\\inv: estLaminee() == (orRestant() == 0)", super.estLaminne()==(super.orRestant()==0));
		
		// \inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)
		assertTrue("\\inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)", (super.etat_d_appartenance()==EETAT.LIBRE)==(super.compteurAbandon()==51));
		
		// \inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)
		assertTrue("\\inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)", (super.etat_d_appartenance()==EETAT.OCCUPE)==(super.compteurAbandon()<51));
		
		// \inv: orRestant()>=0
		assertTrue("\\inv: orRestant()>=0", super.orRestant()>=0);
		
		// \inv: 0<=compteurAbandon()<=51
		assertTrue("\\inv: 0<=compteurAbandon()<=51", 0<=super.compteurAbandon() && super.compteurAbandon()<=51);
	}

	@Override
	public int largeur() {
		checkInvariants();
		return super.largeur();
	}

	@Override
	public int hauteur() {
		checkInvariants();
		return super.hauteur();
	}

	@Override
	public int orRestant() {
		checkInvariants();
		return super.orRestant();
	}

	@Override
	public boolean estLaminne() {
		checkInvariants();
		return super.estLaminne();
	}

	@Override
	public int compteurAbandon() {
		checkInvariants();
		return super.compteurAbandon();
	}

	@Override
	public EETAT etat_d_appartenance() {
		checkInvariants();
		return super.etat_d_appartenance();
	}

	@Override
	public ERace appartenance() {
		// \pre: etat_d_appartenance()==ETAT.OCCUPE
		assertTrue("\\pre: etat_d_appartenance()==ETAT.OCCUPE", super.etat_d_appartenance()==EETAT.OCCUPE);
		
		checkInvariants();
		return super.appartenance();
	}

	@Override
	public void init(int largeur, int hauteur) {
		// \pre: largeur>0
		assertTrue("\\pre: largeur>0", super.largeur()>0);
		
		// \pre: hauteur>0
		assertTrue("\\pre: hauteur>0", super.hauteur()>0);
		
		super.init(largeur, hauteur);
		checkInvariants();
		
		// \post: largeur() == largeur
		assertTrue("\\post: largeur() == largeur", super.largeur()==largeur);
		
		// \post: hauteur() == hauteur
		assertTrue("\\post: hauteur() == hauteur", super.hauteur()==hauteur);
		
		// \post: orRestant() == 500
		assertTrue("\\post: orRestant() == 500", super.orRestant()==500);
		
		// \post: compteurAbandon() == 51
		assertTrue("\\post: compteurAbandon() == 51", super.compteurAbandon()==51);
	}

	@Override
	public void retrait(int s) {
		super.retrait(s);
	}

	@Override
	public void accueil(ERace race) {
		super.accueil(race);
	}

	@Override
	public void abandoned() {
		super.abandoned();
	}
	
	
}
