package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IMineService;

public abstract class AbstractMineTests extends AssertionTests{

	protected IMineService mine;

	@Before
	public abstract void before();

	@After
	public void after() {
		mine = null;
	}

	public void checkInvariants(String obj){
		// \inv: estLaminee() == (orRestant() == 0)
		assertion(obj+": \\inv: estLaminee() == (orRestant() == 0)", mine.estLaminne()==(mine.orRestant()==0));

		// \inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)
		assertion(obj+": \\inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)", (mine.etat_d_appartenance()==EETAT.LIBRE)==(mine.compteurAbandon()==51));

		// \inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)
		assertion(obj+": \\inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)", (mine.etat_d_appartenance()==EETAT.OCCUPE)==(mine.compteurAbandon()<51));

		// \inv: orRestant()>=0
		assertion(obj+": \\inv: orRestant()>=0", mine.orRestant()>=0);

		// \inv: 0<=compteurAbandon()<=51
		assertion(obj+": \\inv: 0<=compteurAbandon()<=51", 0<=mine.compteurAbandon() && mine.compteurAbandon()<=51);
	}

	/**
	 * Objectif 1: pre-condition init
	 * 
	 * Cas 1.0: init: positif
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(100,100)
	 * 
	 * Oracle:
	 * 	Pas d'exception
	 */
	@Test
	public void testInit1_0(){
		String obj="Mine Test Objectif 1.0";
		int largeur=100;
		int hauteur=100;

		//Condition initial:

		//Operation:
		try {
			mine.init(largeur, hauteur);


			//Oracle:
			assertion(obj,true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}




		checkInvariants(obj);

		// \post: largeur() == largeur
		assertion(obj+": \\post: largeur() == largeur", mine.largeur()==largeur);

		// \post: hauteur() == hauteur
		assertion(obj+": \\post: hauteur() == hauteur", mine.hauteur()==hauteur);

		// \post: orRestant() == 500
		assertion(obj+": \\post: orRestant() == 500", mine.orRestant()==500);

		// \post: compteurAbandon() == 51
		assertion(obj+": \\post: compteurAbandon() == 51", mine.compteurAbandon()==51);
	}


	/**
	 * Objectif 1: pre-condition init
	 * Cas 1.1: init: error largeur
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(-100,100)
	 * 
	 * Oracle:
	 * 	Exception pour init
	 */
	@Test
	public void testInitError1_1(){
		String obj="Mine Test Objectif 1.1";
		int largeur=-100;
		int hauteur=100;

		//Condition initial:

		//Operation:
		try {
			mine.init(largeur, hauteur);

			//Oracle:
			assertion(obj+": init devrait échouer", false);
		} catch (Exception e) {
			assertion(obj+": init devrait échouer", true);
		}
	}

	/**
	 * Objectif 1: pre-condition init
	 * 
	 * Cas 1.2: init: error hauteur
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(100,-100)
	 * 
	 * Oracle:
	 * 	Exception pour init
	 */
	@Test
	public void testInitError1_2(){
		String obj="Mine Test Objectif 1.2";
		int largeur=100;
		int hauteur=-100;

		//Condition initial:

		//Operation:
		try {
			mine.init(largeur, hauteur);

			//Oracle:
			assertion(obj+": init devrait échouer", false);
		} catch (Exception e) {
			assertion(obj+": init devrait échouer", true);
		}
	}

	/**
	 * Objectif 2: pre-condition retrait
	 * 
	 * Cas 2.0: retrait: positif
	 * Condition initial:
	 *  init(100,100)
	 *  
	 * Operation:
	 * 	retrait(10)
	 * 
	 * Oracle:
	 * 	Pas d'exception
	 */
	@Test
	public void testRetrait2_0(){
		String obj="Mine Test Objectif 2.0";
		int s=10;
		//Condition initial:
		try {
			mine.init(100, 100);
		} catch (Exception e1) {
		}

		//Captures
		int oldOrRestant=mine.orRestant();
		int oldCompteurAbandon=mine.compteurAbandon();
		EETAT oldEtat_d_appartenance=mine.etat_d_appartenance();
		ERace oldAppartenance=null;

		try {
			oldAppartenance = mine.appartenance();
		} catch (Exception e1) {
		}

		//Operation:
		try {
			mine.retrait(s);

			//Oracle:
			assertion(obj,true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(),false);
		}

		checkInvariants(obj);

		// \post: orRestant() == (orRestant()@pre-s)
		assertion(obj+": \\post: orRestant() == (orRestant()@pre-s)", mine.orRestant()==(oldOrRestant-s));

		// \post: compteurAbandon() == compteurAbandon()@pre
		assertTrue(obj+": \\post: compteurAbandon() == compteurAbandon()@pre", mine.compteurAbandon()==(oldCompteurAbandon));

		// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
		try {
			assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (mine.appartenance()==oldAppartenance));
		} catch (Exception e) {
			assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", false);
		}
	}

	/**
	 * Objectif 2: pre-condition retrait
	 * 
	 * Cas 2.1: retrait: error pas assez d'or
	 * Condition initial:
	 *  retrait(init(100,100),500)
	 *  
	 * Operation:
	 * 	retrait(1)
	 * 
	 * Oracle:
	 * 	Exception pour retrait
	 */
	@Test
	public void testRetraitError2_1(){
		String obj="Mine Test Objectif 2.1";
		int s=1;

		//Condition initial:
		try {
			mine.init(100, 100);
			mine.retrait(500);
		} catch (Exception e1) {
		}

		try {
			mine.retrait(s);
			assertion(obj+": retrait devrait échouer", false);

		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 2: pre-condition retrait
	 * 
	 * Cas 2.2: retrait: error s<0
	 * Condition initial:
	 *  init(100,100)
	 *  
	 * Operation:
	 * 	retrait(-1)
	 * 
	 * Oracle:
	 * 	Exception pour retrait
	 */
	@Test
	public void testRetraitError2_2(){
		String obj="Mine Test Objectif 2.2";
		int s=-1;

		//Condition initial:
		try {
			mine.init(100, 100);
		} catch (Exception e1) {
		}

		try {
			mine.retrait(s);
			assertion(obj+": retrait devrait échouer", false);

		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 3: pre-condition accueil
	 * 
	 * Cas 3_0: accueil: positif etat libre
	 * Condition initial:
	 * init(100,100)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * Pas d'exception
	 */
	@Test
	public void testAccueil3_0(){
		String obj="Mine Test Objectif 3.0";

		//Condition initial:
		try {
			mine.init(100, 100);
		} catch (Exception e) {
		}


		//Operation
		try {
			mine.accueil(ERace.ORC);

			//Oracle
			assertion(obj, true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(),false);
		}
	}

	/**
	 * Objectif 3: pre-condition accueil
	 * 
	 * Cas 3_1: accueil: positif etat occupe par meme race
	 * Condition initial:
	 * acceuil(init(100,100),ERace.ORC)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * Pas d'exception
	 */
	@Test
	public void testAccueil3_1(){
		String obj="Mine Test Objectif 3.1";

		//Condition initial:
		try {
			mine.init(100, 100);
			mine.accueil(ERace.ORC);
		} catch (Exception e) {
		}


		//Operation
		try {
			mine.accueil(ERace.ORC);

			//Oracle
			assertion(obj, true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(),false);
		}
	}

	/**
	 * Objectif 3: pre-condition accueil
	 * 
	 * Cas 3_2: accueil: error etat occupe par une autre race
	 * Condition initial:
	 * acceuil(init(100,100),ERace.HUMAIN)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * Exception pour acceuil
	 */
	@Test
	public void testAccueil3_2(){
		String obj="Mine Test Objectif 3.2";

		//Condition initial:
		try {
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);
		} catch (Exception e) {
		}


		//Operation
		try {
			mine.accueil(ERace.ORC);

			//Oracle
			assertion(obj+": acceuil devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 4: pre-condition accueil
	 * 
	 * Cas 4_0: abandoned: positif
	 * Condition initial:
	 * acceuil(init(100,100),ERace.HUMAIN)
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	 * Exception pour abandoned
	 */
	@Test
	public void testAccueil4_0(){
		String obj="Mine Test Objectif 4.0";

		//Condition initial:
		try {
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);
		} catch (Exception e) {
		}


		//Operation
		try {
			mine.abandoned();

			//Oracle
			assertion(obj, true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(),false);
		}
	}

	/**
	 * Objectif 4: pre-condition accueil
	 * 
	 * Cas 4_1: abandoned: error etat libre
	 * 
	 * Condition initial:
	 * init(100,100)
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	 * Exception pour abandoned
	 */
	@Test
	public void testAccueil4_1(){
		String obj="Mine Test Objectif 4.1";

		//Condition initial:
		try {
			mine.init(100, 100);
		} catch (Exception e) {
		}


		//Operation
		try {
			mine.abandoned();

			//Oracle
			assertion(obj+": abandoned devrait echouer", false);
		} catch (Exception e) {
			assertion(obj,true);
		}
	}

	/**
	 * Objectif 5: pre-condition appartenance
	 * 
	 * Cas 5_0: appartenance: positif
	 * 
	 * Condition initial:
	 * acceuil(init(100,100),ERace.HUMAIN)
	 * 
	 * Operation:
	 * appartenance()
	 * 
	 * Oracle:
	 * Pas d'exception et valeur de retour = ERace.HUMAIN
	 */
	public void testappartenance5_0(){
		String obj="Mine Test Objectif 5.0";

		//Condition initial
		try {
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);
		} catch (Exception e) {
		}

		//Operation
		try {
			ERace val = mine.appartenance();

			//Oracle
			assertion(obj, true);
			assertion(obj, val==ERace.HUMAIN);
		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 5: pre-condition appartenance
	 * 
	 * Cas 5_1: appartenance: error non occupe 
	 * 
	 * Condition initial:
	 * init(100,100)
	 * 
	 * Operation:
	 * appartenance()
	 * 
	 * Oracle:
	 * Exception pour apparetenance
	 */
	public void testappartenance5_1(){
		String obj="Mine Test Objectif 5.1";

		//Condition initial
		try {
			mine.init(100, 100);
		} catch (Exception e) {
		}

		//Operation
		try {
			mine.appartenance();

			//Oracle
			assertion(obj+" appartenance ne devrai pas passer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}
}
