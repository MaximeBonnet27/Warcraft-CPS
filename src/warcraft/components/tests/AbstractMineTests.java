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
	 * Objectif 1: init
	 * 
	 * Cas 1.0: init: positif
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(100,100)
	 * 
	 * Oracle:
	 * 	Pas d'exception &&
	 *	estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	 orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 * 	largeur() == largeur &&
	 *  hauteur() == hauteur &&
	 *  orRestant() == 500 &&
	 * 	compteurAbandon() == 51
	 * 
	 */
	@Test
	public void testInit1_0(){
		String obj="Mine Test Objectif 1.0";
		int largeur=100;
		int hauteur=100;

		//Condition initial:


		try {
			//Operation:
			mine.init(largeur, hauteur);


			//Oracle:
			checkInvariants(obj);

			// \post: largeur() == largeur
			assertion(obj+": \\post: largeur() == largeur", mine.largeur()==largeur);

			// \post: hauteur() == hauteur
			assertion(obj+": \\post: hauteur() == hauteur", mine.hauteur()==hauteur);

			// \post: orRestant() == 500
			assertion(obj+": \\post: orRestant() == 500", mine.orRestant()==500);

			// \post: compteurAbandon() == 51
			assertion(obj+": \\post: compteurAbandon() == 51", mine.compteurAbandon()==51);
		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}





	}


	/**
	 * Objectif 1:init
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


		try {
			//Operation:
			mine.init(largeur, hauteur);

			//Oracle:
			assertion(obj+": init devrait échouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 1:init
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


		try {
			//Operation:
			mine.init(largeur, hauteur);

			//Oracle:
			assertion(obj+": init devrait échouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 2: retrait
	 * 
	 * Cas 2.0: retrait: positif
	 * Condition initial:
	 *  init(100,100)
	 *  
	 * Operation:
	 * 	retrait(10)
	 * 
	 * Oracle:
	 * 	Pas d'exception &&
	 * 	estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	 orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == (orRestant()@pre-10) &&
	 *	compteurAbandon() == compteurAbandon()@pre &&
	 *	(etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
	 */
	@Test
	public void testRetrait2_0(){
		String obj="Mine Test Objectif 2.0";
		int s=10;

		try {
			//Condition initial:
			mine.init(100, 100);

			//Captures
			int oldOrRestant=mine.orRestant();
			int oldCompteurAbandon=mine.compteurAbandon();
			EETAT oldEtat_d_appartenance=mine.etat_d_appartenance();
			ERace oldAppartenance=null;

			try {
				oldAppartenance = mine.appartenance();
				try {
					//Operation:
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
				assertion(obj+": \\post: compteurAbandon() == compteurAbandon()@pre", mine.compteurAbandon()==(oldCompteurAbandon));

				// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
				try {
					assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (mine.appartenance()==oldAppartenance));
				} catch (Exception e) {
					assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", false);
				}
			} catch (Exception e1) {
				assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
			}

		} catch (Exception e1) {
			assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
		}
	}

	/**
	 * Objectif 2: retrait
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


		try {
			//Condition initial:
			mine.init(100, 100);
			mine.retrait(500);

			try {
				//Operation:
				mine.retrait(s);

				//Oracle:
				assertion(obj+": retrait devrait échouer", false);

			} catch (Exception e) {
				assertion(obj, true);
			}
		} catch (Exception e1) {
			assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
		}


	}

	/**
	 * Objectif 2: retrait
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

		try {
			//Condition initial:
			mine.init(100, 100);

			try {
				//Operation:
				mine.retrait(s);

				//Oracle:
				assertion(obj+": retrait devrait échouer", false);

			} catch (Exception e) {
				assertion(obj, true);
			}

		} catch (Exception e1) {
			assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: accueil
	 * 
	 * Cas 3_0: accueil: positif etat libre
	 * Condition initial:
	 * init(100,100)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * pas d'exception &&
	 * estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == orRestant()@pre &&
	 *	compteurAbandon() == 0 &&
	 * 	appartenance=ERace.ORC
	 */
	@Test
	public void testAccueil3_0(){
		String obj="Mine Test Objectif 3.0";
		ERace race=ERace.ORC;

		try {
			//Condition initial:
			mine.init(100, 100);

			//Captures:
			int oldOrRestant=mine.orRestant();

			try {
				//Operation
				mine.accueil(race);

				//Oracle
				checkInvariants(obj);
				// \post: orRestant() == orRestant()@pre
				assertTrue(obj+"\\post: orRestant() == orRestant()@pre", mine.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == 0
				assertTrue(obj+"\\post: compteurAbandon() == 0", mine.compteurAbandon()==0);

				// \post: appartenance() == r
				assertTrue(obj+"\\post: appartenance() == race", mine.appartenance()==race);
			} catch (Exception e) {
				assertion(obj+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: accueil
	 * 
	 * Cas 3_1: accueil: positif etat occupe par meme race
	 * Condition initial:
	 * acceuil(init(100,100),ERace.ORC)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * pas d'exception &&
	 * estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == orRestant()@pre &&
	 *	compteurAbandon() == 0 &&
	 * 	appartenance=ERace.ORC
	 */
	@Test
	public void testAccueil3_1(){
		String obj="Mine Test Objectif 3.1";
		ERace race=ERace.ORC;



		try {
			//Condition initial:
			mine.init(100, 100);
			mine.accueil(race);

			//Captures
			int oldOrRestant=mine.orRestant();

			try {
				//Operation
				mine.accueil(race);

				//Oracle
				checkInvariants(obj);

				// \post: orRestant() == orRestant()@pre
				assertTrue(obj+"\\post: orRestant() == orRestant()@pre", mine.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == 0
				assertTrue(obj+"\\post: compteurAbandon() == 0", mine.compteurAbandon()==0);

				// \post: appartenance() == r
				assertTrue(obj+"\\post: appartenance() == race", mine.appartenance()==race);
			} catch (Exception e) {
				assertion(obj+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3:accueil
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

		try {
			//Condition initial:
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);


			try {
				//Operation
				mine.accueil(ERace.ORC);

				//Oracle
				assertion(obj+": acceuil devrait echouer", false);
			} catch (Exception e) {
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: abandoned
	 * 
	 * Cas 4_0: abandoned: positif reste occupe
	 * Condition initial:
	 * acceuil(init(100,100),ERace.HUMAIN)
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	 * pas d'exception &&
	 *  estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == orRestant()@pre &&
	 *	compteurAbandon() == (compteurAbandon()@pre + 1) &&
	 * 	etat_d_appartenance()== ETAT.OCCUPE &&
	 * 	appartenance() == appartenance()@pre
	 *	
	 */
	@Test
	public void testAbandoned4_0(){
		String obj="Mine Test Objectif 4.0";

		try {
			//Condition initial:
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);

			//Captures:
			int oldOrRestant=mine.orRestant();
			int oldCompteurAbandon=mine.compteurAbandon();
			try {
				ERace oldAppartenance=mine.appartenance();


				try {
					//Operation
					mine.abandoned();

					//Oracle
					checkInvariants(obj);
					// \post: orRestant() == orRestant()@pre
					assertTrue(obj+"\\post: orRestant() == orRestant()@pre", mine.orRestant()==oldOrRestant);

					// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
					assertTrue(obj+"\\post: compteurAbandon() == (compteurAbandon()@pre + 1)", mine.compteurAbandon()==(oldCompteurAbandon+1));

					// post: etat_d_appartenance()== ETAT.OCCUPE
					assertion(obj+"\\post: etat_d_appartenance()== ETAT.OCCUPE", mine.etat_d_appartenance()== EETAT.OCCUPE);

					// \post: 	appartenance() == appartenance()@pre
					assertTrue(obj+"\\post: appartenance() == appartenance()@pre", mine.appartenance()==oldAppartenance);

				} catch (Exception e) {
					assertion(obj+e.getMessage(),false);
				}
			} catch (Exception e) {
				assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
			}
		} catch (Exception e1) {
			assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: abandoned
	 * 
	 * Cas 4_1: abandoned: positif devient libre
	 * Condition initial:
	 * acceuil(init(100,100),ERace.HUMAIN)
	 * for i in range 1 to 51
	 * 	abandoned()
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	 * pas d'exception &&
	 *  estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == orRestant()@pre &&
	 *	compteurAbandon() == (compteurAbandon()@pre + 1) &&
	 * 	etat_d_appartenance()== ETAT.LIBRE
	 *	
	 */
	@Test
	public void testAbandoned4_1(){
		String obj="Mine Test Objectif 4.1";

		try {
			//Condition initial:
			mine.init(100, 100);
			mine.accueil(ERace.HUMAIN);
			for(int i=0;i<50;i++){
				mine.abandoned();
			}

			//Captures:
			int oldOrRestant=mine.orRestant();
			int oldCompteurAbandon=mine.compteurAbandon();
			//Operation
			try {
				mine.abandoned();

				//Oracle
				checkInvariants(obj);
				// \post: orRestant() == orRestant()@pre
				assertTrue(obj+"\\post: orRestant() == orRestant()@pre", mine.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
				assertTrue(obj+"\\post: compteurAbandon() == (compteurAbandon()@pre + 1)", mine.compteurAbandon()==(oldCompteurAbandon+1));

				// \post: etat_d_appartenance()== ETAT.LIBRE
				assertTrue(obj+"\\post: etat_d_appartenance()== ETAT.LIBRE", mine.etat_d_appartenance()==EETAT.LIBRE);

			} catch (Exception e) {
				assertion(obj+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: abandoned
	 * 
	 * Cas 4_2: abandoned: error etat libre
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
	public void testAbandoned4_2(){
		String obj="Mine Test Objectif 4.2";

		try {
			//Condition initial:
			mine.init(100, 100);

			//Operation
			try {
				mine.abandoned();

				//Oracle
				assertion(obj+": abandoned devrait echouer", false);
			} catch (Exception e) {
				assertion(obj,true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

}
