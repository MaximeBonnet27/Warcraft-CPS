package warcraft.contracts;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Set;

import warcraft.decorators.MoteurJeuDecorator;
import warcraft.enums.ECommande;
import warcraft.enums.EETAT;
import warcraft.enums.ERace;
import warcraft.enums.EResultat;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IMineService;
import warcraft.services.IMoteurJeuService;
import warcraft.services.IMurailleService;
import warcraft.services.IRouteService;
import warcraft.services.IVillageoisService;

public class MoteurJeuContrat extends MoteurJeuDecorator {

	public MoteurJeuContrat(IMoteurJeuService moteur) {
		super(moteur);
	}

	public void checkInvariants(){
		// \inv : 0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu();
		assertTrue("// \\inv : 0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu()", 0<=super.pasJeuCourant() && super.pasJeuCourant()<=super.maxPasJeu());

		/* \inv : estFini() == 	(hotelDeVilleA().orRestant() >= 1664 && hotelDeVilleA().etat_d_appartenance()==EETAT.OCCUPE) ||
		 * 						(hotelDeVilleB().orRestant() >= 1664 && hotelDeVilleB().etat_d_appartenance()==EETAT.OCCUPE) ||
		 * 						((hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) &&
		 * 							(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) &&
		 * 							 (hotelDeVilleA().appartenance() == hotelDeVilleB().appartenance()))
		 * */
		try {
			assertTrue("\\inv : estFini()=",super.estFini()==((super.hotelDeVilleA().orRestant() >= 1664 && super.hotelDeVilleA().etat_d_appartenance()==EETAT.OCCUPE) ||
					(super.hotelDeVilleB().orRestant() >= 1664 && super.hotelDeVilleB().etat_d_appartenance()==EETAT.OCCUPE) ||
					((super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) &&
							(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) &&
							(super.hotelDeVilleA().appartenance() == super.hotelDeVilleB().appartenance()))));
		} catch (Exception e) {
			assertTrue("\\inv : estFini()=",false);
		}

		/* \inv : resultatFinal() == EResultat.ORC \ssi 
				  			  (	(hotelDeVilleA().orRestant() >= 1664) &&
				  				(hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
				  				(hotelDeVilleA().appartenance() == ERACE.ORC)	)
				  			||( (hotelDeVilleB().orRestant() >= 1664) &&
				  				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
				  				(hotelDeVilleB().appartenance() == ERACE.ORC)	)
				  			||( (hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
				  				(hotelDeVilleA().appartenance() == ERACE.ORC) &&
				  				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
				  				(hotelDeVilleB().appartenance() == ERACE.ORC)	)
		 */

		try {
			assertTrue("\\inv : resultatFinal() == EResultat.ORC",(super.resultatFinal()==EResultat.ORC)==
					((super.hotelDeVilleA().orRestant() >= 1664) &&
							(super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
							(super.hotelDeVilleA().appartenance() == ERace.ORC)) ||
							((super.hotelDeVilleB().orRestant() >= 1664) &&
									(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
									(super.hotelDeVilleB().appartenance() == ERace.ORC)) ||
									((super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
											(super.hotelDeVilleA().appartenance() == ERace.ORC) &&
											(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
											(super.hotelDeVilleB().appartenance() == ERace.ORC)));
		} catch (Exception e) {
			assertTrue("\\inv : resultatFinal() == EResultat.ORC",false);
		}


		/* \inv : resultatFinal() == EResultat.HUMAIN \ssi 
	  			  (	(hotelDeVilleA().orRestant() >= 1664) &&
	  				(hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	  				(hotelDeVilleA().appartenance() == ERACE.HUMAIN)	)
	  			||( (hotelDeVilleB().orRestant() >= 1664) &&
	  				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	  				(hotelDeVilleB().appartenance() == ERACE.HUMAIN)	)
	  			||( (hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
	 				(hotelDeVilleA().appartenance() == ERACE.HUMAIN) &&
	  				(hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
	  				(hotelDeVilleB().appartenance() == ERACE.HUMAIN)	)
		 */
		try {
			assertTrue("\\inv : resultatFinal() == EResultat.HUMAIN",(super.resultatFinal()==EResultat.HUMMAIN)==
					(((super.hotelDeVilleA().orRestant() >= 1664) &&
							(super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
							(super.hotelDeVilleA().appartenance() == ERace.HUMAIN)) ||
							((super.hotelDeVilleB().orRestant() >= 1664) &&
									(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
									(super.hotelDeVilleB().appartenance() == ERace.HUMAIN)) ||
									((super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) && 
											(super.hotelDeVilleA().appartenance() == ERace.HUMAIN) &&
											(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) && 
											(super.hotelDeVilleB().appartenance() == ERace.HUMAIN))));
		} catch (Exception e) {
			assertTrue("\\inv : resultatFinal() == EResultat.HUMAIN", false);
		}

		/* \inv : resultatFinal() == EResultat.NULL \ssi
		  				hotelDeVilleA().orRestant() < 1664 &&
		  				hotelDeVilleB().orRestant() < 1664 && 
		  				hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE &&
		  				hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE &&	
		  				hotelDeVilleA().appartenance() != hotelDeVilleB().appartenance()
		 */
		try {
			assertTrue("\\inv : resultatFinal() == EResultat.NULL", (super.resultatFinal()==EResultat.NULL)==
					(super.hotelDeVilleA().orRestant() < 1664 &&
							super.hotelDeVilleB().orRestant() < 1664 && 
							super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE &&
							super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE &&	
							super.hotelDeVilleA().appartenance() != super.hotelDeVilleB().appartenance()));
		} catch (Exception e) {
			assertTrue("\\inv : resultatFinal() == EResultat.NULL",false);
		}

		/* \inv : peutEntrer(numVillageois, numMine) ==
	  			distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
	  					 positionMineX(numMine), positionMineY(numMine)) <= 51
	  			&& (getMine(numMine).etat_d_appartenance() == EETAT.OCCUPE 
	  				=> getMine(numMine).appartenance() == getVillageois(numVillageois).race())	
		 */
		for(int numVillageois: super.numeroesVillageois()){
			for(int numMine: super.numeroesMine()){
				try {
					assertTrue("\\inv : peutEntrer(numVillageois, numMine)", super.peutEntrer(numVillageois, numMine)==(Point.distance(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois),
							super.positionMineX(numMine), super.positionMineY(numMine))<=51 &&
							(!(super.getMine(numMine).etat_d_appartenance() == EETAT.OCCUPE) || (super.getMine(numMine).appartenance() == super.getVillageois(numVillageois).race()))));
				} catch (Exception e) {
					assertTrue("\\inv : peutEntrer(numVillageois, numMine)",false);
				}
			}
		}	

		/* \inv : peutEntrerHotelVille(numVillageois, numHotel) == 
				  			numHotel == 0 => distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
				  					 		positionHotelVilleXA, positionHotelVilleYA) <= 51
				  							&&
				  							getHotelVilleA().etat_d_appartenance() == ETAT.OCCUPE
				  							=> getHotelVilleA().appartenance() == getVillageois(numVillageois).race()
				 		 && numHotel == 1 => distance(positionVillageoisX(numVillageois), positionVillageoisY(numVillageois),
				  					 		positionHotelVilleXB, positionHotelVilleYB) <= 51
				  							&&
				  							getHotelVilleB().etat_d_appartenance() == ETAT.OCCUPE
				  							=> getHotelVilleB<().appartenance() == getVillageois(numVillageois).race()
		 */

		for(int numVillageois: super.numeroesVillageois()){
			try {
				assertTrue("\\inv : peutEntrerHotelVille(numVillageois, numHotel)", 
						(super.peutEntrerHotelVille(numVillageois, 0) == ((Point.distance(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois),
								super.positionHotelVilleXA(), super.positionHotelVilleYA()) <= 51) &&
								(!(super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) || (super.hotelDeVilleA().appartenance() == super.getVillageois(numVillageois).race())))) ||
								(super.peutEntrerHotelVille(numVillageois, 1) == ((Point.distance(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois),
										super.positionHotelVilleXB(), super.positionHotelVilleYB()) <= 51) &&
										(!(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) || (super.hotelDeVilleB().appartenance() == super.getVillageois(numVillageois).race())))));
			} catch (Exception e) {
				assertTrue("\\inv : peutEntrerHotelVille(numVillageois, numHotel)",false);
			}
		}

		//\inv: villageoisSurRoute(M,x,y,l,h,r) == collision(x,y,l,h,positionRouteX(r),positionRouteY(r),getRoute(r).largeur(),getRoute(r).hauteur())
		for(int numVillageois: super.numeroesVillageois()){
			for(int r: super.numeroesRoute()){
				assertTrue("\\inv: villageoisSurRoute(M,x,y,l,h,r)", villageoisSurRoute(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois), super.getVillageois(numVillageois).largeur(), super.getVillageois(numVillageois).hauteur(), r) ==
						super.collision(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois), super.getVillageois(numVillageois).largeur(), super.getVillageois(numVillageois).hauteur(),
								super.positionRouteX(r),super.positionRouteY(r),super.getRoute(r).largeur(),super.getRoute(r).hauteur()));
			}
		}

		//\inv: villageoisSurMuraille(M,x,y,l,h,m) == collision(x,y,l,h,positionMurailleX(m),positionMurailleY(m),getMuraille(m).largeur(),getMuraille(m).hauteur())
		for(int numVillageois: super.numeroesVillageois()){
			for(int r: super.numeroesMuraille()){
				assertTrue("\\inv: villageoisSurMuraille(M,x,y,l,h,r)", villageoisSurMuraille(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois), super.getVillageois(numVillageois).largeur(), super.getVillageois(numVillageois).hauteur(), r) ==
						super.collision(super.positionVillageoisX(numVillageois), super.positionVillageoisY(numVillageois), super.getVillageois(numVillageois).largeur(), super.getVillageois(numVillageois).hauteur(),
								super.positionMurailleX(r),super.positionMurailleY(r),super.getMuraille(r).largeur(),super.getMuraille(r).hauteur()));
			}
		}
	}

	@Override
	public int largeurTerrain() {
		return super.largeurTerrain();
	}

	@Override
	public int hauteurTerrain() {
		return super.hauteurTerrain();
	}

	@Override
	public int maxPasJeu() {
		return super.maxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		return super.pasJeuCourant();
	}

	@Override
	public boolean estFini() {
		return super.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		// \pre : estFini()
		assertTrue("\\pre : estFini()", super.estFini());
		return super.resultatFinal();
	}

	@Override
	public Set<Integer> numeroesVillageois() {
		return super.numeroesVillageois();
	}

	@Override
	public IVillageoisService getVillageois(int num) {
		// \pre : \exists i \in numeroesVillageois() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == num", super.numeroesVillageois().contains(new Integer(num)));
		return super.getVillageois(num);
	}

	@Override
	public int positionVillageoisX(int num) {
		// \pre : \exists i \in numeroesVillageois() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == num", super.numeroesVillageois().contains(new Integer(num)));
		return super.positionVillageoisX(num);
	}

	@Override
	public int positionVillageoisY(int num) {
		// \pre : \exists i \in numeroesVillageois() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == num", super.numeroesVillageois().contains(new Integer(num)));
		return super.positionVillageoisY(num);
	}

	@Override
	public IMineService getMineVillageois(int num) {
		// \pre : \exists i \in numeroesVillageois() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == num", super.numeroesVillageois().contains(new Integer(num)));
		// \pre : getVillageois(num).enCorvee()
		assertTrue("\\pre : getVillageois(num).enCorvee()", super.getVillageois(num).enCorvee());
		return super.getMineVillageois(num);
	}

	@Override
	public Set<Integer> numeroesMine() {
		return super.numeroesMine();
	}

	@Override
	public IMineService getMine(int num) {
		// \pre : \exists i \in numeroesMine() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMine() \\tq i == num", super.numeroesMine().contains(new Integer(num)));
		return super.getMine(num);
	}

	@Override
	public int positionMineX(int num) {
		// \pre : \exists i \in numeroesMine() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMine() \\tq i == num", super.numeroesMine().contains(new Integer(num)));
		return super.positionMineX(num);
	}

	@Override
	public int positionMineY(int num) {
		// \pre : \exists i \in numeroesMine() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMine() \\tq i == num", super.numeroesMine().contains(new Integer(num)));
		return super.positionMineY(num);
	}

	@Override
	public Set<Integer> numeroesRoute() {
		return super.numeroesRoute();
	}

	@Override
	public IRouteService getRoute(int num) {
		// \pre : \exists i \in numeroesRoute() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesRoute() \\tq i == num", super.numeroesRoute().contains(new Integer(num)));
		return super.getRoute(num);
	}

	@Override
	public int positionRouteX(int num) {
		// \pre : \exists i \in numeroesRoute() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesRoute() \\tq i == num", super.numeroesRoute().contains(new Integer(num)));
		return super.positionRouteX(num);
	}

	@Override
	public int positionRouteY(int num) {
		// \pre : \exists i \in numeroesRoute() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesRoute() \\tq i == num", super.numeroesRoute().contains(new Integer(num)));
		return super.positionRouteY(num);
	}

	@Override
	public Set<Integer> numeroesMuraille() {
		return super.numeroesMuraille();
	}

	@Override
	public IMurailleService getMuraille(int num) {
		// \pre : \exists i \in numeroesMuraille() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMuraille() \\tq i == num", super.numeroesMuraille().contains(new Integer(num)));
		return super.getMuraille(num);
	}

	@Override
	public int positionMurailleX(int num) {
		// \pre : \exists i \in numeroesMuraille() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMuraille() \\tq i == num", super.numeroesMuraille().contains(new Integer(num)));
		return super.positionMurailleX(num);
	}

	@Override
	public int positionMurailleY(int num) {
		// \pre : \exists i \in numeroesMuraille() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMuraille() \\tq i == num", super.numeroesMuraille().contains(new Integer(num)));
		return super.positionMurailleY(num);
	}

	@Override
	public boolean villageoisSurRoute(int x, int y, int l, int h, int r) {
		// \pre : \exists i \in numeroesRoute() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesRoute() \\tq i == r", super.numeroesRoute().contains(new Integer(r)));
		return super.villageoisSurRoute(x, y, l, h, r);
	}

	@Override
	public boolean villageoisSurMuraille(int x, int y, int l, int h, int m) {
		// \pre : \exists i \in numeroesMuraille() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMuraille() \\tq i == m", super.numeroesMuraille().contains(new Integer(m)));
		return super.villageoisSurMuraille(x, y, l, h, m);
	}

	@Override
	public IHotelDeVilleService hotelDeVilleA() {
		return super.hotelDeVilleA();
	}

	@Override
	public IHotelDeVilleService hotelDeVilleB() {
		return super.hotelDeVilleB();
	}

	@Override
	public IHotelDeVilleService getHotel(int numHotel) {
		// \pre : numHotel == 0 || numHotel == 1
		assertTrue("\\pre : numHotel == 0 || numHotel == 1", numHotel==0 || numHotel==1);
		return super.getHotel(numHotel);
	}

	@Override
	public int positionHotelVilleXA() {
		return super.positionHotelVilleXA();
	}

	@Override
	public int positionHotelVilleYA() {
		return super.positionHotelVilleYA();
	}

	@Override
	public int positionHotelVilleXB() {
		return super.positionHotelVilleXB();
	}

	@Override
	public int positionHotelVilleYB() {
		return super.positionHotelVilleYB();
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// \pre : \exists i \in numeroesVillageois() \tq i == numVillageois
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == numVillageois", super.numeroesVillageois().contains(new Integer(numVillageois)));
		// \pre : \exists i \in numeroesMine() \tq i == num
		assertTrue("\\pre : \\exists i \\in numeroesMine() \\tq i == numMine", super.numeroesMine().contains(new Integer(numMine)));
		return super.peutEntrer(numVillageois, numMine);
	}

	@Override
	public boolean peutEntrerHotelVille(int numVillageois, int numHotel) {
		// \pre : \exists i \in numeroesVillageois() \tq i == numVillageois
		assertTrue("\\pre : \\exists i \\in numeroesVillageois() \\tq i == numVillageois", super.numeroesVillageois().contains(new Integer(numVillageois)));
		// \pre : numHotel == 0 || numHotel == 1
		assertTrue("\\pre : numHotel == 0 || numHotel == 1", numHotel==0 || numHotel==1);
		return super.peutEntrerHotelVille(numVillageois, numHotel);
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSA() {
		return super.CNRSA();
	}

	@Override
	public ICentreNationalRechercheSpecialeService CNRSB() {
		return super.CNRSB();
	}

	@Override
	public void init(int largeur, int hauteur, int maxPas) {
		// \pre: largeur >=600
		assertTrue("\\pre: largeur >=600", largeur>=600);
		// \pre: hauteur >=400
		assertTrue("\\pre: hauteur >=400", hauteur>=400);
		// \pre: maxPas >= 0
		assertTrue("\\pre: maxPas >= 0", maxPas>=0);

		super.init(largeur, hauteur, maxPas);
		checkInvariants();

		//\post: largeurTerrain() == largeur
		assertTrue("\\post: largeurTerrain() == largeur", super.largeurTerrain()==largeur);

		// \post: hauteurTerrain() == hauteur
		assertTrue("\\post: hauteurTerrain() == hauteur", super.hauteurTerrain()==hauteur);

		// \post: maxPasJeu() == maxPas
		assertTrue("\\post: maxPasJeu() == maxPas", super.maxPasJeu()==maxPas);

		// \post: pasJeuCourant() == 0
		assertTrue("\\post: pasJeuCourant() == 0", super.pasJeuCourant()==0);

		// \post: numerosVillageois() == {0,...,199}
		for(Integer i=0;i<200;i++)
			assertTrue("\\post: numerosVillageois() == {0,...,199}", super.numeroesVillageois().contains(i));

		/* \post: \foreach i \in numeroesVillageois():
				 			\if i<100 \then
				 				getVillageois(i)=new Villageois().init(ERace.ORC,3,3,20,1,50) &&
				 				distance(positionVillageoisX(i),positionVillageoisY(i), positionHotelVilleXA(), positionHotelVilleYA()) ≤ 51
				 			\else
				 				getVillageois(i)=new Villageois().init(ERace.HUMAIN,3,3,10,1,100) &&
				 				distance(positionVillageoisX(i),positionVillageoisY(i), positionHotelVilleXB(), positionHotelVilleYB()) ≤ 51
		 */
		//new Villageois().init(... appel a un autre service pas de test sur les posts
		for(int i: super.numeroesVillageois()){
			if(i<100){
		 				assertTrue("distance init villageois", Point.distance(super.positionVillageoisX(i),positionVillageoisY(i), super.positionHotelVilleXA(), super.positionHotelVilleYA()) <= 51);
			}else{
				assertTrue("distance init villageois", Point.distance(super.positionVillageoisX(i),super.positionVillageoisY(i), super.positionHotelVilleXB(), super.positionHotelVilleYB()) <= 51);
			}
		}

		// \post: numeroesMines()={0,...,19}
		for(Integer i=0;i<19;i++){
			assertTrue("\\post: numeroesMines()={0,...,19}", super.numeroesMine().contains(i));
		}
		
		/* \post: \foreach i \in numeroesMines():
				 			getMine(i)=new Mine().init(50,50) &&
				 			positionMineX(i) = random(largeurTerrain()) &&
				 			positionMineY(i) = random(hauteurTerrain())
		 */ 
		//new Mine().init(... appel a un autre service pas de test sur les posts
		for(int i:super.numeroesMine()){
			assertTrue("position init mine",super.positionMineX(i)>=0 && super.positionMineX(i)<super.largeurTerrain() && super.positionMineY(i)>=0 && super.positionMineY(i)<super.hauteurTerrain());
		}
		
		// \post: numeroesRoute()={0,...,99}
		for(Integer i=0;i<99;i++){
			assertTrue("\\post: numeroesRoute()={0,...,99}", super.numeroesRoute().contains(i));
		}
		
		/* \post: \foreach i \in numeroesRoute():
				 			getRoute(i)=new Route().init(5,5,2) &&
				 			positionRouteX(i) = random(largeurTerrain()) &&
				 			positionRouteY(i) = random(hauteurTerrain())
		 */
		//new Route().init(... appel a un autre service pas de test sur les posts
		for(int i:super.numeroesRoute()){
			assertTrue("position init route",super.positionRouteX(i)>=0 && super.positionRouteX(i)<super.largeurTerrain() && super.positionRouteY(i)>=0 && super.positionRouteY(i)<super.hauteurTerrain());
		}
		
		// \post: numeroesMuraille()={0,...,49}
		for(Integer i=0;i<49;i++){
			assertTrue("\\post: numeroesMuraille()={0,...,49}", super.numeroesMuraille().contains(i));
		}
		
		/* \post: \foreach i \in numeroesMuraille():
				 			getMuraille(i)=new Muraille().init(5,5,10) &&
				 			positionMurailleX(i) = random(largeurTerrain()) &&
				 			positionMurailleY(i) = random(hauteurTerrain())
		*/
		for(int i:super.numeroesMuraille()){
			assertTrue("position init muraille",super.positionMurailleX(i)>=0 && super.positionMurailleX(i)<super.largeurTerrain() && super.positionMurailleY(i)>=0 && super.positionMurailleY(i)<super.hauteurTerrain());
		}

		// \post: hotelDeVilleA() == new HotelDeVille().init(ERace.ORC,100,100)
		// autre service
		
		// \post: hotelDeVilleB() == new HotelDeVille().init(ERace.HUMAIN,100,100)
		// autre service
		
		// \post: positionHotelDeVilleXA() == 0
		assertTrue("\\post: positionHotelDeVilleXA() == 0", super.positionHotelVilleXA()==0);
		// \post: positionHotelDeVilleYA() == 0
		assertTrue("\\post: positionHotelDeVilleYA() == 0", super.positionHotelVilleYA()==0);

		// \post: positionHotelDeVilleXB() == largeurTerrain()-hotelDeVilleB().largeur()-1
		assertTrue("\\post: positionHotelDeVilleXB() == largeurTerrain()-hotelDeVilleB().largeur()-1", super.positionHotelVilleXB()==super.largeurTerrain()-super.hotelDeVilleB().largeur()-1);
		// \post: positionHotelDeVilleYB() == hauteurTerrain()-hotelDeVilleB().hauteur()-1
		assertTrue("\\post: positionHotelDeVilleYB() == hauteurTerrain()-hotelDeVilleB().hauteur()-1", super.positionHotelVilleYB()==super.hauteurTerrain()-super.hotelDeVilleB().hauteur()-1);

		// \post: CNRSA() == new CentreNationalRechercheSpeciale().init(100,25,500,100)
		// autre service
		// \post: CNRSB() == new CentreNationalRechercheSpeciale().init(100,25,500,100)
		// autre service
	}

	@Override
	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		//\pre : !estFini()
		assertTrue("\\pre : !estFini()", !super.estFini());
		
		/* \pre : \foreach i \in s1 :
		  			i \in numeroesVillageois() &&
		  			getVillageois(i).race() == ERace.HUMAIN &&
		  			(c1 == ECommande.DEPLACER => (0 <= a1 && a1 <= 360)) &&
					(c1 == ECommande.ENTRERMINE => (a1 \in numeroesMine() && peutEntrer(i, a1))) &&
		  			(c1 == ECommande.ENTRERHOTELVILLE => (a1 == 0 || a1 == 1) && peutEntrerHotelVille(i, a1))
		  			(c1 == ECommande.CONSTRUIRECNRS => (a1 == 0 || a1 == 1) && getHotel(a1).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a1).appartenance() == getVillageois(i).race())
		  			(c1 == ECommande.RECHERCHECNRS => (a1 == 0 || a1 == 1) && getHotel(a1).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a1).appartenance() == getVillageois(i).race())
		  			(c1 == ECommande.ATTAQUER => (100 <= a1 && a1 <= 199) &&
		  			(c1 == ECommancde.APPLIQUER_RECHERCHE => ((a1== 0 || a1 = 1) && getHotel(a1).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a1).appartenance() == getVillageois(i).race())) &&
	 * 				(c1 == ECommande.ATTAQUER_MURAILLE => a1 \in numeroesMuraille()
		 */
		for(Integer i :s1){
				try {
					assertTrue("mauvais parametre pour J1 pas de Jeux", super.numeroesVillageois().contains(i) &&
							(super.getVillageois(i).race()==ERace.HUMAIN) &&
							(!(c1==ECommande.DEPLACER) || (0<=a1 && a1<=360)) &&
							(!(c1==ECommande.ENTRERMINE) || (super.numeroesMine().contains(a1) && super.peutEntrer(i, a1)))&&
							(!(c1==ECommande.ENTRERHOTELVILLE) || ((a1==0 || a1==1) && super.peutEntrerHotelVille(i, a1))) &&
							(!(c1==ECommande.CONSTRUIRECNRS) || ((a1==0 || a1==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race()) &&
							(!(c1==ECommande.RECHERCHECNRS) || ((a1==0 || a1==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race())) &&
							(!(c1==ECommande.ATTAQUER) || (100<=a1 && a1 <=199))&&
							(!(c1==ECommande.ATTAQUER_MURAILLE) || (super.numeroesMuraille().contains(a1)))&&
							(!(c1==ECommande.APPLIQUER_RECHERCHE) || ((a1==0 || a1==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race())))) ;
				} catch (Exception e) {
					assertTrue("mauvais parametre pour J1 pas de Jeux",false);
				}
		}
		
		/* \pre : \foreach i \in s2 :
		  			i \in numeroesVillageois() &&
		  			getVillageois(i).race() == ERace.ORC &&
		  			(c2 == ECommande.DEPLACER => (0 <= a2 && a2 <= 360)) &&
		  			(c2 == ECommande.ENTRERMINE => (a2 \in numeroesMine() && peutEntrer(i, a2))) &&
		  			(c2 == ECommande.ENTRERHOTELVILLE => (a2 == 0 || a2 == 1) && peutEntrerHotelVille(i, a2))
		  			(c2 == ECommande.CONSTRUIRECNRS => (a2 == 0 || a2 == 1) && getHotel(a2).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a2).appartenance() == getVillageois(i).race())
		  			(c2 == ECommande.RECHERCHECNRS => (a2 == 0 || a2 == 1) && getHotel(a2).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a2).appartenance() == getVillageois(i).race())
		  			(c2 == ECommande.ATTAQUER => (100 <= a2 && a2 <= 199) &&
		  			(c2==ECommancde.APPLIQUER_RECHERCHE => ((a2== 0 || a2 = 1) && getHotel(a2).etat_d_appartenance() == EETAT.OCCUPE && getHotel(a2).appartenance() == getVillageois(i).race())) &&
	 * 				(c2 == ECommande.ATTAQUER_MURAILLE => a2 \in numeroesMuraille()
		 */
		
		for(Integer i :s2){
			try {
				assertTrue("mauvais parametre pour J2 pas de Jeux", super.numeroesVillageois().contains(i) &&
						(super.getVillageois(i).race()==ERace.ORC) &&
						(!(c2==ECommande.DEPLACER) || (0<=a2 && a2<=360)) &&
						(!(c2==ECommande.ENTRERMINE) || (super.numeroesMine().contains(a2) && super.peutEntrer(i, a2)))&&
						(!(c2==ECommande.ENTRERHOTELVILLE) || ((a2==0 || a2==1) && super.peutEntrerHotelVille(i, a2))) &&
						(!(c2==ECommande.CONSTRUIRECNRS) || ((a2==0 || a2==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race()) &&
						(!(c2==ECommande.RECHERCHECNRS) || ((a2==0 || a2==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race())) &&
						(!(c2==ECommande.ATTAQUER) || (100<=a2 && a2 <=199))&&
						(!(c2==ECommande.ATTAQUER_MURAILLE) || (super.numeroesMuraille().contains(a1)))&&
						(!(c2==ECommande.APPLIQUER_RECHERCHE) || ((a2==0 || a2==1) && super.getHotel(a1).etat_d_appartenance()==EETAT.OCCUPE && super.getHotel(a1).appartenance()==super.getVillageois(i).race())))) ;
			} catch (Exception e) {
				assertTrue("mauvais parametre pour J1 pas de Jeux",false);
			}
	}
		checkInvariants();
		super.pasJeu(c1, s1, a1, c2, s2, a2);
		checkInvariants();
		
	}



}
