package warcraft.contracts;

import static org.junit.Assert.*;
import warcraft.decorators.HotelDeVilleDecorator;
import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IHotelDeVilleService;

public class HotelDeVilleContrat extends HotelDeVilleDecorator {

	public HotelDeVilleContrat(IHotelDeVilleService delegate) {
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
	public void init(int largeur, int hauteur, ERace race) {
		// \pre: largeur>0
		assertTrue("\\pre: largeur>0", super.largeur()>0);

		// \pre: hauteur>0
		assertTrue("\\pre: hauteur>0", super.hauteur()>0);

		super.init(largeur, hauteur, race);
		checkInvariants();

		// \post: largeur() == largeur
		assertTrue("\\post: largeur() == largeur", super.largeur()==largeur);

		// \post: hauteur() == hauteur
		assertTrue("\\post: hauteur() == hauteur", super.hauteur()==hauteur);

		// \post: orRestant() == 16
		assertTrue("\\post: orRestant() == 16", super.orRestant()==16);

		// \post: compteurAbandon() == 0
		assertTrue("\\post: compteurAbandon() == 0", super.compteurAbandon()==0);

		// \post: appartenance() == race
		assertTrue("\\post: appartenance() == race", super.appartenance()==race);
	}

	@Override
	public void retrait(int s) {
		//Captures
		int oldOrRestant=super.orRestant();
		int oldCompteurAbandon=super.compteurAbandon();
		EETAT oldEtat_d_appartenance=super.etat_d_appartenance();
		ERace oldAppartenance=super.appartenance();

		// \pre: orRestant()-s>=0
		assertTrue("\\pre: orRestant()-s>=0",(super.orRestant()-1)>=0);

		// \pre: s>=0
		assertTrue("\\pre: s>=0", s>=0);

		checkInvariants();
		super.retrait(s);
		checkInvariants();

		// \post: orRestant() == (orRestant()@pre-s)
		assertTrue("\\post: orRestant() == (orRestant()@pre-s)", super.orRestant()==(oldOrRestant-s));

		// \post: compteurAbandon() == compteurAbandon()@pre
		assertTrue("\\post: compteurAbandon() == compteurAbandon()@pre", super.compteurAbandon()==(oldCompteurAbandon));

		// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
		assertTrue("\\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (super.appartenance()==oldAppartenance));
	}

	@Override
	public void depot(int d) {
		//Captures
		int oldOrRestant=super.orRestant();
		int oldCompteurAbandon=super.compteurAbandon();
		EETAT oldEtat_d_appartenance=super.etat_d_appartenance();
		ERace oldAppartenance=super.appartenance();

		// \pre: d>=0
		assertTrue("\\pre: d>=0", d>=0);

		checkInvariants();
		super.depot(d);
		checkInvariants();

		// \post: orRestant() == (orRestant()@pre +d)
		assertTrue("\\post: orRestant() == (orRestant()@pre +d)", super.orRestant()==(oldOrRestant+d));

		// \post: compteurAbandon() == compteurAbandon()@pre
		assertTrue("\\post: compteurAbandon() == compteurAbandon()@pre", super.compteurAbandon()==oldCompteurAbandon);

		// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
		assertTrue("\\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (super.appartenance()==oldAppartenance));
	}

	@Override
	public void accueil(ERace race) {
		//Captures
		int oldOrRestant=super.orRestant();

		// \pre: etat_d_appartenance()==ETAT.OCCOPE) ==> (race == appartenance())
		assertTrue("\\pre: etat_d_appartenance()==ETAT.OCCOPE) ==> (race == appartenance())", !(super.etat_d_appartenance()==EETAT.OCCUPE) || (race==super.appartenance()));

		checkInvariants();
		super.accueil(race);
		checkInvariants();

		// \post: orRestant() == orRestant()@pre
		assertTrue("\\post: orRestant() == orRestant()@pre", super.orRestant()==oldOrRestant);

		// \post: compteurAbandon() == 0
		assertTrue("\\post: compteurAbandon() == 0", super.compteurAbandon()==0);

		// \post: appartenance() == r
		assertTrue("\\post: appartenance() == race", super.appartenance()==race);
	}

	@Override
	public void abandoned() {
		//Captures
		int oldOrRestant=super.orRestant();
		int oldCompteurAbandon=super.compteurAbandon();
		ERace oldAppartenance=super.appartenance();

		// \pre: etat_d_appartenance() == ETAT.OCCUPE
		assertTrue("\\pre: etat_d_appartenance() == ETAT.OCCUPE",super.etat_d_appartenance()==EETAT.OCCUPE);

		checkInvariants();
		super.abandoned();
		checkInvariants();

		// \post: orRestant() == orRestant()@pre
		assertTrue("\\post: orRestant() == orRestant()@pre", super.orRestant()==oldOrRestant);

		// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
		assertTrue("\\post: compteurAbandon() == (compteurAbandon()@pre + 1)", super.compteurAbandon()==(oldCompteurAbandon+1));

		// \post: appartenance() == appartenance()@pre
		assertTrue("\\post: appartenance() == appartenance()@pre", super.appartenance()==oldAppartenance);
	}

}
