package warcraft.decorators;

import java.util.Set;

import warcraft.enums.ECommande;
import warcraft.enums.EResultat;
import warcraft.services.ICentreNationalRechercheSpecialeService;
import warcraft.services.IHotelDeVilleService;
import warcraft.services.IMineService;
import warcraft.services.IMoteurJeuService;
import warcraft.services.IMurailleService;
import warcraft.services.IRouteService;
import warcraft.services.IVillageoisService;

public class MoteurJeuDecorator {
	private IMoteurJeuService moteur;

	public MoteurJeuDecorator(IMoteurJeuService moteur) {
		super();
		this.moteur = moteur;
	}

	public int largeurTerrain() {
		return moteur.largeurTerrain();
	}

	public int hauteurTerrain() {
		return moteur.hauteurTerrain();
	}

	public int maxPasJeu() {
		return moteur.maxPasJeu();
	}

	public int pasJeuCourant() {
		return moteur.pasJeuCourant();
	}

	public boolean estFini() {
		return moteur.estFini();
	}

	public EResultat resultatFinal() {
		return moteur.resultatFinal();
	}

	public Set<Integer> numeroesVillageois() {
		return moteur.numeroesVillageois();
	}

	public IVillageoisService getVillageois(int num) {
		return moteur.getVillageois(num);
	}

	public int positionVillageoisX(int num) {
		return moteur.positionVillageoisX(num);
	}

	public int positionVillageoisY(int num) {
		return moteur.positionVillageoisY(num);
	}

	public IMineService getMineVillageois(int num) {
		return moteur.getMineVillageois(num);
	}

	public Set<Integer> numeroesMine() {
		return moteur.numeroesMine();
	}

	public IMineService getMine(int num) {
		return moteur.getMine(num);
	}

	public int positionMineX(int num) {
		return moteur.positionMineX(num);
	}

	public int positionMineY(int num) {
		return moteur.positionMineY(num);
	}

	public Set<Integer> numeroesRoute() {
		return moteur.numeroesRoute();
	}

	public IRouteService getRoute(int num) {
		return moteur.getRoute(num);
	}

	public int positionRouteX(int num) {
		return moteur.positionRouteX(num);
	}

	public int positionRouteY(int num) {
		return moteur.positionRouteY(num);
	}

	public Set<Integer> numeroesMuraille() {
		return moteur.numeroesMuraille();
	}

	public IMurailleService getMuraille(int num) {
		return moteur.getMuraille(num);
	}

	public int positionMurailleX(int num) {
		return moteur.positionMurailleX(num);
	}

	public int positionMurailleY(int num) {
		return moteur.positionMurailleY(num);
	}

	public boolean villageoisSurRoute(int x, int y, int l, int h, int r) {
		return moteur.villageoisSurRoute(x, y, l, h, r);
	}

	public boolean villageoisSurMuraille(int x, int y, int l, int h, int m) {
		return moteur.villageoisSurMuraille(x, y, l, h, m);
	}

	public IHotelDeVilleService hotelDeVilleA() {
		return moteur.hotelDeVilleA();
	}

	public IHotelDeVilleService hotelDeVilleB() {
		return moteur.hotelDeVilleB();
	}

	public IHotelDeVilleService getHotel(int numHotel) {
		return moteur.getHotel(numHotel);
	}

	public int positionHotelVilleXA() {
		return moteur.positionHotelVilleXA();
	}

	public int positionHotelVilleYA() {
		return moteur.positionHotelVilleYA();
	}

	public int positionHotelVilleXB() {
		return moteur.positionHotelVilleXB();
	}

	public int positionHotelVilleYB() {
		return moteur.positionHotelVilleYB();
	}

	public boolean peutEntrer(int numVillageois, int numMine) {
		return moteur.peutEntrer(numVillageois, numMine);
	}

	public boolean peutEntrerHotelVille(int numVillageois, int numHotel) {
		return moteur.peutEntrerHotelVille(numVillageois, numHotel);
	}

	public ICentreNationalRechercheSpecialeService CNRSA() {
		return moteur.CNRSA();
	}

	public ICentreNationalRechercheSpecialeService CNRSB() {
		return moteur.CNRSB();
	}

	public void init(int largeur, int hauteur, int maxPas) {
		moteur.init(largeur, hauteur, maxPas);
	}

	public void pasJeu(ECommande c1, Set<Integer> s1, int a1, ECommande c2,
			Set<Integer> s2, int a2) {
		moteur.pasJeu(c1, s1, a1, c2, s2, a2);
	}
	
	
}
