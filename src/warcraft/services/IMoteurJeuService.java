/**
 * 
 */
package warcraft.services;

import java.util.Set;

import warcraft.enums.EResultat;

public interface IMoteurJeuService {

	// Observators
	public int largeurTerrain();
	public int hauteurTerrain();
	public int maxPasJeu();
	public boolean estFini();
	/**
	 * \pre : estFini()
	 */
	public EResultat resultatFinal();


	public Set<Integer> numeroesVillageois();
	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == num
	 */
	public IVillageoisService getVillageois(int num);
	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == num
	 */
	public int positionVillageoisX(int num);
	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == num
	 */
	public int positionVillageoisY(int num);
	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == num
	 * \pre : getVillageois(num).enCorvee()
	 */
	public IMineService getMineVillageois(int num);


	public Set<Integer> numeroesMine();
	/**
	 * \pre : \exists i \in numeroesMine() \tq i == num
	 */
	public IMineService getMine(int num);
	/**
	 * \pre : \exists i \in numeroesMine() \tq i == num
	 */
	public int positionMineX(int num);
	/**
	 * \pre : \exists i \in numeroesMine() \tq i == num
	 */
	public int positionMineY(int num);


	public Set<Integer> numeroesRoute();
	/**
	 * \pre : \exists i \in numeroesRoute() \tq i == num
	 */
	public IRouteService getRoute(int num);
	/**
	 * \pre : \exists i \in numeroesRoute() \tq i == num
	 */
	public int positionRouteX(int num);
	/**
	 * \pre : \exists i \in numeroesRoute() \tq i == num
	 */
	public int positionRouteY(int num);

	public Set<Integer> numeroesMuraille();
	/**
	 * \pre : \exists i \in numeroesMuraille() \tq i == num
	 */
	public IMurailleService getMuraille(int num);
	/**
	 * \pre : \exists i \in numeroesMuraille() \tq i == num
	 */
	public int positionMurailleX(int num);
	/**
	 * \pre : \exists i \in numeroesMuraille() \tq i == num
	 */
	public int positionMurailleY(int num);


	public IHotelDeVilleService hotelDeVilleA();
	public IHotelDeVilleService hotelDeVilleB();
	/**
	 * \pre : numHotel == 0 || numHotel == 1
	 */
	public IHotelDeVilleService getHotel(int numHotel);
	public int positionHotelVilleXA();
	public int positionHotelVilleYA();
	public int positionHotelVilleXB();
	public int positionHotelVilleYB();
	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == numVillageois
	 * \pre : \exists i \in numeroesMine() \tq i == numMine
	 */
	public boolean peutEntrer(int numVillageois, int numMine);

	/**
	 * \pre : \exists i \in numeroesVillageois() \tq i == numVillageois
	 * \pre : numHotel == 0 || numHotel == 1
	 */
	public boolean peutEntrerHotelVille(int numVillageois, int numHotel);
	
	// Invariants
	//TODO conditions de victoire peut-être à revoir.
	/**
	 * \inv : 0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu();
	 * \inv : estFini() == 	(hotelDeVilleA().orRestant() >= 1664) ||
	 * 						(hotelDeVilleB().orRestant() >= 1664) ||
	 * 						((hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) &&
	 * 							(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) &&
	 * 							 (hotelDeVilleA().appartenance() == hotelDeVilleB().appartenance()))
	 * \inv : resultatFinal() == EResultat.ORC \ssi 
	 * 			  (	(hotelDeVilleA().orRestant() >= 1664) &&
	 * 				(hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleA().appartenance() == ERACE.ORC)	)
	 * 			||( (hotelDeVilleB().orRestant() >= 1664) &&
	 * 				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleB().appartenance() == ERACE.ORC)	)
	 * 			||( (hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleA().appartenance() == ERACE.ORC) &&
	 * 				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleB().appartenance() == ERACE.ORC)	)
	 * 
	 * \inv : resultatFinal() == EResultat.HUMAIN \ssi 
	 * 			  (	(hotelDeVilleA().orRestant() >= 1664) &&
	 * 				(hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleA().appartenance() == ERACE.HUMAIN)	)
	 * 			||( (hotelDeVilleB().orRestant() >= 1664) &&
	 * 				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleB().appartenance() == ERACE.HUMAIN)	)
	 * 			||( (hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleA().appartenance() == ERACE.HUMAIN) &&
	 * 				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	 * 				(hotelDeVilleB().appartenance() == ERACE.HUMAIN)	)
	 * 
	 * \inv : resultatFinal() == EResultat.NULL \ssi
	 * 				hotelDeVilleA().orRestant() < 1664 &&
	 * 				hotelDeVilleB().orRestant() < 1664 && 
	 * 				hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE &&
	 * 				hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE &&	
	 * 				hotelDeVilleA().appartenance() != hotelDeVilleB().appartenance()
	 * 
	 * \inv : peutEntrer(numVillageois, numMine) ==
	 * 			distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
	 * 					 positionMineX(numMine), positionMineY(numMine)) <= 51
	 * 			&& (getMine(numMine).etat_d_appartenance() == EETAT.OCCUPE 
	 * 				=> getMine(numMine).appartenance() == getVillageois(numVillageois).race())	
	 * 
	 * \inv : peutEntrerHotelVille(numVillageois, numHotel) == 
	 * 			numHotel == 0 => distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
	 * 					 		positionHotelVilleXA, positionHotelVilleYA) <= 51
	 * 							&&
	 * 							getHotelVilleA().etat_d_appartenance() == ETAT.OCCUPE
	 * 							=> getHotelVilleA().appartenance() == getVillageois(numVillageois).race()
	 *		 && numHotel == 1 => distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
	 * 					 		positionHotelVilleXB, positionHotelVilleYB) <= 51
	 * 							&&
	 * 							getHotelVilleB().etat_d_appartenance() == ETAT.OCCUPE
	 * 							=> getHotelVilleB().appartenance() == getVillageois(numVillageois).race()
	 *
	 */ 
	// Constructors

	// Operators

}
