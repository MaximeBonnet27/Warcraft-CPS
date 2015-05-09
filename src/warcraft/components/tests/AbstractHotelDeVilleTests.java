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
		assertTrue(obj+": \\inv: estLaminee() == (orRestant() == 0)", hotel.estLaminne()==(hotel.orRestant()==0));

		// \inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)
		assertTrue(obj+": \\inv: (etat_d_appartenance() == ETAT.LIBRE) == (compteurAbandon()=51)", (hotel.etat_d_appartenance()==EETAT.LIBRE)==(hotel.compteurAbandon()==51));

		// \inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)
		assertTrue(obj+": \\inv: (etat_d_appartenance() == ETAT.OCCUPE) == (compteurAbandon()<51)", (hotel.etat_d_appartenance()==EETAT.OCCUPE)==(hotel.compteurAbandon()<51));

		// \inv: orRestant()>=0
		assertTrue(obj+": \\inv: orRestant()>=0", hotel.orRestant()>=0);

		// \inv: 0<=compteurAbandon()<=51
		assertTrue(obj+": \\inv: 0<=compteurAbandon()<=51", 0<=hotel.compteurAbandon() && hotel.compteurAbandon()<=51);
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

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,ERace.HUMAIN);


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
		assertion(obj+": \\post: compteurAbandon() == 51", hotel.compteurAbandon()==0);
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

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,ERace.HUMAIN);

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

		//Condition initial:

		//Operation:
		try {
			hotel.init(largeur, hauteur,ERace.HUMAIN);

			//Oracle:
			assertion(obj+": init devrait échouer", false);
		} catch (Exception e) {
			assertion(obj+": init devrait échouer", true);
		}
	}
}
