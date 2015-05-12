/**
 * 
 */
package warcraft.services;

import java.util.Set;

import warcraft.enums.ECommande;
import warcraft.enums.EResultat;

public interface IMoteurJeuService {

	// Observators
	public int largeurTerrain();
	public int hauteurTerrain();
	public int maxPasJeu();
	public int pasJeuCourant();
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
	
	/**
	 * \pre : \exists i \in numeroesRoute() \tq i == r
	 */
	public boolean villageoisSurRoute(int x,int y,int l,int h,int r);

	/**
	 * \pre : \exists i \in numeroesMuraille() \tq i == m
	 */
	public boolean villageoisSurMuraille(int x,int y,int l,int h,int m);
	
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
	
	public ICentreNationalRechercheSpecialeService CNRSA();
	public ICentreNationalRechercheSpecialeService CNRSB();
	
	// Invariants
	/**
	 * \inv : 0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu();
	 * \inv : estFini() == 	(hotelDeVilleA().orRestant() >= 1664 && hotelDeVilleA().etat_d_appartenance()==EETAT.OCCUPE) ||
	 * 						(hotelDeVilleB().orRestant() >= 1664 && hotelDeVilleB().etat_d_appartenance()==EETAT.OCCUPE) ||
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
	 * 							=> getHotelVilleB<().appartenance() == getVillageois(numVillageois).race()
	 *
	 * \inv: villageoisSurRoute(M,x,y,l,h,r) == collision(x,y,l,h,positionRouteX(r),positionRouteY(r),getRoute(r).largeur(),getRoute(r).hauteur())
	 * 
	 * \inv: villageoisSurMuraille(M,x,y,l,h,m) == collision(x,y,l,h,positionMurailleX(m),positionMurailleY(m),getMuraille(m).largeur(),getMuraille(m).hauteur())
	 */ 
	
	// Constructors

	
	/**
	* \pre: largeur >=600
	* \pre: hauteur >=400
	* \pre: maxPas >= 0
	* 
	* \post: largeur() == largeur
	* \post: hauteur() == hauteur
	* \post: maxPasJeu() == maxPas
	* \post: pasJeuCourant() == 0
	* \post: numerosVillageois() == {0,...,199}
	* \post: \foreach i \in numeroesVillageois():
	* 			\if i<100 \then
	* 				getVillageois(i)=new Villageois().init(ERace.ORC,3,3,20,1,50) &&
	* 				distance(positionVillageoisX(i),positionVillageoisY(i), positionHotelVilleXA(), positionHotelVilleYA()) ≤ 51
	* 			\else
	* 				getVillageois(i)=new Villageois().init(ERace.ORC,3,3,10,1,100) &&
	* 				distance(positionVillageoisX(i),positionVillageoisY(i), positionHotelVilleXB(), positionHotelVilleYB()) ≤ 51
	* 
	* \post: numeroesMines()={0,...,19}
	* \post: \foreach i \in numeroesMines():
	* 			getMine(i)=new Mine().init(50,50) &&
	* 			positionMineX(i) = random(largeurTerrain()) &&
	* 			positionMineY(i) = random(hauteurTerrain())
	* 
	* \post: numeroesRoute()={0,...,99}
	* \post: \foreach i \in numeroesRoute():
	* 			getRoute(i)=new Route().init(5,5,2) &&
	* 			positionRouteX(i) = random(largeurTerrain()) &&
	* 			positionRouteY(i) = random(hauteurTerrain())
	* 
	* \post: numeroesMuraille()={0,...,49}
	* \post: \foreach i \in numeroesMuraille():
	* 			getMuraille(i)=new Muraille().init(5,5,10) &&
	* 			positionMurailleX(i) = random(largeurTerrain()) &&
	* 			positionMurailleY(i) = random(hauteurTerrain())
	* 
	* \post: hotelDeVilleA() == new HotelDeVille().init(ERace.ORC,100,100)
	* \post: hotelDeVilleB() == new HotelDeVille().init(ERace.HUMAIN,100,100)
	* 
	* \post: positionHotelDeVilleXA() == 0
	* \post: positionHotelDeVilleYA() == 0
	* 
	* \post: positionHotelDeVilleXB() == largeurTerrain()-hotelDeVilleB().largeur()-1
	* \post: positionHotelDeVilleYB() == hauteurTerrain()-hotelDeVilleB().hauteur()-1
	* 
	* \post: CNRSA() == new CentreNationalRechercheSpeciale().init(100,25,500,100)
	* \post: CNRSB() == new CentreNationalRechercheSpeciale().init(100,25,500,100)
	*/
	public void init(int largeur,int hauteur,int maxPas);
	
	
	// Operators

	/**
	 * \pre : !estFini()
	 * \pre : \foreach i \in s1 :
	 * 			i \in numeroesVillageois() &&
	 * 			getVillageois(i).race() == ERace.HUMAIN &&
	 * 			(c1 == ECommande.DEPLACER => (0 <= a1 && a1 <= 360)) &&
	 * 			(c1 == ECommande.ENTRERMINE => (a1 \in numeroesMine() && peutEntrer(i, a1))) &&
	 * 			(c1 == ECommande.ENTRERHOTELVILLE => (a1 == 0 || a1 == 1) && peutEntrerHotelVille(i, a1))
	 * 			(c1 == ECommande.CONSTRUIRECNRS => (a1 == 0 || a1 == 1) && getHotel(a1).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a1).appartenance() == getVillageois(i).race())
	 * 			(c1 == ECommande.RECHERCHECNRS => (a1 == 0 || a1 == 1) && getHotel(a1).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a1).appartenance() == getVillageois(i).race())
	 * 			(c1 == ECommande.ATTAQUER => (100 <= a1 && a1 <= 199)
	 * \pre : \foreach i \in s2 :
	 * 			i \in numeroesVillageois() &&
	 * 			getVillageois(i).race() == ERace.ORC &&
	 * 			(c2 == ECommande.DEPLACER => (0 <= a2 && a2 <= 360)) &&
	 * 			(c2 == ECommande.ENTRERMINE => (a2 \in numeroesMine() && peutEntrer(i, a2))) &&
	 * 			(c2 == ECommande.ENTRERHOTELVILLE => (a2 == 0 || a2 == 1) && peutEntrerHotelVille(i, a2))
	 * 			(c2 == ECommande.CONSTRUIRECNRS => (a2 == 0 || a2 == 1) && getHotel(a2).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a2).appartenance() == getVillageois(i).race())
	 * 			(c2 == ECommande.RECHERCHECNRS => (a2 == 0 || a2 == 1) && getHotel(a2).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a2).appartenance() == getVillageois(i).race())
	 * 			(c2 == ECommande.ATTAQUER => (100 <= a2 && a2 <= 199)
	 * 
	 * \post : pasJeuCourant() == pasJeuCourant()@pre + 1
	 * \post : \foreach i \in numeroesVillageois() :
	 * 			getVillageois(i) == 
	 * 				\if  getVillageois(i)@pre.estMort() \then
	 * 					getVillageois(i)@pre
	 * 				\elseif i \in s1 \then
	 * 					   \if c1 == ECommande.ENTRERMINE \then
	 * 							peutEntrer(i, a1)@pre => getVillageois()@pre.commenceTravail()
	 * 							!peutEntrer(i, a1)@pre => getVillageois()@pre
	 * 						\elseif c1 == ECommande.ENTRERHOTELVILLE \then
	 * 							peutEntrerHotelVille(i, a1)]@pre => getVillageois(i)@pre.retraitOr(getVillageois(i)@pre.quantiteOr())
	 * 							!peutEntrerHotelVille(i, a1)@pre => getVillageois(i)@pre
	 * 						\elseif c1 == ECommande.APPLIQUERRECHERCHE \then
	 * 							\if (getHotel(a1)@pre == hotelDeVilleA()@pre  &&
	 * 								 getHotel(a1)@pre.etat_d_appartenance()==EETAT.OCCUPE &&
	 * 								 getHotel(a1)@pre.appartenance==getVillageois(s1.get(0))@pre.race() &&
	 * 								 CNRSA()@pre.rechercheFinie()@pre) \then
	 * 										getVillageois(i)@pre.amelioration(CNRSA()@pre.competenceAugmente(),CNRSA()@pre.boost()
	 * 							\elsefi (getHotel(a1)@pre == hotelDeVilleB()@pre  &&
	 * 								 getHotel(a1)@pre.etat_d_appartenance()==EETAT.OCCUPE &&
	 * 								 getHotel(a1)@pre.appartenance==getVillageois(s1.get(0))@pre.race() &&
	 * 								 CNRSB()@pre.rechercheFinie()@pre) \then
	 * 										getVillageois(i)@pre.amelioration(CNRSB()@pre.competenceAugmente(),CNRSB()@pre.boost()
	 *  						\else
	 *  							getVillageois(i)@pre
	 *  					\elseif c1 == ECommande.ATTAQUER \then
	 *  						new GestionCombat().init(getVillageois(i)@pre,getVillageois(a1)).combat().attaquant()
	 *  					\elseif c2 =ECommande.ATTAQUER && i==a2 \then
	 *  						new GestionCombat().init(s2,getVillageois(i)@pre).combat().defenseur()
	 * 	 					\else
	 * 							getVillageois(i)@pre
	 * 
	 * 				\elseif i \in s2 \then
	 * 					   \if c2 == ECommande.ENTRERMINE \then
	 * 							peutEntrer(i, a2)@pre => getVillageois()@pre.commenceTravail()
	 * 							!peutEntrer(i, a2)@pre => getVillageois()@pre
	 * 						\elseif c2 == ECommande.ENTRERHOTELVILLE \then
	 * 							peutEntrerHotelVille(i, a2)]@pre => getVillageois(i)@pre.retraitOr(getVillageois(i)@pre.quantiteOr())
	 * 							!peutEntrerHotelVille(i, a2)@pre => getVillageois(i)@pre
	 * 						\elseif c2 == ECommande.APPLIQUERRECHERCHE \then
	 * 							\if (getHotel(a2)@pre == hotelDeVilleA()@pre  &&
	 * 								 getHotel(a2)@pre.etat_d_appartenance()==EETAT.OCCUPE &&
	 * 								 getHotel(a2)@pre.appartenance==getVillageois(s2.get(0))@pre.race() &&
	 * 								 CNRSA()@pre.rechercheFinie()@pre) \then
	 * 										getVillageois(i)@pre.amelioration(CNRSA()@pre.competenceAugmente(),CNRSA()@pre.boost()
	 * 							\elsefi (getHotel(a2)@pre == hotelDeVilleB()@pre  &&
	 * 								 getHotel(a2)@pre.etat_d_appartenance()==EETAT.OCCUPE &&
	 * 								 getHotel(a2)@pre.appartenance==getVillageois(s2.get(0))@pre.race() &&
	 * 								 CNRSB()@pre.rechercheFinie()@pre) \then
	 * 										getVillageois(i)@pre.amelioration(CNRSB()@pre.competenceAugmente(),CNRSB()@pre.boost()
	 *  						\else
	 *  							getVillageois(i)@pre
	 *  					\elseif c2 == ECommande.ATTAQUER \then
	 *  						new GestionCombat().init(getVillageois(i)@pre,getVillageois(a2)).combat().attaquant()
	 *  					\elseif c1 =ECommande.ATTAQUER && i==a1 \then
	 *  						new GestionCombat().init(s1,getVillageois(i)@pre).combat().defenseur()
	 * 	 					\else
	 * 							getVillageois(i)@pre
	 * 
	 * 				\elseif getVillageois(i)@pre.enCorvee() \then
	 * 					\if getVillageois(i)@pre.corveeFini() \then
	 * 						\if !getMineVillagois()@pre.estlaminee() \then
	 * 							getVillageois(i)@pre.finirTravail().ajouteOr()
	 * 						\else
	 * 							getVillageois(i)@pre.finirTravail()
	 * 					\else
	 * 						getVillageois(i)@pre.travail()
	 * 				\else
	 * 					getVillageois(i)@pre
	 * 
	 *  \post : \foreach i \in numeroesVillageois() :
	 * 				positionVillageoisX()=
	 * 					\if i \in s1 && c1==ECommande.DEPLACER \then
	 * 						\if \exist num_route \with villageoisSurRoute(positionVillageoisX(i)@pre,
	 * 																	positionVillageoisY(i)@pre,
	 * 																	getVillageois(i)@pre.largeur(),
	 * 																	getVillageois(i)@pre.hauteur(),
	 * 																	num_route) \then
	 * 							\if \exist num_muraille \with villageoisSurMuraille(positionVillageoisX(i)@pre+cos(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur(),
	 * 																				positionVillageoisY(i)@pre+sin(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur(),
	 * 																				getVillageois(i)@pre.largeur(),
	 * 																				getVillageois(i)@pre.hauteur(),
	 * 																				num_muraille) \then
	 * 								positionVillageoisX()@pre
	 * 							\else
	 * 								positionVillageoisX()@pre+cos(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur()
	 * 						\else
	 * 							\if \exist num_muraille \with villageoisSurMuraille(positionVillageoisX(i)@pre+cos(a1)*getVillageois(i).vitesse()),
	 * 																				positionVillageoisY(i)@pre+sin(a1)*getVillageois(i).vitesse(),
	 * 																				getVillageois(i)@pre.largeur(),
	 * 																				getVillageois(i)@pre.hauteur(),
	 * 																				num_muraille) \then
	 * 								positionVillageoisX()@pre
	 * 							\else
	 * 								positionVillageoisX()@pre+cos(a1)*getVillageois(i).vitesse()
	 * 
	 *  \post : \foreach i \in numeroesVillageois() :
	 * 				positionVillageoisY()=
	 * 					\if i \in s1 && c1==ECommande.DEPLACER \then
	 * 						\if \exist num_route \with villageoisSurRoute(positionVillageoisX(i)@pre,
	 * 																	positionVillageoisY(i)@pre,
	 * 																	getVillageois(i)@pre.largeur(),
	 * 																	getVillageois(i)@pre.hauteur(),
	 * 																	num_route) \then
	 * 							\if \exist num_muraille \with villageoisSurMuraille(positionVillageoisX(i)@pre+cos(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur(),
	 * 																				positionVillageoisY(i)@pre+sin(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur(),
	 * 																				getVillageois(i)@pre.largeur(),
	 * 																				getVillageois(i)@pre.hauteur(),
	 * 																				num_muraille) \then
	 * 								positionVillageoisY()@pre
	 * 							\else
	 * 								positionVillageoisY()@pre+sin(a1)*getVillageois(i).vitesse()*getRoute(num_route).multiplicateur()
	 * 						\else
	 * 							\if \exist num_muraille \with villageoisSurMuraille(positionVillageoisX(i)@pre+cos(a1)*getVillageois(i).vitesse()),
	 * 																				positionVillageoisY(i)@pre+sin(a1)*getVillageois(i).vitesse(),
	 * 																				getVillageois(i)@pre.largeur(),
	 * 																				getVillageois(i)@pre.hauteur(),
	 * 																				num_muraille) \then
	 * 								positionVillageoisY()@pre
	 * 							\else
	 * 								positionVillageoisY()@pre+sin(a1)*getVillageois(i).vitesse()
	 * 	\post : \foreach i \in numeroesVillageois() :
	 * 				getMineVillageois(i)=
	 * 					\if i \in s1 && c1==ECommmande.ENTRERMINE \then
	 * 						peutEntrer(i,a1)@pre => getMine(a1)@pre.accueil(getVillageois(i)@pre.race())
	 * 						!peutEntrer(i,a1)@pre => getMine(a1)@pre
	 * 				
	 * 					\elsif i \in s2 && c2==ECommmande.ENTRERMINE \then
	 * 						peutEntrer(i,a2)@pre => getMine(a2)@pre.accueil(getVillageois(i)@pre.race())
	 * 						!peutEntrer(i,a2)@pre => getMine(a2)@pre
	 * 
	 * 					\elseif getVillageois(i)@pre.enCorvee() \then
	 * 						\if getVillageois(i)@pre.corveeFini() \then
	 * 							\if getMineVillageois(i)@pre.estLaminne() \then
	 * 								\if \exist n \in numeroesVilalgeois()@pre/i \with getMineVillageois(n)@pre = getMineVillageois(i)@pre \then
	 * 									getMineVillageois(i)@pre.retrait(1)
	 * 								\else
	 * 									getMineVillageois(i)@pre.retrait(1).abandoned()
	 * 							\elseif
	 * 								\if \exist n \in numeroesVilalgeois()@pre/i \with getMineVillageois(n)@pre = getMineVillageois(i)@pre \then
	 * 									getMineVillageois(i)@pre
	 * 								\else
	 * 									getMineVillageois(i)@pre.abandoned()
	 * 		
	 *  \post : \foreach i \in numeroesMine() :
	 *  			getMine(i)=
	 *  				\if getMine(i)@pre.etat_d_appartenance()=EEtat.OCCUPE \then
	 *  					\foreach num \in numeroesVillagoies()@pre:
	 *  						\if getMineVillageois(num)@pre = getMine(i)@pre \then
	 *  							\if getVillageois(num)@pre.enCorvee() && getVillageois(num)@pre.corveeFini() \then
	 *  								\if !getMineVillageois(num)@pre.estLaminee() \then
	 *  									\if \exist n \in numeroesVillageois()@pre/i \with getMineVillageois(num)@pre==getMineVillageois(n)@pre \then
	 *  										getMine(i)@pre.retrait(1)
	 *  									\else
	 *  										getMine(i)@pre.retrait(1).abandoned()
	 *  								\else
	 *  									\if \exist n \in numeroesVillageois()@pre/i \with getMineVillageois(num)@pre==getMineVillageois(n)@pre \then
	 *  										getMine(i)@pre
	 *  									\else
	 *  										getMine(i)@pre.abandoned()
	 *  							\else
	 *  								getMine(i)@pre
	 *  			\else
	 *  				getMine(i)@pre
	 *  
	 *  \post: \foreach i \in numeroesRoute() :
	 *  			getRoute(i)=getRoute(i)@pre
	 *  
	 *  \post: \foreach i \in numeroesMuraille() :
	 *  getMuraille(i)=
	 *        \if c1 == ECommande.ATTAQUER_MURAILLE \then
	 *         \if a1 == i \then
	 *             \if s1.size() > 1 \then
	 *             new GestionCombat().initMurailleMultiple(s1, getMuraille(i)).combatMurailleMultiple().getMuraille()
	 *             \else
	 *              new GestionCombat().initMuraille(s1[0], getMuraille(i)).combatMuraille().getMuraille()
	 *         \if c2 == ECommande.ATTAQUER_MURAILLE \then
   *          \if a2 == i \then
   *             \if s2.size() > 1 \then
   *             new GestionCombat().initMurailleMultiple(s2, getMuraille(i)).combatMurailleMultiple().getMuraille()
   *             \else
   *              new GestionCombat().initMuraille(s2[0], getMuraille(i)).combatMuraille().getMuraille()
      \else
	 *  			getMuraille(i)@pre
	 *  
	 *  \post: hotelDeVilleA()=
	 *  			\if getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE \then
	 *  				\if getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() \then
	 *  					\if c1=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSA()@pre.enConstruction() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSA()@pre.prixConstruction() &&
	 *  							getHotel(a1)@pre==hotelDeVilleA()@pre \then
	 *  						hotelDeVilleA()@pre.retrait(CNRSA()@pre.prixConstruction()).abandoned()
	 *  					\elseif c1=ECommande.RECHERCHECNRS &&
	 *  							!CNRSA()@pre.enRecherche() &&
	 *  							CNRSA()@pre.constructionFinie() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSA()@pre.prixRecherche() &&
	 *  							getHotel(a1)@pre==hotelDeVilleA()@pre \then
	 *  						hotelDeVilleA()@pre.retrait(CNRSA()@pre.prixRecherche()).abandoned()
	 *  					\else
	 *  						hotelDeVilleA()@pre.abandoned()
	 *  
	 *  				\elseif getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() \then
	 *  					\if c2=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSA()@pre.enConstruction() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSA()@pre.prixConstruction() &&
	 *  							getHotel(a2)@pre==hotelDeVilleA()@pre \then
	 *  						hotelDeVilleA()@pre.retrait(CNRSA()@pre.prixConstruction()).abandoned()
	 *  					\elseif c2=ECommande.RECHERCHECNRS &&
	 *  							!CNRSA()@pre.enRecherche() &&
	 *  							CNRSA()@pre.constructionFinie() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSA()@pre.prixRecherche() &&
	 *  							getHotel(a2)@pre==hotelDeVilleA()@pre \then
	 *  						hotelDeVilleA()@pre.retrait(CNRSA()@pre.prixRecherche()).abandoned()
	 *  					\else
	 *  						hotelDeVilleA()@pre.abandoned()
	 *  
	 *  				\else
	 *  						hotelDeVilleA()@pre.abandoned()
	 *  
	 *  \post: hotelDeVilleB()=
	 *  			\if getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE \then
	 *  				\if getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() \then
	 *  					\if c1=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSB()@pre.enConstruction() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSB()@pre.prixConstruction() &&
	 *  							getHotel(a1)@pre==hotelDeVilleB()@pre \then
	 *  						hotelDeVilleB()@pre.retrait(CNRSB()@pre.prixConstruction()).abandoned()
	 *  					\elseif c1=ECommande.RECHERCHECNRS &&
	 *  							!CNRSB()@pre.enRecherche() &&
	 *  							CNRSB()@pre.constructionFinie() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSB()@pre.prixRecherche() &&
	 *  							getHotel(a1)@pre==hotelDeVilleB()@pre \then
	 *  						hotelDeVilleB()@pre.retrait(CNRSB()@pre.prixRecherche()).abandoned()
	 *  					\else
	 *  						hotelDeVilleB()@pre.abandoned()
	 *  
	 *  				\elseif getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() \then
	 *  					\if c2=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSB()@pre.enConstruction() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSB()@pre.prixConstruction() &&
	 *  							getHotel(a2)@pre==hotelDeVilleB()@pre \then
	 *  						hotelDeVilleB()@pre.retrait(CNRSB()@pre.prixConstruction()).abandoned()
	 *  					\elseif c2=ECommande.RECHERCHECNRS &&
	 *  							!CNRSB()@pre.enRecherche() &&
	 *  							CNRSB()@pre.constructionFinie() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSB()@pre.prixRecherche() &&
	 *  							getHotel(a2)@pre==hotelDeVilleB()@pre \then
	 *  						hotelDeVilleB()@pre.retrait(CNRSB()@pre.prixRecherche()).abandoned()
	 *  					\else
	 *  						hotelDeVilleB()@pre.abandoned()
	 *  
	 *  				\else
	 *  						hotelDeVilleB()@pre.abandoned()
	 *  
	 *  \post: CNRSA()=
	 *  		\if getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE \then
	 *  				\if getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() \then
	 *  					\if c1=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSA()@pre.enConstruction() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSA()@pre.prixConstruction() &&
	 *  							getHotel(a1)@pre==hotelDeVilleA()@pre \then
	 *  						CNRSB()@pre.commencerConstruction()
	 *  					\elseif c1=ECommande.RECHERCHECNRS &&
	 *  							!CNRSA()@pre.enRecherche() &&
	 *  							CNRSA()@pre.constructionFinie() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSA()@pre.prixRecherche() &&
	 *  							getHotel(a1)@pre==hotelDeVilleA()@pre \then
	 *  						CNRSA()@pre.commencerRecherche()
	 *  					\else
	 *  						CNRSA()@pre
	 *  		\elseif getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() \then
	 *  					\if c2=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSA()@pre.enConstruction() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSA()@pre.prixConstruction() &&
	 *  							getHotel(a2)@pre==hotelDeVilleA()@pre \then
	 *  						CNRSA()@pre.commencerConstruction()
	 *  					\elseif c2=ECommande.RECHERCHECNRS &&
	 *  							!CNRSA()@pre.enRecherche() &&
	 *  							CNRSA()@pre.constructionFinie() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSA()@pre.prixRecherche() &&
	 *  							getHotel(a2)@pre==hotelDeVilleA()@pre \then
	 *  						CNRSA()@pre.commencerConstruction()
	 *  					\else
	 *  						CNRSA()@pre
	 *  
	 *  		\elseif CNRSA()@pre.enConstruction() \then
	 *  				\if !CNRSA()@pre.constructionFinie() \then
	 *  					CNRSA()@pre.construire()
	 *  				\else
	 *  					CNRSA()@pre
	 *  
	 *  		\elseif CNRSA()@pre.enRecherche() \then
	 *  				\if !CNRSA()@pre.rechercheFinie() \then
	 *  					CNRSA()@pre.recherche()
	 *  
	 *  				\elseif c1== ECommande.APPLIQUER_RECHERCHE &&
	 *  						getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE &&
	 *  						getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() &&
	 *  						getHotel(a1)@pre==hotelDeVilleA() \then
	 *  					CNRSA()@pre.finirRecherche()
	 *  
	 *  				\elseif c2== ECommande.APPLIQUER_RECHERCHE &&
	 *  						getHotel(a2)@pre.etat_d_appartenance()==EEtat.OCCUPE &&
	 *  						getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() &&
	 *  						getHotel(a2)@pre==hotelDeVilleA() \then
	 *  					CNRSA()@pre.finirRecherche()
	 *  				
	 *  				\else
	 *  					CNRSA()@pre
	 *  		\else
	 *  			CNRSA()@pre
	 *  
	 *  \post: CNRSB()=
	 *  		\if getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE \then
	 *  				\if getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() \then
	 *  					\if c1=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSB()@pre.enConstruction() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSB()@pre.prixConstruction() &&
	 *  							getHotel(a1)@pre==hotelDeVilleB()@pre \then
	 *  						CNRSB()@pre.commencerConstruction()
	 *  					\elseif c1=ECommande.RECHERCHECNRS &&
	 *  							!CNRSB()@pre.enRecherche() &&
	 *  							CNRSB()@pre.constructionFinie() &&
	 *  							getHotel(a1)@pre.orRestant() >= CNRSB()@pre.prixRecherche() &&
	 *  							getHotel(a1)@pre==hotelDeVilleB()@pre \then
	 *  						CNRSB()@pre.commencerRecherche()
	 *  					\else
	 *  						CNRSB()@pre
	 *  		\elseif getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() \then
	 *  					\if c2=ECommande.CONSTRUIRECNRS &&
	 *  							!CNRSB()@pre.enConstruction() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSB()@pre.prixConstruction() &&
	 *  							getHotel(a2)@pre==hotelDeVilleB()@pre \then
	 *  						CNRSB()@pre.commencerConstruction()
	 *  					\elseif c2=ECommande.RECHERCHECNRS &&
	 *  							!CNRSB()@pre.enRecherche() &&
	 *  							CNRSB()@pre.constructionFinie() &&
	 *  							getHotel(a2)@pre.orRestant() >= CNRSB()@pre.prixRecherche() &&
	 *  							getHotel(a2)@pre==hotelDeVilleB()@pre \then
	 *  						CNRSB()@pre.commencerConstruction()
	 *  					\else
	 *  						CNRSB()@pre
	 *  
	 *  		\elseif CNRSB()@pre.enConstruction() \then
	 *  				\if !CNRSB()@pre.constructionFinie() \then
	 *  					CNRSB()@pre.construire()
	 *  				\else
	 *  					CNRSB()@pre
	 *  
	 *  		\elseif CNRSB()@pre.enRecherche() \then
	 *  				\if !CNRSB()@pre.rechercheFinie() \then
	 *  					CNRSB()@pre.recherche()
	 *  
	 *  				\elseif c1== ECommande.APPLIQUER_RECHERCHE &&
	 *  						getHotel(a1)@pre.etat_d_appartenance()==EEtat.OCCUPE &&
	 *  						getHotel(a1)@pre.appartenance() == getVillageois(s1.get(0))@pre.race() &&
	 *  						getHotel(a1)@pre==hotelDeVilleB() \then
	 *  					CNRSB()@pre.finirRecherche()
	 *  
	 *  				\elseif c2== ECommande.APPLIQUER_RECHERCHE &&
	 *  						getHotel(a2)@pre.etat_d_appartenance()==EEtat.OCCUPE &&
	 *  						getHotel(a2)@pre.appartenance() == getVillageois(s2.get(0))@pre.race() &&
	 *  						getHotel(a2)@pre==hotelDeVilleB() \then
	 *  					CNRSB()@pre.finirRecherche()
	 *  				
	 *  				\else
	 *  					CNRSB()@pre
	 *  		\else
	 *  			CNRSB()@pre
	 */
	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2, Set<Integer> s2, int a2);
}
