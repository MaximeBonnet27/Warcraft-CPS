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
		assertion(obj+": \\inv: estLaminee() == (orRestant() == 0)", hotel.estLaminne()==(hotel.orRestant()==0));

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
	 * Objectif 1: pre-condition init
	 * 
	 * Cas 1.0: init: positif
	 * Condition initial:
	 *  aucune
	 *  
	 * Operation:
	 * 	init(100,100,ERace.HUMAIN)
	 * 
	 * Oracle:
	 * 	Pas d'exception
	 */
	@Test
	public void testInit1_0(){
		String obj="HotelDeVille Test Objectif 1.0";
		int largeur=100;
		int hauteur=100;
		ERace race=ERace.HUMAIN;

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,race);


			//Oracle:
			assertion(obj,true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(), false);
		}




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
	}


	/**
	 * Objectif 1: pre-condition init
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
	public void testInitError1_1(){
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
	 * 	init(100,-100,ERace.HUMAIN)
	 * 
	 * Oracle:
	 * 	Exception pour init
	 */
	@Test
	public void testInitError1_2(){
		String obj="HotelDeVille Test Objectif 1.2";
		int largeur=100;
		int hauteur=-100;
		ERace race=ERace.HUMAIN;

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,race);

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
	 *  init(100,100,ERace.HUMAIN)
	 *  
	 * Operation:
	 * 	retrait(10)
	 * 
	 * Oracle:
	 * 	Pas d'exception
	 */
	@Test
	public void testRetrait2_0(){
		String obj="HotelDeVille Test Objectif 2.0";
		int s=10;

		//Condition initial:
		try {
			hotel.init(100, 100,ERace.HUMAIN);
		} catch (Exception e1) {
		}

		//Captures
		int oldOrRestant=hotel.orRestant();
		int oldCompteurAbandon=hotel.compteurAbandon();
		EETAT oldEtat_d_appartenance=hotel.etat_d_appartenance();
		ERace oldAppartenance=null;

		try {
			oldAppartenance = hotel.appartenance();
		} catch (Exception e1) {
		}

		//Operation:
		try {
			hotel.retrait(s);

			//Oracle:
			assertion(obj,true);
		} catch (Exception e) {
			assertion(obj+e.getMessage(),false);
		}

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
	}

	/**
	 * Objectif 2: pre-condition retrait
	 * 
	 * Cas 2.1: retrait: error pas assez d'or
	 * Condition initial:
	 *  retrait(init(100,100,ERace.HUMAIN),500)
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

		//Condition initial:
		try {
			hotel.init(100, 100,ERace.HUMAIN);
			hotel.retrait(500);
		} catch (Exception e1) {
		}

		try {
			hotel.retrait(s);
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

		//Condition initial:
		try {
			hotel.init(100, 100,ERace.HUMAIN);
		} catch (Exception e1) {
		}

		try {
			hotel.retrait(s);
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
	 * init(100,100,ERace.HUMAIN)
	 * \for i in range 1 at 51:
	 * 		abandoned()
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * appartenance==ERace.ORC
	 */
	@Test
	public void testAccueil3_0(){
		String obj="HotelDeVille Test Objectif 3.0";
		ERace race=ERace.ORC;

		//Captures
				int oldOrRestant=hotel.orRestant();
				
		//Condition initial:
		try {
			hotel.init(100, 100,ERace.HUMAIN);
			for(int i=0;i<51;i++){
				hotel.abandoned();
			}

			if(hotel.compteurAbandon()==51){
				//Operation
				try {
					hotel.accueil(race);

					//Oracle
					// \post: orRestant() == orRestant()@pre
					assertion("\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

					// \post: compteurAbandon() == 0
					assertion("\\post: compteurAbandon() == 0", hotel.compteurAbandon()==0);

					// \post: appartenance() == r
					assertion("\\post: appartenance() == race", hotel.appartenance()==race);
				} catch (Exception e) {
					assertion(obj+e.getMessage(),false);
				}
			}else{
				assertion(obj+"erreur lors de l'initialisation du test : ne passe pas a l'état LIBRE", false);
			}
		} catch (Exception e) {
			assertion(obj+"erreur lors de l'initialisation du test ="+e.getMessage(), false);
		}
	}

	/**
	 * Objectif 3: pre-condition accueil
	 * 
	 * Cas 3_1: accueil: positif etat occupe par meme race
	 * Condition initial:
	 * init(100,100,ERace.ORC)
	 * 
	 * Operation:
	 * acceuil(ERace.ORC)
	 * 
	 * Oracle:
	 * appartenance=ERace.ORC
	 */
	@Test
	public void testAccueil3_1(){
		String obj="HotelDeVille Test Objectif 3.1";
		ERace race=ERace.ORC;
		
		//Captures
		int oldOrRestant=hotel.orRestant();
		
		//Condition initial:
		try {
			hotel.init(100, 100,race);
		} catch (Exception e) {
		}


		//Operation
		try {
			hotel.accueil(ERace.ORC);

			//Oracle
			// \post: orRestant() == orRestant()@pre
			assertion("\\post: orRestant() == orRestant()@pre", hotel.orRestant()==oldOrRestant);

			// \post: compteurAbandon() == 0
			assertion("\\post: compteurAbandon() == 0", hotel.compteurAbandon()==0);

			// \post: appartenance() == r
			assertion("\\post: appartenance() == race", hotel.appartenance()==race);
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
		ERace race=ERace.ORC;
		
		//Condition initial:
		try {
			hotel.init(100, 100,ERace.HUMAIN);
		} catch (Exception e) {
		}


		//Operation
		try {
			hotel.accueil(race);

			//Oracle
			assertion(obj+": acceuil devrait echouer", false);
		} catch (Exception e) {
			assertion(obj, true);
		}
	}

	/**
	 * Objectif 4: pre-condition abandoned
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
	 * Objectif 4: pre-condition abandoned
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
