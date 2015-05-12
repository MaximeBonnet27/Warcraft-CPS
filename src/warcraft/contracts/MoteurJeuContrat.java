package warcraft.contracts;

import static org.junit.Assert.*;

import java.util.Set;

import warcraft.decorators.MoteurJeuDecorator;
import warcraft.decorators.asserttr;
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
		assertTrue("\\inv : estFini()=",(super.hotelDeVilleA().orRestant() >= 1664 && super.hotelDeVilleA().etat_d_appartenance()==EETAT.OCCUPE) ||
				(super.hotelDeVilleB().orRestant() >= 1664 && super.hotelDeVilleB().etat_d_appartenance()==EETAT.OCCUPE) ||
				((super.hotelDeVilleA().etat_d_appartenance() == EETAT.OCCUPE) &&
						(super.hotelDeVilleB().etat_d_appartenance() == EETAT.OCCUPE) &&
						(super.hotelDeVilleA().appartenance() == super.hotelDeVilleB().appartenance())));

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

		assertTrue("\\inv : resultatFinal() == EResultat.ORC",!(super.resultatFinal()==EResultat.ORC) ||
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
		
		//l 143 IMoteurJeuService
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
		// TODO Auto-generated method stub
		super.init(largeur, hauteur, maxPas);
	}

	@Override
	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		// TODO Auto-generated method stub
		super.pasJeu(c1, s1, a1, c2, s2, a2);
	}



}
