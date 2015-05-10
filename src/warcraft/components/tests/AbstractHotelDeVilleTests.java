package warcraft.components.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.services.IHotelDeVilleService;

public abstract class AbstractHotelDeVilleTests extends AssertionTests {

	protected IHotelDeVilleService hotel;

	@Before
	public abstract void before();

	@After
	public void after() {
		hotel = null;
	}

	public void checkInvariants(String obj){
		// \inv: estLaminee() == (orRestant() == 0)
		assertion(obj+": \\inv: estLaminee() == (orRestant() == 0)", hotel.estLaminee()==(hotel.orRestant()==0));

		// \inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)
		assertion(obj+": \\inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)", (hotel.etat_d_appartenance()==EETAT.LIBRE)==(hotel.compteurAbandon()==51));

		// \inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)
		assertion(obj+": \\inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)", (hotel.etat_d_appartenance()==EETAT.OCCUPE)==(hotel.compteurAbandon()<51));

		// \inv: orRestant()>=0
		assertion(obj+": \\inv: orRestant()>=0", hotel.orRestant()>=0);

		// \inv: 0<=compteurAbandon()<=51
		assertion(obj+": \\inv: 0<=compteurAbandon()<=51", 0<=hotel.compteurAbandon() && hotel.compteurAbandon()<=51);
	}

	/**
	 * Objectif 1: init
	 * 
	 * Cas 1.0: init: positif
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(100,100,ERace.HUMAIN)
	 * 
	 * Oracle:
	 * 	Pas d'exception&&
	 *	estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	 orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 * 	largeur() == largeur &&
	 *  hauteur() == hauteur &&
	 *  orRestant() == 16 &&
	 * 	compteurAbandon() == 0 &&
	 *  race()=ERace.HUMAIN
	 */
	@Test
	public void testInit1_0(){
		String obj="HotelDeVille Test Objectif 1.0";
		int largeur=100;
		int hauteur=100;
		ERace race=ERace.HUMAIN;

		//Condition initial:

		try {
			//Operation:
			hotel.init(largeur, hauteur,race);


			//Oracle:
			checkInvariants(obj);

			// \post: largeur() == largeur
			assertion(obj+": \\post: largeur() == largeur", hotel.largeur()==largeur);

			// \post: hauteur() == hauteur
			assertion(obj+": \\post: hauteur() == hauteur", hotel.hauteur()==hauteur);

			// \post: orRestant() == 500
			assertion(obj+": \\post: orRestant() == 500", hotel.orRestant()==16);

			// \post: compteurAbandon() == 51
			assertion(obj+": \\post: compteurAbandon() == 0", hotel.compteurAbandon()==0);

			// \post: appartenance() == race
			try {
				assertion("\\post: appartenance() == race", hotel.appartenance()==race);
			} catch (Exception e) {
				assertion(obj+": appartenance devrait passer", false);
			}
		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}
	}


	/**
	 * Objectif 1: init
	 * Cas 1.1: init: error largeur
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(-100,100,ERace.HUMAIN)
	 * 
	 * Oracle:
	 * 	Exception pour init
	 */
	@Test
	public void testInit1_1(){
		String obj="HotelDeVille Test Objectif 1.1";
		int largeur=-100;
		int hauteur=100;
		ERace race=ERace.HUMAIN;

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,race);

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
	 * 	init(100,-100,ERace.HUMAIN)
	 * 
	 * Oracle:
	 * 	Exception pour init
	 */
	@Test
	public void testInit1_2(){
		String obj="HotelDeVille Test Objectif 1.2";
		int largeur=100;
		int hauteur=-100;
		ERace race=ERace.HUMAIN;

		//Condition initial:

		try {
			//Operation:
			hotel.init(largeur, hauteur,race);

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
	 *  init(100,100,ERace.HUMAIN)
	 *  
	 * Operation:
	 * 	retrait(10)
	 * 
	 * Oracle:
	 * 	Pas d'exception&&
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
		String obj="HotelDeVille Test Objectif 2.0";
		int s=10;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			//Captures
			int oldOrRestant=hotel.orRestant();
			int oldCompteurAbandon=hotel.compteurAbandon();
			EETAT oldEtat_d_appartenance=hotel.etat_d_appartenance();
			ERace oldAppartenance=null;

			try {
				oldAppartenance = hotel.appartenance();

				try {
					//Operation:
					hotel.retrait(s);

					//Oracle:
					checkInvariants(obj);

					// \post: orRestant() == (orRestant()@pre-s)
					assertion(obj+": \\post: orRestant() == (orRestant()@pre-s)", hotel.orRestant()==(oldOrRestant-s));

					// \post: compteurAbandon() == compteurAbandon()@pre
					assertion(obj+": \\post: compteurAbandon() == compteurAbandon()@pre", hotel.compteurAbandon()==(oldCompteurAbandon));

					// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
					try {
						assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (hotel.appartenance()==oldAppartenance));
					} catch (Exception e) {
						assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", false);
					}
				} catch (Exception e) {
					assertion(obj+e.getMessage(),false);
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
	 *  retrait(init(100,100,ERace.HUMAIN),16)
	 *  
	 * Operation:
	 * 	retrait(1)
	 * 
	 * Oracle:
	 * 	Exception pour retrait
	 */
	@Test
	public void testRetraitError2_1(){
		String obj="HotelDeVille Test Objectif 2.1";
		int s=1;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);
			hotel.retrait(16);

			try {
				//Operation:
				hotel.retrait(s);

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
	 * Objectif 2:  retrait
	 * 
	 * Cas 2.2: retrait: error s<0
	 * Condition initial:
	 *  init(100,100,ERace.HUMAIN)
	 *  
	 * Operation:
	 * 	retrait(-1)
	 * 
	 * Oracle:
	 * 	Exception pour retrait
	 */
	@Test
	public void testRetraitError2_2(){
		String obj="HotelDeVille Test Objectif 2.2";
		int s=-1;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			try {
				//Operation:
				hotel.retrait(s);

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
	 * init(100,100,ERace.HUMAIN)
	 * \for i in range 1 at 51:
	 * 		abandoned()
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
		String obj="HotelDeVille Test Objectif 3.0";
		ERace race=ERace.ORC;


		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);
			for(int i=0;i<51;i++){
				hotel.abandoned();
			}

			//Captures:
			int oldOrRestant=hotel.orRestant();

			//Operation
			try {
				hotel.accueil(race);

				//Oracle

				checkInvariants(obj);

				// \post: orRestant() == orRestant()@pre
				assertion(obj+"\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == 0
				assertion(obj+"\\post: compteurAbandon() == 0", hotel.compteurAbandon()==0);

				// \post: appartenance() == race
				assertion(obj+"\\post: appartenance() == race", hotel.appartenance()==race);

			} catch (Exception e) {
				assertion(obj+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+"erreur lors de l'initialisation du test ="+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: accueil
	 * 
	 * Cas 3_1: accueil: positif etat occupe par meme race
	 * Condition initial:
	 * init(100,100,ERace.ORC)
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
		String obj="HotelDeVille Test Objectif 3.1";
		ERace race=ERace.ORC;

		try {
			//Condition initial:
			hotel.init(100, 100,race);

			//Captures
			int oldOrRestant=hotel.orRestant();

			try {
				//Operation
				hotel.accueil(ERace.ORC);

				//Oracle
				// \post: orRestant() == orRestant()@pre
				assertion(obj+"\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == 0
				assertion(obj+"\\post: compteurAbandon() == 0", hotel.compteurAbandon()==0);

				// \post: appartenance() == r
				assertion(obj+"\\post: appartenance() == race", hotel.appartenance()==race);
			} catch (Exception e) {
				assertion(obj+e.getMessage(),false);
			}
		} catch (Exception e) {
			assertion(obj+"erreur lors de l'initialisation du test ="+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: accueil
	 * 
	 * Cas 3_2: accueil: error etat occupe par une autre race
	 * Condition initial:
	 * init(100,100,ERace.HUMAIN)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * Exception pour acceuil
	 */
	@Test
	public void testAccueil3_2(){
		String obj="HotelDeVille Test Objectif 3.2";
		ERace race=ERace.ORC;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			try {
				//Operation
				hotel.accueil(race);

				//Oracle
				assertion(obj+": acceuil devrait echouer", false);
			} catch (Exception e) {
				assertion(obj, true);
			}
		} catch (Exception e) {
			assertion(obj+"erreur lors de l'initialisation du test ="+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 4: abandoned
	 * 
	 * Cas 4_0: abandoned: positif
	 * Condition initial:
	 * init(100,100,ERace.HUMAIN)
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	pas d'exception &&
	 *  estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == orRestant()@pre &&
	 *	compteurAbandon() == (compteurAbandon()@pre + 1) &&
	 * 	etat_d_appartenance()== ETAT.OCCUPE &&
	 * 	appartenance() == appartenance()@pre
	 */
	@Test
	public void testAccueil4_0(){
		String obj="HotelDeVille Test Objectif 4.0";

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			//Captures:
			int oldOrRestant=hotel.orRestant();
			int oldCompteurAbandon=hotel.compteurAbandon();
			try {
				ERace oldAppartenance=hotel.appartenance();


				try {
					//Operation
					hotel.abandoned();

					//Oracle
					checkInvariants(obj);
					// \post: orRestant() == orRestant()@pre
					assertTrue(obj+"\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

					// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
					assertTrue(obj+"\\post: compteurAbandon() == (compteurAbandon()@pre + 1)", hotel.compteurAbandon()==(oldCompteurAbandon+1));

					// post: etat_d_appartenance()== ETAT.OCCUPE
					assertion(obj+"\\post: etat_d_appartenance()== ETAT.OCCUPE", hotel.etat_d_appartenance()== EETAT.OCCUPE);

					// \post: 	appartenance() == appartenance()@pre
					assertTrue(obj+"\\post: appartenance() == appartenance()@pre", hotel.appartenance()==oldAppartenance);

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
	 * Objectif 4:abandoned
	 * 
	 * Cas 4_1: abandoned: positif devient libre
	 * Condition initial:
	 * init(100,100,ERace.HUMAIN)
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
		String obj="HotelDeVille Test Objectif 4.1";

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);
			for(int i=0;i<50;i++){
				hotel.abandoned();
			}

			//Captures:
			int oldOrRestant=hotel.orRestant();
			int oldCompteurAbandon=hotel.compteurAbandon();

			//Operation
			try {
				hotel.abandoned();

				//Oracle
				checkInvariants(obj);
				// \post: orRestant() == orRestant()@pre
				assertTrue(obj+"\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

				// \post: compteurAbandon() == (compteurAbandon()@pre + 1)
				assertTrue(obj+"\\post: compteurAbandon() == (compteurAbandon()@pre + 1)", hotel.compteurAbandon()==(oldCompteurAbandon+1));

				// \post: etat_d_appartenance()== ETAT.LIBRE
				assertTrue(obj+"\\post: etat_d_appartenance()== ETAT.LIBRE", hotel.etat_d_appartenance()==EETAT.LIBRE);

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
	 * init(100,100,ERace.HUMAIN)
	 * for i in range 0 to 51
	 * 	abandoned()
	 * 
	 * Operation:
	 * abandoned()
	 * 
	 * Oracle:
	 * Exception pour abandoned
	 */
	@Test
	public void testAbandoned4_2(){
		String obj="HotelDeVille Test Objectif 4.2";

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);
			for(int i=0;i<51;i++){
				hotel.abandoned();
			}

			//Operation
			try {
				hotel.abandoned();

				//Oracle
				assertion(obj+": abandoned devrait echouer", false);
			} catch (Exception e) {
				assertion(obj,true);
			}
		} catch (Exception e) {
			assertion(obj+": erreur durant l'initialisation du test: "+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 5: depot
	 * 
	 * Cas 5.0: depot: positif
	 * Condition initial:
	 *  init(100,100,ERace.HUMAIN)
	 *  
	 * Operation:
	 * 	depot(10)
	 * 
	 * Oracle:
	 * 	Pas d'exception&&
	 * 	estLaminee() == (orRestant() == 0) &&
	 *	(etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51) &&
	 *	(etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)&&
	 *	 orRestant()>=0 &&
	 *	0<=compteurAbandon()<=51 &&
	 *	orRestant() == (orRestant()@pre+10) &&
	 *	compteurAbandon() == compteurAbandon()@pre &&
	 *	(etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
	 */

	@Test
	public void testDepot5_0(){
		String obj="HotelDeVille Test Objectif 5.0";
		int d=10;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			//Captures
			int oldOrRestant=hotel.orRestant();
			int oldCompteurAbandon=hotel.compteurAbandon();
			EETAT oldEtat_d_appartenance=hotel.etat_d_appartenance();
			ERace oldAppartenance=null;

			try {
				oldAppartenance = hotel.appartenance();

				try {
					//Operation:
					hotel.depot(d);

					//Oracle:
					assertion(obj,true);
				} catch (Exception e) {
					assertion(obj+e.getMessage(),false);
				}

				checkInvariants(obj);

				// \post: orRestant() == (orRestant()@pre-s)
				assertion(obj+": \\post: orRestant() == (orRestant()@pre-s)", hotel.orRestant()==(oldOrRestant+d));

				// \post: compteurAbandon() == compteurAbandon()@pre
				assertion(obj+": \\post: compteurAbandon() == compteurAbandon()@pre", hotel.compteurAbandon()==(oldCompteurAbandon));

				// \post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)
				try {
					assertion(obj+": \\post: (etat_d_appartenance()@pre== ETAT.OCCUPE) ==> (appartenance()==appartenance()@pre)", !(oldEtat_d_appartenance==EETAT.OCCUPE) || (hotel.appartenance()==oldAppartenance));
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
	 * Objectif 5:  depot
	 * 
	 * Cas 5.1: depot: error d<0
	 * Condition initial:
	 *  init(100,100,ERace.HUMAIN)
	 *  
	 * Operation:
	 * 	depot(-1)
	 * 
	 * Oracle:
	 * 	Exception pour depot
	 */
	@Test
	public void testDepot5_1(){
		String obj="HotelDeVille Test Objectif 5.1";
		int d=-1;

		try {
			//Condition initial:
			hotel.init(100, 100,ERace.HUMAIN);

			try {
				//Operation:
				hotel.retrait(d);

				//Oracle:
				assertion(obj+": retrait devrait échouer", false);

			} catch (Exception e) {
				assertion(obj, true);
			}

		} catch (Exception e1) {
			assertion(obj+": erreur durant l'initialisation du test: "+e1.getMessage(), false);
		}
	}
}
